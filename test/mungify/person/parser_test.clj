(ns mungify.person.parser-test
  (:require [clojure.test :refer :all]
            [clj-time.core :as time]
            [mungify.person :as p]
            [mungify.person.parser :as parser]))

(def ^:private ->person (partial p/->Person nil))

(deftest string->person
  (are [s expected] (= expected (parser/string->person s))
    nil p/EMPTY

    "" p/EMPTY

    "Foo" p/EMPTY

    "Bar | Foo | female | blue | 2010-08-25"
    (->person "Foo" "Bar" "female" "blue" (time/date-time 2010 8 25))

    "Quux, Baz, female, red, 2011-09-26"
    (->person "Baz" "Quux" "female" "red" (time/date-time 2011 9 26))

    "Xyzzy Foo male black 2012-10-27"
    (->person "Foo" "Xyzzy" "male" "black" (time/date-time 2012 10 27))

    "Xyzzy Foo male black 2012-10-27 lalala"
    p/EMPTY))
