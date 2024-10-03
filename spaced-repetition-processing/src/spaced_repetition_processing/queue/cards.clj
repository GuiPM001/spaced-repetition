(ns spaced-repetition-processing.queue.cards
  (:require [langohr.basic :as basic]
            [langohr.channel :as ch]
            [langohr.core :as core]
            [langohr.queue :as queue]
            [langohr.consumers :as consumers]
            [spaced-repetition-processing.services.cards :as services]
            [spaced-repetition-processing.logic.cards :as logic]))

(def queue-name "process-review")

(defn open-connection []
  (let [connection (core/connect {:host     "rabbitmq"
                                  :username "guest"
                                  :password "guest"})
        channel (ch/open connection)]
    channel))

(defn handle-message [^bytes message]
  (let [data (logic/convert-bytes-to-map message)]
    (future (services/process-review (:id data) (:qtd-days data)))))

(defn start-consumer []
  (let [channel (open-connection)]
    (queue/declare channel queue-name)
    (consumers/subscribe channel queue-name
                             (fn [ch metadata ^bytes message]
                               (handle-message message)
                               (basic/ack ch (:delivery-tag metadata))))))