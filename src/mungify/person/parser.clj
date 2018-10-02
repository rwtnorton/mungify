(ns mungify.person.parser
  (:require [clojure.string :as str]
            [mungify.person :as p]))

(def ^:dynamic *sep-regex* #"\s*[,| ]\s*")
(def ^:dynamic *fields* [:last-name
                         :first-name
                         :gender
                         :favorite-color
                         :date-of-birth])

(defn string->person
  [s]
  (if-not s
    p/EMPTY
    (let [tokens (str/split s *sep-regex*)]
      (if (not= (count tokens) (count *fields*))
        p/EMPTY
        (p/new-person (zipmap *fields* tokens))))))
