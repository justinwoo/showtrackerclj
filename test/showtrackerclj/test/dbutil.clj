(ns showtrackerclj.test.dbutil
  (:use showtrackerclj.dbutil
        clojure.test
        cheshire.core))

(deftest crappy-test
  (let [cleanup (delete-by-id 63)  
        result (insert-show { :title "test" :episode 1})]
    (println result)
    (println (class result))))