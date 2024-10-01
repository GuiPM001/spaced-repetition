(ns spaced-repetition-crud.main
  (:gen-class)
  (:require [io.pedestal.http :as http]
            [spaced-repetition-crud.routes.cards :as routes]))

(defn create-server []
  (http/create-server
    {::http/routes routes/routes
     ::http/type   :jetty
     ::http/port   3300
     ::http/allowed-origins {:allowed-origins (constantly true)}}))

(defn -main []
  (http/start (create-server)))