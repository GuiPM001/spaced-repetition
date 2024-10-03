(ns spaced-repetition-crud.db.cards
  (:require [clojure.java.jdbc :as jdbc]))

(def db-spec
  {:dbtype   "mysql"
   :dbname   "spaced_repetition"
   :user     "root"
   :password "123"
   :host     "db"
   :port     3306})

(defn insert [front back next-review]
  (jdbc/execute! db-spec
                 ["INSERT INTO cards (front, back, next_review, next_qtd_days) VALUES (?, ?, ?, 1)"
                  front back next-review]))

(defn get-cards-by-date [date]
  (jdbc/query db-spec
              ["SELECT *
                FROM cards
                WHERE next_review <= ?" date]))