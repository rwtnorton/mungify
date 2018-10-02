(ns mungify.main-test
  (:require [clojure.test :refer :all]
            [mungify.main :as main]))

(deftest main
  (is (= "ohai!\n"
         (with-out-str
           (main/-main)))))
