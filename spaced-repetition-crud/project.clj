(defproject spaced-repetition-crud "0.1.0-SNAPSHOT"
  :description "CRUD microservice to manage flashcards"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [io.pedestal/pedestal.service "0.5.1"]
                 [io.pedestal/pedestal.route   "0.5.1"]
                 [io.pedestal/pedestal.jetty   "0.5.1"]
                 [org.clojure/data.json        "0.2.6"]
                 [org.clojure/java.jdbc "0.7.9"]
                 [com.novemberain/langohr "2.3.2"]]
  :main spaced-repetition-crud.main)
