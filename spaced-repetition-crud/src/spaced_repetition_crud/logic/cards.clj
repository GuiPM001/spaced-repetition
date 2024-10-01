(ns spaced-repetition-crud.logic.cards
  (:import (java.time LocalDate)))

(defn get-current-date []
  (str (LocalDate/now)))