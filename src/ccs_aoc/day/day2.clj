(ns ccs_aoc.day.day2
  (:require
   [clojure.string :as str]
   [clojure.core.match :refer [match]]))

(def inputFile "input/day2.txt")

(defn readCmds
  []
  (map #(identity [(first %) (Integer/parseInt (second %))])
       (map #(str/split % #" ")
            (str/split-lines
             (slurp inputFile)))))

;; Part 1
(defn computePosition
  [cmds]
  (loop [cmds cmds x 0 y 0]
    (match [cmds]
      [([] :seq)] [x y]
      [([["forward" dx] & _] :seq)] (recur (drop 1 cmds) (+ x dx) y)
      [([["down" dy] & _] :seq)] (recur (drop 1 cmds) x (+ y dy))
      [([["up" dy] & _] :seq)] (recur (drop 1 cmds) x (- y dy)))))

(apply * (computePosition (readCmds)))

;; Part 2
(defn computePositionAim
  [cmds]
  (loop [cmds cmds x 0 y 0 aim 0]
    (match [cmds]
      [([] :seq)] [x y]
      [([["forward" dx] & _] :seq)] (recur (drop 1 cmds) (+ x dx) (+ y (* dx aim)) aim)
      [([["down" da] & _] :seq)] (recur (drop 1 cmds) x y (+ aim da))
      [([["up" da] & _] :seq)] (recur (drop 1 cmds) x y (- aim da)))))

(apply * (computePositionAim (readCmds)))
