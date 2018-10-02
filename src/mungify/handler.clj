(ns mungify.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [site-defaults]]
            [ring.middleware.json :refer [wrap-json-response]]
            [mungify.person.sort-by-gender-last-name :as gln.sorter]
            [mungify.person.sort-by-birthdate :as bd.sorter]
            [mungify.person.sort-by-last-name :as ln.sorter]
            [mungify.person.parser :as parser]
            [mungify.person :as p]
            [clojure.java.io :as io]))

;; Help cheshire understand how to JSON-ify Joda Time objects.
(extend-protocol cheshire.generate/JSONable
  org.joda.time.DateTime
  (to-json [dt gen]
    (cheshire.generate/write-string gen (str dt))))

(def people (atom []))
#_(def people
    (let [rs (map-indexed (fn [i s]
                            (assoc (parser/string->person s) :id (inc i)))
                          (-> "sample-data-space"
                              io/resource
                              slurp
                              (java.io.StringReader.)
                              (java.io.BufferedReader.)
                              line-seq))]
      (atom rs)))

(defn add-person
  [{:keys [body] :as request}]
  (let [s      (slurp body)
        person (parser/string->person s)]
    (if (= person p/EMPTY)
      {:status 400 :body {:error "invalid input"}}
      (do
        (swap! people
               (fn [rs]
                 (let [id (inc (count rs))]
                   (conj rs (assoc person :id id)))))
        {:status 201 :body ""}))))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (POST "/records" request add-person)
  (GET "/records/gender" [] (gln.sorter/sort-people @people))
  (GET "/records/birthdate" [] (bd.sorter/sort-people @people))
  (GET "/records/name" [] (ln.sorter/sort-people @people))
  (route/not-found "Not Found"))

(def app
  (wrap-json-response app-routes site-defaults))
