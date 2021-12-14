(ns ccs-aoc.day.day1
  (:gen-class)
  (:require
   [ccs-aoc.util :as u]))

(defn parse-input []
  (mapcat u/parse-line-int (u/read-lines "input/day1.txt")))

(defn count-increasing [xs]
  (->> xs
       (#(u/zip % (drop 1 %)))
       (map #(< (first %) (second %)))
       (reduce (fn [acc x] (if x (inc acc) acc)) 0)))

(defn part1 []
  (count-increasing (parse-input)))

(defn group [xs]
  (map #(apply + %)
       (u/zip xs (drop 1 xs) (drop 2 xs))))

(defn part2 []
  (count-increasing (group (parse-input))))
