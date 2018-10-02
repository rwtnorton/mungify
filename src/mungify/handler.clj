(ns mungify.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :refer [wrap-json-response]]
            [mungify.person.sort-by-gender-last-name :as gln.sorter]
            [mungify.person.sort-by-birthdate :as bd.sorter]
            [mungify.person.sort-by-last-name :as ln.sorter]
            [mungify.person.parser :as parser]
            [clojure.java.io :as io]))

;; Help cheshire understand how to JSON-ify Joda Time objects.
(extend-protocol cheshire.generate/JSONable
  org.joda.time.DateTime
  (to-json [dt gen]
    (cheshire.generate/write-string gen (str dt))))

#_(def people (atom []))
(def people
  (atom (map-indexed (fn [i s] (assoc (parser/string->person s) :id (inc i)))
                     (-> "sample-data-space"
                         io/resource
                         slurp
                         (java.io.StringReader.)
                         (java.io.BufferedReader.)
                         line-seq))))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/records/gender" [] (gln.sorter/sort-people @people))
  (GET "/records/birthdate" [] (bd.sorter/sort-people @people))
  (GET "/records/name" [] (ln.sorter/sort-people @people))
  (route/not-found "Not Found"))

(def app
  (wrap-json-response app-routes site-defaults))
