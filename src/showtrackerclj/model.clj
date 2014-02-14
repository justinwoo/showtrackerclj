(ns showtrackerclj.model)

(def data-store
  (atom []))

(defn find-by-id [shows id]
  (first (filter (fn [show] (= (:id show) (Integer/parseInt id)))
         shows)))