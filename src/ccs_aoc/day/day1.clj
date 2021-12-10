(ns ccs-aoc.day.day1
  (:gen-class)
  (:require
   [ccs-aoc.util :refer [zip parseLineInt readLines]]))

(defn parseInput []
  (mapcat parseLineInt (readLines "input/day1.txt")))

(defn countIncreasing [xs]
  (->> xs
       (#(zip % (drop 1 %)))
       (map #(< (first %) (second %)))
       (reduce (fn [acc x] (if x (inc acc) acc)) 0)))

(defn part1 []
  (countIncreasing (parseInput)))

(defn group [xs]
  (map #(apply + %)
       (zip xs (drop 1 xs) (drop 2 xs))))

(defn part2 []
  (countIncreasing (group (parseInput))))
