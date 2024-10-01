(ns spaced-repetition-processing.main
  (:gen-class)
  (:require [spaced-repetition-processing.queue.cards :as queue]))

(defn -main [& args]
  (queue/start-consumer)
  (while true (Thread/sleep 1000)))
