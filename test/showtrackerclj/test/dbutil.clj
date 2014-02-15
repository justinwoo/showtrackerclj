(ns showtrackerclj.test.dbutil
  (:use showtrackerclj.dbutil
        clojure.test))

(deftest crappy-test
  (println (get-all)))