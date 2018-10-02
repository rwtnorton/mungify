(ns mungify.person-test
  (:require [clojure.test :refer :all]
            [clj-time.core :as time]
            [mungify.person :as p]))

(def good-params {:id             42
                  :first-name     "Foo"
                  :last-name      "Bar"
                  :gender         "female"
                  :favorite-color "blue"
                  :date-of-birth  "2010-08-25"})

(deftest new-person
  (are [args expected] (= expected (p/new-person args))
    good-params
    (p/->Person 42 "Foo" "Bar" "female" "blue" (time/date-time 2010 8 25)))

  (are [args] (thrown-with-msg? Throwable
                                #"(did not conform to spec|Assert failed)"
                                (p/new-person args))
    nil
    {}
    (dissoc good-params :id)
    (dissoc good-params :first-name)
    (dissoc good-params :last-name)
    (dissoc good-params :gender)
    (dissoc good-params :favorite-color)
    (dissoc good-params :date-of-birth)))
