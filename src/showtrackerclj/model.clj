(ns showtrackerclj.model
  (:use showtrackerclj.dbutil))

(def counter (atom 1))

(def data-store (atom []))

(defn find-all []
  (get-all))

(defn find-by-id [id]
  (first (filter (fn [show] (= (:id show) id))
         @data-store)))

(defn exclude-by-id [id]
  (filter (fn [show] (not= (:id show) id))
         @data-store))

(defn find-by-title [title]
  (first (filter (fn [show] (= (:title show) title))
         @data-store)))

(defn persist-show [id show]
  (let [new-show (merge show { :id id })]
    (swap! data-store conj new-show)))

(defn update-show [id show]
  (let [old-shows (exclude-by-id id)
        new-show (merge show { :id id })
        updated-shows (conj old-shows new-show)]
    (reset! data-store updated-shows)))

(defn delete-show [id]
  (let [filtered-shows (exclude-by-id id)]
    (reset! data-store filtered-shows)))