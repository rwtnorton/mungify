(defproject mungify "0.1.0-SNAPSHOT"
  :description "mungify Person records from stdin to stdout sorted"
  :url "https://github.com/rwtnorton/mungify"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :plugins [
            [lein-cloverage "1.0.13"] ;; really should be in ~/.lein/profiles.clj
                                      ;; but keeping it here for portability
            ]
  :main mungify.main)

