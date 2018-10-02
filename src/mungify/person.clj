(ns mungify.person)

(defrecord Person [id
                   first-name
                   last-name
                   gender
                   favorite-color
                   date-of-birth])

(defonce EMPTY (map->Person {}))

(defn new-person
  [{:keys [id first-name last-name gender favorite-color date-of-birth]
    :as params}]
  (map->Person params))
