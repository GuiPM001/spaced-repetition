(ns spaced-repetition-crud.main
  (:gen-class)
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [spaced-repetition-crud.api :as api]
            [spaced-repetition-crud.middleware.cors :as m]))

(defn start-server []
  (let [handler (-> (api/routes)
                    (wrap-defaults api-defaults)
                    (m/all-cors))]
    (run-jetty handler {:port 3300 :join? false})))

(defn -main [& args]
  (start-server))