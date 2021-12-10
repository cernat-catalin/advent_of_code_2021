(ns ccs-aoc.day.day6
  (:require
   [ccs-aoc.util :refer [readLines parseLineInt rotate]]))

(defn parseInput []
  (->> (readLines "input/day6.txt")
       first
       (parseLineInt #",")
       (reduce #(update %1 %2 inc) freq)
       (let [freq [0 0 0 0 0 0 0 0 0]])))

(defn step
  ([xs]
   (let [rot (vec (rotate xs))]
     (update rot 6 #(+ % (nth rot 8)))))
  ([xs n]
   (nth (iterate step xs) n)))

(defn part1 []
  (apply + (step (parseInput) 80)))

(defn part2 []
  (apply + (step (parseInput) 256)))
