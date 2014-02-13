(ns showtrackerclj.handler
  (:use [compojure.core]
        [showtrackerclj.model])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as resp]           
            [ring.middleware.json :as json]))

(defroutes app-routes
  (GET "/" []
    (resp/redirect "/index.html"))
  (GET "/shows" []
    {:body @data-store})
  (GET "/shows/:id" [id]
    {:body {:show (find-by-id @data-store id)}})
  (PUT "/shows/:id" {id :id body :body}
    (println id body))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      (json/wrap-json-body)
      (json/wrap-json-params)
      (json/wrap-json-response)))
