(ns mungify.person.sort-by-birthdate
  (:require [mungify.person :as p]
            [clojure.string :as str]
            [clojure.spec.alpha :as spec]
            [clojure.spec.test.alpha :refer [enumerate-namespace]]
            [orchestra.spec.test :as spec.test]))

(spec/fdef sort-people
  :args (spec/cat :persons (spec/coll-of ::p/person))
  :fn (fn [{:keys [ret args]}]
        (= (count (:persons args))
           (count ret)))
  :ret (spec/coll-of ::p/person))
(defn sort-people
  [persons]
  (sort-by (juxt :date-of-birth :id) persons))

(spec.test/instrument (enumerate-namespace (ns-name *ns*)))
