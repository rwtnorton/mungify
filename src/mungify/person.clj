(ns mungify.person
  (:require [clj-time.core :as time]
            [clj-time.format :as time.format]
            [clojure.spec.alpha :as spec]
            [clojure.spec.test.alpha :refer [enumerate-namespace]]
            [orchestra.spec.test :as spec.test]))

(defrecord Person [id
                   first-name
                   last-name
                   gender
                   favorite-color
                   date-of-birth])

(def ^:dynamic *default-id* -1)

(defonce EMPTY (map->Person {:id             *default-id*
                             :first-name     ""
                             :last-name      ""
                             :gender         ""
                             :favorite-color ""
                             :date-of-birth  (time/date-time 0)}))

(spec/def ::id integer?)
(spec/def ::first-name string?)
(spec/def ::last-name string?)
(spec/def ::gender string?)
(spec/def ::favorite-color string?)
(spec/def ::date-of-birth inst?)
(spec/def ::person (spec/keys :req-un [::id
                                       ::first-name
                                       ::last-name
                                       ::gender
                                       ::favorite-color
                                       ::date-of-birth]))

(spec/fdef new-person
  :args (spec/cat :params map?)
  :ret ::person)
(defn new-person
  [{:keys [id first-name last-name gender favorite-color date-of-birth]
    :as   params}]
  {:pre [(map? params)
         (integer? id)
         (string? first-name)
         (string? last-name)
         (string? gender)
         (string? favorite-color)
         (or (string? date-of-birth)
             (inst? date-of-birth))]}
  (-> params
      (update :date-of-birth #(if (string? %) (time.format/parse %) %))
      map->Person))

(spec.test/instrument (enumerate-namespace (ns-name *ns*)))
