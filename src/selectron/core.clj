(ns selectron.core)

(defn simple-select [data id-val]
  (:animal (first (filter #(= id-val (:number %)) data))))

(defn my-select [out-fn data in-fn & check]
  (first (map out-fn (filter (comp (apply partial check) in-fn) data))))

