(ns spaced-repetition-crud.middleware.cors)

(def cors-headers
  "Generic CORS headers"
  {"Access-Control-Allow-Origin"  "*"
   "Access-Control-Allow-Headers" "*"
   "Access-Control-Allow-Methods" "*"})

(defn preflight? [request]
  (= (request :request-method) :options))

(defn all-cors [handler]
  (fn [request]
    (if (preflight? request)
      {:status 200
       :headers cors-headers
       :body "preflight complete"}
      (let [response (handler request)]
        (update-in response [:headers]
                   merge cors-headers )))))