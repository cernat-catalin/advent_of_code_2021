(ns ccs-aoc.day.day2
  (:require
   [clojure.string :as str]
   [clojure.core.match :refer [match]]
   [ccs-aoc.util :as u]))

(defn read-cmds []
  (->> (u/read-lines "input/day2.txt")
       (map #(str/split % #" "))
       (map #(identity [(first %) (u/parse-int (second %))]))))

(defn compute-position
  [cmds]
  (loop [cmds cmds x 0 y 0]
    (match [cmds]
      [([] :seq)] [x y]
      [([["forward" dx] & _] :seq)] (recur (drop 1 cmds) (+ x dx) y)
      [([["down" dy] & _] :seq)] (recur (drop 1 cmds) x (+ y dy))
      [([["up" dy] & _] :seq)] (recur (drop 1 cmds) x (- y dy)))))

(defn part1 []
  (apply * (compute-position (read-cmds))))

(defn compute-position-aim
  [cmds]
  (loop [cmds cmds x 0 y 0 aim 0]
    (match [cmds]
      [([] :seq)] [x y]
      [([["forward" dx] & _] :seq)] (recur (drop 1 cmds) (+ x dx) (+ y (* dx aim)) aim)
      [([["down" da] & _] :seq)] (recur (drop 1 cmds) x y (+ aim da))
      [([["up" da] & _] :seq)] (recur (drop 1 cmds) x y (- aim da)))))

(defn part2 []
  (apply * (compute-position-aim (read-cmds))))
