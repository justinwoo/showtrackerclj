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

(defn select-all []
  (select shows))

(defn select-by-id [id]
  (first 
    (select shows
      (where { :id id }))))

(defn insert-show [show]
  (insert shows
    (values show)))

(defn delete-by-id [id]
  (delete shows
    (where { :id id })))

(defn update-show [id show]
  (update shows
    (set-fields show)
    (where { :id id })))