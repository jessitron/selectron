(ns selectron.core)

;; simplest possible
(defn simple-select [data id-val]
  (:animal (first (filter #(= id-val (:number %)) data))))

;; generified to make it more explicit in the arguments
(defn my-select [out-fn data in-fn & check]
  (first (map out-fn (filter (comp (apply partial check) in-fn) data))))

;; what sort of future makes this design shine?
;; Never looking at it again!

;;
;; Eric Normand suggests a for+when
;; https://twitter.com/ericnormand/status/516812371764215808

(defn eric-select [data id-val]
  (first (for [{id :number value :animal} data
               :when (= id id-val)]
          value)))

;; Garrett Everding suggests the threading macro
(defn garrett-select [data id-val]
  (->> data
       (filter #(= id-val (:number %)))
       first
       :animal))
