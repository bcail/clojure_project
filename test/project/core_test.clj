(ns project.core-test
  (:require [clojure.test :refer :all]
            [project.core :refer :all]))

(deftest test-path-from-pid
  (testing "basic path-from-pid functionality"
    (is (= (path-from-pid "test:1234") "/tmp/test_1234"))
    (is (= (path-from-pid "test:4321") "/tmp/test_4321"))))
