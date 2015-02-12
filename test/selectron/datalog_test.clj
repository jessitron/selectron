(ns selectron.datalog-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer [defspec]]
            [selectron.spec-helper :refer [select-spec-positive select-spec-negative]]
            [selectron.datalog]
            [schema.test]))

(defspec datalog-one 10
  (select-spec-positive selectron.datalog/datalog-select))

(defspec datalog-neg 10
  (select-spec-negative selectron.datalog/datalog-select))
