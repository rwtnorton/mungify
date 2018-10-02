(ns mungify.person
  (:require [clj-time.format :as time.format]))

(defrecord Person [id
                   first-name
                   last-name
                   gender
                   favorite-color
                   date-of-birth])

(defonce EMPTY (map->Person {}))

(defn new-person
  [{:keys [id first-name last-name gender favorite-color date-of-birth]
    :as   params}]
  (-> params
      (update :date-of-birth #(if % (time.format/parse %) %))
      map->Person))
