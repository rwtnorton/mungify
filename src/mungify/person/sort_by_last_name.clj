(ns mungify.person.sort-by-last-name
  (:require [mungify.person :as p]
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
  (sort-by :last-name (comp - compare) persons))

(spec.test/instrument (enumerate-namespace (ns-name *ns*)))
