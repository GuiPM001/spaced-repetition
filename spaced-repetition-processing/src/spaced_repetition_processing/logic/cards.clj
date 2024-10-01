(ns spaced-repetition-processing.logic.cards
  (:require [cheshire.core :as json])
  (:import [java.time LocalDate]))

(defn next-fibonacci [n]
  (if (= n 1) 2
    (loop [a 0 b 1]
      (if (>= b n)
        (+ a b)
        (recur b (+ a b))))))

(defn get-current-date-plus-days [days]
  (str (.plusDays (LocalDate/now) days)))

(defn convert-bytes-to-map [^bytes message]
  (let [message-string (String. message "UTF-8")]
    (json/parse-string message-string true)))