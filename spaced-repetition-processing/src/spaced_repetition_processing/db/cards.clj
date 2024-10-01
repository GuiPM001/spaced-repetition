(ns spaced-repetition-processing.db.cards
  (:require [clojure.java.jdbc :as jdbc]))

(def db-spec
  {:dbtype   "mysql"
   :dbname   "spaced_repetition"
   :user     "root"
   :password "123"
   :host     "db"
   :port     3306})

(defn update-next-review [id next-review qtd-days]
  (jdbc/execute! db-spec
                 ["UPDATE cards
                   SET next_review = ?,
                       next_qtd_days = ?
                   WHERE id = ?" next-review qtd-days id]))