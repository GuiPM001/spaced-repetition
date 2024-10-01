(ns spaced-repetition-processing.services.cards
  (:require [spaced-repetition-processing.logic.cards :as logic]
            [spaced-repetition-processing.db.cards :as db]))

(defn process-review [card-id qtd-days]
  (let [next-review-day (logic/get-current-date-plus-days qtd-days)
        next-qtd-days (logic/next-fibonacci qtd-days)]
       (db/update-next-review card-id next-review-day next-qtd-days)))
