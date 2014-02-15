(ns showtrackerclj.model
  (:require [showtrackerclj.dbutil :as db]))

(defn find-all []
  (sort-by :id (db/select-all)))

(defn find-by-id [id]
  (db/select-by-id id))

(defn persist-show [show]
  (let [new-show (db/insert-show show)]
    new-show))

(defn delete-show [id]
  (db/delete-by-id id))

(defn update-show [id show]
  (let [updated-show (db/update-show id show)]
    updated-show))
