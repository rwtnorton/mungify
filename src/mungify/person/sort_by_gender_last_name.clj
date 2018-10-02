(ns mungify.person.sort-by-gender-last-name
  (:require [mungify.person :as p]
            [clojure.string :as str]
            [clojure.spec.alpha :as spec]
            [clojure.spec.test.alpha :refer [enumerate-namespace]]
            [orchestra.spec.test :as spec.test]))

(defn female?
  [s]
  (when s
    (re-seq #"\bfemale\b" (str/lower-case s))))

(defn male?
  [s]
  (when s
    (re-seq #"\bmale\b" (str/lower-case s))))

;; Please understand that nothing in this code is intended as a judgement call
;; regarding gender.  The requirements call for a sorting by gender.

(defn classify-gender
  [s]
  (cond
    (female? s) :female
    (male? s) :male
    :else :other))

(defmulti cmp-by-gender
  (fn [gender1 gender2]
    (mapv classify-gender [gender1 gender2])))
(defmethod cmp-by-gender :default
  [gender1 gender2]
  (throw (IllegalStateException. (format "bad cmp-by-gender: [%s %s]"
                                         gender1 gender2))))
(defmethod cmp-by-gender [:female :female] [& _] 0)
(defmethod cmp-by-gender [:female :male] [& _] -1)
(defmethod cmp-by-gender [:male :female] [& _] 1)
(defmethod cmp-by-gender [:male :male] [& _] 0)
(defmethod cmp-by-gender [:female :other] [& _] -1)
(defmethod cmp-by-gender [:male :other] [& _] -1)
(defmethod cmp-by-gender [:other :female] [& _] 1)
(defmethod cmp-by-gender [:other :male] [& _] 1)
(defmethod cmp-by-gender [:other :other]
  [gender1 gender2]
  (compare gender1 gender2))

(spec/fdef sort-people
  :args (spec/cat :persons (spec/coll-of ::p/person))
  :fn (fn [{:keys [ret args]}]
        (= (count (:persons args))
           (count ret)))
  :ret (spec/coll-of ::p/person))
(defn sort-people
  [persons]
  (sort-by (juxt (comp classify-gender :gender)
                 :last-name
                 :id)
           compare
           persons))

(spec.test/instrument (enumerate-namespace (ns-name *ns*)))
