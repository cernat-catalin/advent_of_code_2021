(ns ccs-aoc.day.days-test
  (:gen-class)
  (:require
   [clojure.test :refer [deftest testing is]]
   [ccs-aoc.day.day1 :as day1]
   [ccs-aoc.day.day2 :as day2]
   [ccs-aoc.day.day3 :as day3]
   [ccs-aoc.day.day4 :as day4]
   [ccs-aoc.day.day5 :as day5]
   [ccs-aoc.day.day6 :as day6]))

(deftest day1-test
  (testing "Day 1 Part 1"
    (is (= 1390 (day1/part1))))
  (testing "Day 1 Part 2"
    (is (= 1457 (day1/part2)))))

(deftest day2-test
  (testing "Day 2 Part 1"
    (is (= 1250395 (day2/part1))))
  (testing "Day 2 Part 2"
    (is (= 1451210346 (day2/part2)))))

(deftest day3-test
  (testing "Day 3 Part 1"
    (is (= 4174964 (day3/part1))))
  (testing "Day 3 Part 2"
    (is (= 4474944 (day3/part2)))))

(deftest day4-test
  (testing "Day 4 Part 1"
    (is (= 44736 (day4/part1))))
  (testing "Day 4 Part 2"
    (is (= 1827 (day4/part2)))))

(deftest day5-test
  (testing "Day 5 Part 1"
    (is (= 6311 (day5/part1))))
  (testing "Day 5 Part 2"
    (is (= 19929 (day5/part2)))))

(deftest day6-test
  (testing "Day 6 Part 1"
    (is (= 350917 (day6/part1))))
  (testing "Day 6 Part 2"
    (is (= 1592918715629 (day6/part2)))))
