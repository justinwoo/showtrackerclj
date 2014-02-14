(ns showtrackerclj.test.model
  (:use showtrackerclj.model
        clojure.test))

(deftest test-find-by-id []
  (let [show (find-by-id 1)]
    (is show)
    (is (= 1 (:id show)))))

(deftest test-find-by-title []
  (let [show (find-by-title "sometitle")]
    (is show)
    (is (= "sometitle" (:title show)))))

(deftest test-persist-show []
  (let [show { :title "sometitle" :episode 1 }
        persist (persist-show 2 show)
        result (find-by-id 2)]
    (is persist)
    (is result)
    (is (= 2 (:id result)))))

(defn setup-model []
  (let [shows (:shows @data-store)]
      (swap! data-store 
        conj { :id 1 :title "sometitle" :episode 1 })))

(defn test-fixture [f]
  (setup-model)
  (f))

(use-fixtures :once test-fixture)