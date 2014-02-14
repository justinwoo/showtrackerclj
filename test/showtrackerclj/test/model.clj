(ns showtrackerclj.test.model
  (:use showtrackerclj.model
        clojure.test))




(deftest setup-model []
  (let [shows (:shows @data-store)]
    (do 
      (def newshows (conj shows 
                     { :id 1
                       :title "sometitle"
                       :episode 1 }))
      (println newshows)
      (swap! data-store :shows newshows))))

; (defn my-fixture [f]
;   (setup-model)
;   (f))