(ns showtrackerclj.dbutil
  (:use korma.db
        korma.core))

(defdb db-store
  (postgres { :db "showtrackerdb"
              :user "pg-user"
              :password "mycrappypassword"
              :host "localhost"
              :port "5432" }))

(defentity shows
  (pk :id)
  (table :shows)
  (database db-store)
  (entity-fields :title :episode))

(defn get-all []
  (select shows))

