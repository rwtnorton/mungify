(ns mungify.person.format-test
  (:require [clojure.test :refer :all]
            [clj-time.core :as time]
            [mungify.person :as p]
            [mungify.person.format :as fmt]))

(deftest person->string
  (are [person expected] (= expected (fmt/person->string person))
    (p/new-person {:id             42
                   :first-name     "Foo"
                   :last-name      "Bar"
                   :gender         "female"
                   :favorite-color "aqua"
                   :date-of-birth  "1982-03-20"})
    "42: Foo Bar (F) [aqua] 3/20/1982"

    (p/new-person {:id             9001
                   :first-name     "Quux"
                   :last-name      "Great"
                   :gender         "unknown"
                   :favorite-color "chartruse"
                   :date-of-birth  "500-01-02"})
    "9001: Quux Great (U) [chartruse] 1/2/0500"))
