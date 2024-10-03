(ns spaced-repetition-crud.api
  (:require [compojure.api.sweet :as sw]
            [ring.util.http-response :as http-response]
            [schema.core :as s]
            [spaced-repetition-crud.controllers.cards :as controller]
            [spaced-repetition-crud.schemas.cards :as schemas]))

(defn routes []
  (sw/api {:swagger
           {:ui   "/swagger"
            :spec "/swagger.json"
            :data {:info {:title       "Spaced repetition API"
                          :description "API to manage flashcards for spaced repetition study technique"
                          :consumes    ["application/json"]
                          :produces    ["application/json"]}
                   :tags [{:name "Card" :description "Flashcards operations"}]}}}

          (sw/context "/card" []
            :tags ["Card"]
            (sw/POST "/" []
              :body [card schemas/NewCardDTO]
              :summary "Add a new flashcard"
              (http-response/ok (controller/insert-new-card (:front card) (:back card))))

            (sw/POST "/review-card/id/:id/next-review/:next-review" []
              :path-params [id :- s/Int, next-review :- s/Int]
              :summary "Review a card"
              (http-response/ok (controller/review-card id next-review)))

            (sw/GET "/cards-to-review" []
              :return [schemas/CardDTO]
              :summary "Returns all flashcard to review"
              (http-response/ok (controller/get-cards-to-review))))))
