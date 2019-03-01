(ns project.core
  (:gen-class)
  (:require [clojure.java.jdbc :as jdbc])
  )

(defn path-from-pid
  [pid, db-spec]
  (first
    (jdbc/query db-spec ["SELECT path FROM objects WHERE pid = ?" pid]{:row-fn :path})))

(defn -main
  "Hello World"
  [& args]
  (println "Hello World"))
