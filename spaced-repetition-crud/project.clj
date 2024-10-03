(defproject spaced-repetition-crud "0.1.0-SNAPSHOT"
  :description "CRUD microservice to manage flashcards"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [mysql/mysql-connector-java "5.1.47"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [com.novemberain/langohr "2.3.2"]

                 [ring "1.11.0"]
                 [ring/ring-core "1.11.0"]
                 [ring/ring-defaults "0.4.0"]
                 [ring/ring-jetty-adapter "1.11.0"]
                 [ring-cors "0.1.13"]

                 [metosin/compojure-api "2.0.0-alpha31"]
                 [metosin/ring-swagger-ui "5.9.0"]
                 [metosin/jsonista "0.3.8"]
                 [com.fasterxml.jackson.core/jackson-core "2.16.1"]
                 [com.fasterxml.jackson.core/jackson-annotations "2.16.1"]]
  :main ^:skip-aot spaced-repetition-crud.main
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
