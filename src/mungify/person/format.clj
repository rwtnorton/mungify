(ns mungify.person.format
  (:require [clj-time.format :as time.format]
            [clojure.string :as str]
            [clojure.spec.alpha :as spec]
            [clojure.spec.test.alpha :refer [enumerate-namespace]]
            [orchestra.spec.test :as spec.test]
            [mungify.person :as p]))

(def date-formatter (time.format/formatter "M/d/yyyy"))

(spec/fdef person->string
  :args (spec/cat :person ::p/person)
  :ret string?)
(defn person->string
  [{:keys [id first-name last-name gender favorite-color date-of-birth]
    :as   p}]
  (format "%d: %s %s (%s) [%s] %s"
          id first-name last-name
          (if (seq gender)
            (subs (str/upper-case gender) 0 1)
            gender)
          favorite-color
          (time.format/unparse date-formatter date-of-birth)))

(spec.test/instrument (enumerate-namespace (ns-name *ns*)))
