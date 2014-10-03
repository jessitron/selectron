(ns selectron.core-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [selectron.core :refer :all]
            [selectron.datalog]))


(def hard-coded-data [{:number 4 :animal "Raccoon"}
                      {:number 8 :animal "Tarantula" }
                      {:number 2 :animal "Lemur"}])

(defn select-spec-positive [select-fn]
 (prop/for-all [rec (gen/elements hard-coded-data)]
      (let [input  (:number rec)
            output (:animal rec)]
        (is (= output (select-fn hard-coded-data input))))) )

(defn select-spec-negative [select-fn]
  (prop/for-all [input (->> gen/int
                            (gen/such-that (complement (set (map :number hard-coded-data)))))]
                (is (nil? (select-fn hard-coded-data input)))))

(defspec datalog-one 10
  (select-spec-positive selectron.datalog/datalog-select))


(defspec first-instinct 10
  (select-spec-positive simple-select))
;; I wish there were an "and" method to compose properties
(defspec first-instinct-neg 10
  (select-spec-negative simple-select))

(def specific-my-select #(my-select :animal %1 :number = %2))
(defspec my-select-pos 10
  (select-spec-positive specific-my-select))
(defspec my-select-neg 10
  (select-spec-negative specific-my-select))


(deftest a-test
  (testing "spildrazil"
    (is (= 1 1))))
