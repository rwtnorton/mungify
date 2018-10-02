(ns mungify.main
  (:gen-class)
  (:require [mungify.person :as p]
            [mungify.person.parser :as parser]
            [mungify.person.format :as fmt]
            [mungify.person.sort-by-gender-last-name :as gln.sorter])
  (:import (java.io BufferedReader)))

(defn -main
  [& args]
  (let [peeps (map-indexed (fn [i s]
                             (assoc (parser/string->person s) :id (inc i)))
                           (line-seq (BufferedReader. *in*)))]
    (println "== Sorted by gender then by last name ascending")
    (doseq [p (gln.sorter/sort-people peeps)]
      (println (fmt/person->string p)))))
