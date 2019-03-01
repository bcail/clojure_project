(ns project.core
  (:gen-class)
  )

(defn path-from-pid
  [pid]
  (apply str ["/tmp/" (clojure.string/replace pid ":" "_")]))

(defn -main
  "Hello World"
  [& args]
  (println "Hello World"))
