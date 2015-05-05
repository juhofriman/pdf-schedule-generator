(ns otm-schedule-creator.clocktime-test
  (:require [clojure.test :refer :all]
            [otm-schedule-creator.clocktime :refer :all]))

(deftest clocktime-transformation-tests

  (testing "Testing str-to-clocktime correct values"
    (is (= [12 0] (str-to-clocktime "12:00")))
    (is (= [0 0] (str-to-clocktime "00:00")))
    (is (= [1 15] (str-to-clocktime "01:15")))
    (is (= [1 9] (str-to-clocktime "01:09")))
    (is (= [23 23] (str-to-clocktime "23:23")))
    (is (= [12 55] (str-to-clocktime "12:55"))))

  (testing "Testing str-to-clocktime incorrect values"
    (is (thrown? IllegalArgumentException (str-to-clocktime "25:33")))
    (is (thrown? IllegalArgumentException (str-to-clocktime "400:00")))
    (is (thrown? IllegalArgumentException (str-to-clocktime "10000:61"))))

  (testing "Testing clocktime-to-str"
    (is (= "12:00" (clocktime-to-str [12 0])))
    (is (= "01:01" (clocktime-to-str [1 1])))
    (is (= "23:21" (clocktime-to-str [23 21])))))

(deftest clocktime-interval-tests

  (testing "test adding interval minutes"
    (is (= (str-to-clocktime "13:00")
           (clocktime-add (str-to-clocktime "13:00"))))
    (is (= (str-to-clocktime "13:10")
           (clocktime-add (str-to-clocktime "13:00") :minutes 10)))
    (is (= (str-to-clocktime "00:55")
           (clocktime-add (str-to-clocktime "00:00") :minutes 55)))
    (is (= (str-to-clocktime "00:09")
           (clocktime-add (str-to-clocktime "00:05") :minutes 4))))

  (testing "test adding interval hours"
    (is (= (str-to-clocktime "14:00")
           (clocktime-add (str-to-clocktime "13:00") :hours 1)))
    (is (= (str-to-clocktime "6:00")
           (clocktime-add (str-to-clocktime "1:00") :hours 5)))
    (is (= (str-to-clocktime "12:00")
           (clocktime-add (str-to-clocktime "10:00") :hours 2))))

  (testing "testing adding hours and minutes"
    (is (= (str-to-clocktime "14:05")
           (clocktime-add (str-to-clocktime "13:00") :hours 1 :minutes 5))))

  ; This must be implemented
  ;(testing "Testing minute overflow"
  ;  (is (= (str-to-clocktime "14:05")
  ;         (clocktime-add (str-to-clocktime "13:00") :minutes 65))))
  )

