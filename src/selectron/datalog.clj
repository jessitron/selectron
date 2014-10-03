(ns selectron.datalog
  (:use [datomic.api :only (db q) :as d])
  )

(defn- zip-with-index [s]
  (map vector s (range)))

;; maps to EAV (entity, attibute, value) triples
(defn- evify [maps]
  (into [] (for [[m i] (zip-with-index maps)
                 [k v] m]
             [i k v]))) ;; index becomes entity; key is attribute; value is value.
;; (evify hard-coded-data) yields
;; [[1 :number 4] // an entity "1" has number two
;;  [1 :animal "Raccoon"] // an entity "1" has animal Raccoon
;;  [2 :number 8] // an entity "2" has number eight
;;  [2 :animal "Tarantula"] // an entity "2" has animal Tarantula
;; ...]

(defn datalog-select [data id]
 (first (first (q
  `[:find ?name
     :where
     [?m :animal ?name]
     [?m :number ~id]]
   (evify data)))))

;; the query says:
;; [:find ?name     ;; find every value for name
;; :where           ;; such that there exist a set of facts satisfying
;;   [?m :animal ?name]  ;; some entity has animal of this name
;;   [?m :number ~id]    ;; and the same entity has number of this id.

; what future changes would make this design shine?
; - lots of other little tables to do the same way
; - more complicated queries
