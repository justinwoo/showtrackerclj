(ns showtrackerclj.handler
  (:use [compojure.core]
        [cheshire.core]
        [showtrackerclj.model])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as resp]           
            [ring.middleware.json :as json]))

(defn respond-json [body & [status]]
  { :status (or status 200) 
    :headers { "Content-Type" "application/json" }
    :body (generate-string body) })

(defn extract-json [params]
  (parse-string (slurp (:body params)) true))

(defroutes app-routes
  (GET "/" []
    (resp/redirect "/index.html"))
  (context "/shows" [] (defroutes shows-routes 
    (GET "/" []
      (respond-json @data-store))
    (POST "/" params
        (let [show (:show (extract-json params))]
          (println show)))
    (context "/:id" [id] (defroutes ids-routes
      (GET "/" []
        (respond-json {:show (find-by-id @data-store id)}))
      (DELETE "/" params
        (println id))
      (PUT "/" params
        (let [show (:show (extract-json params))]
          (println id (:title show) (:episode show))))))))
  (route/resources "/")
  (route/not-found "Not Found"))


(def app
  (handler/site app-routes))
