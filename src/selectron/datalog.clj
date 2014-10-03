(ns selectron.datalog
  (:use [datomic.api :only (db q) :as d])
  )

(defn zip-with-index [s]
  (map vector s (range)))

(defn evify [maps]
  (into [] (for [[m i] (zip-with-index maps)
                 [k v] m]
             [i k v])))

(defn datalog-select [data id]
 (first (first (q
  `[:find ?name
     :where
     [?m :animal ?name]
     [?m :number ~id]]
   ( evify  data)))))
