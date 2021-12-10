(ns ccs-aoc.day.day2
  (:require
   [clojure.string :as str]
   [clojure.core.match :refer [match]]))

(def inputFile "input/day2.txt")

(defn readCmds []
  (->> inputFile
       slurp
       str/split-lines
       (map #(str/split % #" "))
       (map #(identity [(first %) (Integer/parseInt (second %))]))))

;; Part 1
(defn computePosition
  [cmds]
  (loop [cmds cmds x 0 y 0]
    (match [cmds]
      [([] :seq)] [x y]
      [([["forward" dx] & _] :seq)] (recur (drop 1 cmds) (+ x dx) y)
      [([["down" dy] & _] :seq)] (recur (drop 1 cmds) x (+ y dy))
      [([["up" dy] & _] :seq)] (recur (drop 1 cmds) x (- y dy)))))

(defn part1 []
  (apply * (computePosition (readCmds))))

;; Part 2
(defn computePositionAim
  [cmds]
  (loop [cmds cmds x 0 y 0 aim 0]
    (match [cmds]
      [([] :seq)] [x y]
      [([["forward" dx] & _] :seq)] (recur (drop 1 cmds) (+ x dx) (+ y (* dx aim)) aim)
      [([["down" da] & _] :seq)] (recur (drop 1 cmds) x y (+ aim da))
      [([["up" da] & _] :seq)] (recur (drop 1 cmds) x y (- aim da)))))

(defn part2 []
  (apply * (computePositionAim (readCmds))))
