(ns mungify.person-test
  (:require [clojure.test :refer :all]
            [clj-time.core :as time]
            [mungify.person :as p]))

(deftest new-person
  (are [args expected] (= expected (p/new-person args))
    nil p/EMPTY

    {} p/EMPTY

    {:id             42
     :first-name     "Foo"
     :last-name      "Bar"
     :gender         "female"
     :favorite-color "blue"
     :date-of-birth  "2010-08-25"}
    (p/->Person 42 "Foo" "Bar" "female" "blue" (time/date-time 2010 8 25))))
