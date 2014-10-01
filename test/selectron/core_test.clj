(ns selectron.core-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [selectron.core :refer :all]))


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

(defn simple-select [data id-val]
  (:animal (first (filter #(= id-val (:number %)) data))))

(defspec first-instinct 10
  (select-spec-positive simple-select))

(defspec first-instinct-neg 10
  (select-spec-negative simple-select))


(deftest a-test
  (testing "spildrazil"
    (is (= 1 1))))
