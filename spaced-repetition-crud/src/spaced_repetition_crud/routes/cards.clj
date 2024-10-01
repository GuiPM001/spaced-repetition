(ns spaced-repetition-crud.routes.cards
  (:require [clojure.data.json :as json]
            [io.pedestal.http.route.definition.table :as table]
            [spaced-repetition-crud.controllers.cards :as controller]))

(defn get-by-id [request]
  (let [id (Integer. (get-in request [:path-params :id]))
        card (controller/get-card-by-id id)]
    (if (:message card)
      {:status  404
       :body    card
       :headers {"Content-Type" "application/json"}}
      {:status  200
       :body    card
       :headers {"Content-Type" "application/json"}})))

(defn insert-new-card [request]
  (let [body (-> request :body slurp (json/read-str :key-fn keyword))
        front (:front body)
        back (:back body)]
    (controller/insert-new-card front back)
    {:status  201
     :body    {:message "Card created"}
     :headers {"Content-Type" "application/json"}}))

(defn review-card [request]
  (let [id (Integer. (get-in request [:path-params :id]))
        option-choose (Integer. (get-in request [:path-params :next-review]))]
    (controller/review-card id option-choose)
    {:status  200
     :headers {"Content-Type" "application/json"}}))

(defn get-cards-to-review [request]
  {:status  200
   :body    (json/write-str (controller/get-cards-to-review))
   :headers {"Content-Type" "application/json"}})

(def routes
  (table/table-routes
    [["/card/:id" :get get-by-id :route-name :get-by-id]
     ["/cards" :post insert-new-card :route-name :insert-new-card]
     ["/review-card/id/:id/next-review/:next-review" :post review-card :route-name :review-card]
     ["/cards-to-review" :get get-cards-to-review :route-name :get-cards-to-review]]))
