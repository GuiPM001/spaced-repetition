(ns spaced-repetition-crud.queue.cards
  (:require [langohr.basic :as basic]
            [langohr.channel :as ch]
            [langohr.core :as core]
            [langohr.queue :as queue]))

(defn open-connection []
  (let [connection (core/connect {:host     "rabbitmq"
                                  :username "guest"
                                  :password "guest"})
        channel (ch/open connection)]
    [connection channel]))

(defn close-connection [connection channel]
  (ch/close channel)
  (core/close connection))

(defn send-message [queue-name message]
  (let [[connection channel] (open-connection)]
    (queue/declare channel queue-name)
    (basic/publish channel "" queue-name (byte-array (.getBytes message)))
    (close-connection connection channel)))