(ns showtrackerclj.test.config
  (:use clojure.test
        cheshire.core)
  (:require [showtrackerclj.config :as config]))

;; (deftest crap
;;   (spit "dbconfig.json"
;;         (generate-string
;;         { :db "showtrackerdb"
;;           :user "pg-user"
;;           :password "mycrappypassword"
;;           :host "localhost"
;;           :port "5432" } {:pretty true})))

(deftest crap
  (print config/dbconfig)
  (print (class config/dbconfig))
  (let [mymap { :db "showtrackerdb"
              :user "pg-user"
              :password "mycrappypassword"
              :host "localhost"
              :port "5432" }]
    (print mymap)
    (print (class mymap))))
