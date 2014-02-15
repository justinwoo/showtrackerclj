(ns showtrackerclj.test.dbutil
  (:use showtrackerclj.dbutil
        clojure.test
        cheshire.core))

(deftest crappy-test
  (let [shows (get-all)]
    (println shows)
    (println (class shows))
    (println (generate-string shows))))