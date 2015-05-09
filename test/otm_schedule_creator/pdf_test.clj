(ns otm-schedule-creator.pdf-test
  (:require [clojure.test :refer :all]
            [otm-schedule-creator.pdf :refer :all]))


(defn clear-test-result []
  (println "Clearing test result"))

(use-fixtures
 :each
 (fn [f]
   (f)
   (clear-test-result)))

(deftest pdf-creation-test
  (testing "pdf data structure. We don't want to test what it actually outputs."
    (is (->> (create-schedule-representation
              :header "Headers string")
             nil?
             not))))
