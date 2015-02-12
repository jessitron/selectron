(defproject selectron "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :plugins [[lein-gorilla "0.3.3"]]
  :license {:name "CC0"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/test.check "0.7.0"]
                 [com.datomic/datomic-free "0.9.4470"]
                 [prismatic/schema "0.3.0"]])
