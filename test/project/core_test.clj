(ns project.core-test
  (:require [clojure.test :refer :all]
            [project.core :refer :all]
            [clojure.java.jdbc :as jdbc]))

(deftest test-path-from-pid
  (testing "basic path-from-pid functionality"
    (is (= (path-from-pid "test:1234") "/tmp/test_1234"))
    (is (= (path-from-pid "test:4321") "/tmp/test_4321"))))

(def db-spec
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname "test.db"})

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

(defn cleanup-db
  [db-name]
  (clojure.java.io/delete-file db-name)
  )

(defn db-test-fixture [f]
  (init-test-db "test.db")
  (f)
  (cleanup-db "test.db"))

(use-fixtures :each db-test-fixture)
