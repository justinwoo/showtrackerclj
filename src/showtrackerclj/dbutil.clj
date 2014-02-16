(ns showtrackerclj.dbutil
  (:use korma.db
        korma.core)
  (:require [showtrackerclj.config :as config]))

(defdb db-store
  (postgres config/dbconfig))

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
