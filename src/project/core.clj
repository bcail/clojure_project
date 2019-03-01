(ns project.core
  (:gen-class)
  (require [clojure.java.jdbc :as jdbc]))


(def db-spec
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname "test.db"})


(defn path-from-pid
  [pid]
  (apply str ["/tmp/" (clojure.string/replace pid ":" "_")]))

(def table-ddl
  (jdbc/create-table-ddl "objects"
                         [[:pid "char(20)"]
                          [:path "char(50)"]]))

(defn init-test-db
  [db-name]
  (try
    (jdbc/db-do-commands db-spec
                       [table-ddl])
    (jdbc/insert-multi! db-spec "objects" [{:pid "test:1234" :path "/tmp/test_1234"} {:pid "test:1234" :path "/tmp/test_1234"}])
    (catch java.lang.Exception ex))
  )

(defn my-run-tests
  []
  (println "initializing DB")
  (init-test-db "test.db")
  (println "running tests")
  (my-test-path-from-pid))

(defn -main
  "Run tests"
  [& args]
  (my-run-tests))
