(ns mungify.person.sort-by-gender-last-name-test
  (:require [clojure.test :refer :all]
            [mungify.person.sort-by-gender-last-name :as sorter]
            [mungify.person :as p]
            [clj-time.core :as time]))

(deftest female?
  (are [s] (sorter/female? s)
    "female"
    "Female"
    "FEMALE")
  (are [s] (not (sorter/female? s))
    nil
    ""
    "phfemale"
    "femalely"
    "male"))

(deftest male?
  (are [s] (sorter/male? s)
    "male"
    "Male"
    "MALE")
  (are [s] (not (sorter/male? s))
    nil
    ""
    "phmale"
    "malely"
    "female"))

(deftest classify-gender
  (are [s expected] (= expected (sorter/classify-gender s))
    "male" :male
    "female" :female
    "" :other
    "dunno" :other))

(deftest cmp-by-gender
  (are [g1 g2 expected] (= expected (sorter/cmp-by-gender g1 g2))
    "female" "female" 0
    "male" "male" 0
    "female" "male" -1
    "male" "female" 1
    "a" "a" 0
    "a" "b" -1
    "b" "a" 1))

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
                             :gender         "bleh"
                             :favorite-color "grey"
                             :date-of-birth  (time/date-time 1987)})
        zoe   (p/new-person {:id             4
                             :first-name     "Zoe"
                             :last-name      "Ziggy"
                             :gender         "female"
                             :favorite-color "blue"
                             :date-of-birth  (time/date-time 1984)})
        chuck (assoc bob :id 5 :first-name "Chuck" :last-name "Aardvark")
        fay   (assoc pat :id 6 :first-name "Fay" :last-name "Aardvark")
        mandy (assoc zoe :id 7 :first-name "Mandy" :last-name "Aardvark")]
    (are [args expected] (= expected (sorter/sort-people args))
      [] []
      [pat] [pat]
      [zoe pat bob alice] [alice zoe bob pat]

      [zoe pat bob alice chuck fay mandy]
      [mandy alice zoe
       chuck bob
       fay pat])))
