(ns selectron.spec-helper
  (:require [clojure.test :refer :all]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [schema.core :as s]
            [schema.test]))

(use-fixtures :once schema.test/validate-schemas)

(def hard-coded-data [{:number 4 :animal "Raccoon"}
                      {:number 8 :animal "Tarantula" }
                      {:number 2 :animal "Lemur"}])

(def Animal (s/named s/Str "animal name"))
(def ID (s/named s/Num "animal ID"))
(def AnimalData [{:number ID :animal Animal}])

(s/defn select-spec-positive [select-fn :- (s/=> Animal AnimalData ID)]
 (prop/for-all [rec (gen/elements hard-coded-data)]
      (let [input  (:number rec)
            output (:animal rec)]
        (is (= output (select-fn hard-coded-data input))))) )

(defn select-spec-negative [select-fn]
  (prop/for-all [input (->> gen/int
                            (gen/such-that (complement (set (map :number hard-coded-data)))))]
                (is (nil? (select-fn hard-coded-data input)))))
