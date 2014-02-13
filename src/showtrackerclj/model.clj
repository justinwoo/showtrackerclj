(ns showtrackerclj.model)

(def data-store
  (atom {
    :shows [{
      "id" 1
      "title" "sdkjflksdj"
      "episode" 1}]}))

(defn find-by-id [map id]
  (let [shows (:shows map)]
    (filter (fn [show] 
              (= (:id show) id))
            shows)))