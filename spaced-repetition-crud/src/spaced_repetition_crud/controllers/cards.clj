(ns spaced-repetition-crud.controllers.cards
  (:require [spaced-repetition-crud.db.cards :as db]
            [spaced-repetition-crud.logic.cards :as logic]
            [spaced-repetition-crud.queue.cards :as queue]))

(defn insert-new-card [front back]
  (let [next-review (logic/get-current-date)]
    (db/insert front back next-review)))

(defn get-card-by-id [id]
  (let [cards (db/get-by-id id)]
    (if (empty? cards)
      {:message "Card with id " id " not found."}
      (first cards))))

(defn review-card [id option-choose]
  (let [message (str "{\"id\":" id ",\"qtd-days\":" option-choose "}")]
    (queue/send-message "process-review" message)))

(defn get-cards-with-review-for-today []
  (db/get-cards-by-date (logic/get-current-date)))

(defn get-cards-to-review []
  (let [cards (get-cards-with-review-for-today)]
    (map (fn [card]
           (-> card
               (assoc :goodOption (:next_qtd_days card)
                      :badOption 1)
               (dissoc :next_qtd_days :next_review)))
         cards)))