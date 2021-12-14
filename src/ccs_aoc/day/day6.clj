(ns ccs-aoc.day.day6
  (:require
   [ccs-aoc.util :as u]))

(defn parse-input []
  (->> (u/read-lines "input/day6.txt")
       first
       (u/parse-line-int #",")
       (reduce #(update %1 %2 inc) freq)
       (let [freq [0 0 0 0 0 0 0 0 0]])))

(defn step
  ([xs]
   (let [rot (vec (u/rotate xs))]
     (update rot 6 #(+ % (nth rot 8)))))
  ([xs n]
   (nth (iterate step xs) n)))

(defn part1 []
  (apply + (step (parse-input) 80)))

(defn part2 []
  (apply + (step (parse-input) 256)))
