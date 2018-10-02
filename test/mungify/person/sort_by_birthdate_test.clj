(ns mungify.person.sort-by-birthdate-test
  (:require [clojure.test :refer :all]
            [mungify.person.sort-by-birthdate :as sorter]
            [mungify.person :as p]
            [clj-time.core :as time]))

(deftest sort-people
  (let [alice (p/new-person {:id             1
                             :first-name     "Alice"
                             :last-name      "Smith"
                             :gender         "female"
                             :favorite-color "blue"
                             :date-of-birth  (time/date-time 1984)})
        bob   (p/new-person {:id             2
                             :first-name     "Bob"
                             :last-name      "Jones"
                             :gender         "male"
                             :favorite-color "green"
                             :date-of-birth  (time/date-time 1986)})
        pat   (p/new-person {:id             3
                             :first-name     "Pat"
                             :last-name      "Doe"
                             :gender         "nope"
                             :favorite-color "grey"
                             :date-of-birth  (time/date-time 1987)})
        zoe   (p/new-person {:id             4
                             :first-name     "Zoe"
                             :last-name      "Ziggy"
                             :gender         "female"
                             :favorite-color "blue"
                             :date-of-birth  (time/date-time 1984 2)})
        chuck (assoc bob :id 5 :first-name "Chuck" :last-name "Aardvark")
        fay   (assoc pat :id 6 :first-name "Fay" :last-name "Aardvark")
        mandy (assoc zoe :id 7 :first-name "Mandy" :last-name "Aardvark"
                     :date-of-birth (time/date-time 1984 2 2))]
    (are [args expected] (= expected (sorter/sort-people args))
      [] []
      [pat] [pat]

      [zoe pat bob alice chuck fay mandy]
      [alice zoe mandy
       bob chuck
       pat fay])))
