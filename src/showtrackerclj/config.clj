(ns showtrackerclj.config
  (:use cheshire.core))

(def dbconfig
 (parse-string (slurp "dbconfig.json") true))
