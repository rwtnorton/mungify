(defproject mungify "0.1.0-SNAPSHOT"
  :description "mungify Person records from stdin to stdout sorted"
  :url "https://github.com/rwtnorton/mungify"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/spec.alpha "0.2.176"]
                 [orchestra "2018.09.10-1"]
                 [clj-time "0.14.4"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.4.0"]]
  :plugins [[lein-ring "0.12.4"]
            ;; These really should be in ~/.lein/profiles.clj
            ;; but keeping them here for portability.
            [lein-cloverage "1.0.13"]
            [lein-cljfmt "0.6.1"]
            [lein-kibit "0.1.6"]
            [jonase/eastwood "0.2.9"]]
  :ring {:handler mungify.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}}
  :main mungify.main)
