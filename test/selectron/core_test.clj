(ns selectron.core-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer [defspec]]
            [selectron.spec-helper :refer [select-spec-positive select-spec-negative]]
            [selectron.core :refer :all]
            [selectron.datalog]
            [schema.test]))

(defspec first-instinct 10
  (select-spec-positive simple-select))
;; I wish there were an "and" method to compose properties
(defspec first-instinct-neg 10
  (select-spec-negative simple-select))

(def specific-my-select #(my-select :animal %1 :number = %2))
;; "I want the animal from DATA given that number = ID"
(defspec my-select-pos 10
  (select-spec-positive specific-my-select))
(defspec my-select-neg 10
  (select-spec-negative specific-my-select))


(defspec datalog-one 10
  (select-spec-positive selectron.datalog/datalog-select))
