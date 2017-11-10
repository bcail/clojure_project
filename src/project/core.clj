(ns project.core
  (:gen-class))

(defn path-from-pid
  [pid]
  (apply str ["/tmp/" (clojure.string/replace pid ":" "_")]))

(defn test-path-from-pid
  []
  (print "  test-path-from-pid... ")
  (if (= (path-from-pid "test:1234") "/tmp/test_1234")
    (print "passed ")
    (print "failed "))
  (if (= (path-from-pid "test:4321") "/tmp/test_4321")
    (println "passed")
    (println "failed")))

(defn run-tests
  []
  (println "running tests")
  (test-path-from-pid))

(defn -main
  "Run tests"
  [& args]
  (run-tests))
