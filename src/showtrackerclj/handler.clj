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
    :headers { "Content-Type" "application/json;charset=UTF-8" }
    :body (generate-string body) })

(defn extract-json [params]
  (parse-string (slurp (:body params)) true))

(defroutes app-routes
  (GET "/" []
    (resp/redirect "/index.html"))
  (context "/shows" [] (defroutes shows-routes 
    (GET "/" []
      (let [shows (find-all)]
        (respond-json { :shows shows })))
    (POST "/" params
        (let [show (:show (extract-json params))
              new-show (persist-show show)]
            (respond-json { :show new-show })))
    (context "/:id" [id] (defroutes ids-routes
      (GET "/" []
        (let [int-id (Integer/parseInt id)
              show (find-by-id int-id)]
          (if show 
            (respond-json { :show show })
            (respond-json { :show {} }))))
      (DELETE "/" params
        (let [int-id (Integer/parseInt id)
              result (delete-show int-id)]
          (respond-json { :message "show deleted" })))
      (PUT "/" params
        (let [int-id (Integer/parseInt id)
              show (:show (extract-json params))
              update (update-show int-id show)
              updated-show (find-by-id int-id)]
          (respond-json { :show updated-show })))))))
  (route/resources "/")
  (route/not-found "Not Found"))

(defn catch-errors [handler]
  (fn [request]
    (try
      (handler request)
      (catch Exception e
        (respond-json 
          { :body (str "error: " e) }
          400)))))

(def app
  (catch-errors 
    (handler/site app-routes)))
