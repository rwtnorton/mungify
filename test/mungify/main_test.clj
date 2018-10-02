(ns mungify.main-test
  (:require [clojure.test :refer :all]
            [mungify.main :as main]))

(def input-lines
  ["Bar Foo male red 2001-04-07"
   "Baz Foo female brown 2001-07-04"
   "Quux Quux female black 1972-02-28"
   "Xyzzy Foo male blue 1972-12-25"])

(deftest mungify
  (is (= "==== Sorted by gender then by last name ascending
2: Foo Baz (F) [brown] 7/4/2001
3: Quux Quux (F) [black] 2/28/1972
1: Foo Bar (M) [red] 4/7/2001
4: Foo Xyzzy (M) [blue] 12/25/1972
==== Sorted by birthdate ascending
3: Quux Quux (F) [black] 2/28/1972
4: Foo Xyzzy (M) [blue] 12/25/1972
1: Foo Bar (M) [red] 4/7/2001
2: Foo Baz (F) [brown] 7/4/2001
==== Sorted by last name descending
4: Foo Xyzzy (M) [blue] 12/25/1972
3: Quux Quux (F) [black] 2/28/1972
2: Foo Baz (F) [brown] 7/4/2001
1: Foo Bar (M) [red] 4/7/2001
"
         (with-out-str
           (main/mungify input-lines)))))
