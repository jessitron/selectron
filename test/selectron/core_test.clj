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
                            (gen/such-that (complement (keys hard-coded-data))))]
                (is (nil? (select-fn hard-coded-data input)))))

(defspec first-instinct 10
  (select-spec-positive
    (fn [data id-val]
        (:animal (first (filter #(= id-val (:number %)) data))))))


(deftest a-test
  (testing "spildrazil"
    (is (= 1 1))))
