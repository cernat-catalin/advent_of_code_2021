(ns ccs-aoc.day.day4
  (:require
   [ccs-aoc.util :refer [parseLineInt readLines map2d zip drop-nths]]
   [clojure.core.match :refer [match]]))

(defn parseBoards [lines]
  (loop [xs lines boards []]
    (if (= 0 (count xs))
      boards
      (recur
       (drop 6 xs)
       (conj boards (map parseLineInt (take 5 xs)))))))

(defn parseInput []
  (->> (vector numbers boards)
       (let [lines (readLines "input/day4.txt")
             numbers (parseLineInt #"," (first lines))
             boards (parseBoards (drop 2 lines))])))

(defn sumBoard [board]
  (match [board]
    [([] :seq)] 0
    [([xs & r] :seq)] (+ (reduce + (filter #(not (= -1 %)) xs)) (sumBoard r))))

(defn checkLine [xs]
  (= 5 (count (filter #(= -1 %) xs))))

(defn checkBoard [board]
  (let [boardT (apply mapv vector board)]
    (not
     (nil?
      (or
       (some true? (map checkLine board))
       (some true? (map checkLine boardT)))))))

(defn markBoard [x board]
  (map2d #(if (= % x) -1 %) board))

(defn part1 []
  (let [input (parseInput)
        numbers (first input)
        boards (second input)] (loop [boards boards numbers numbers]
                                 (let [x (first numbers)
                                       markedBoards (map #(markBoard x %) boards)
                                       candidates (zip (map checkBoard markedBoards) markedBoards)
                                       canditate (filter #(first %) candidates)]
                                   (if (not (= 0 (count canditate)))
                                     (* x (sumBoard (second (first canditate))))
                                     (recur markedBoards (drop 1 numbers)))))))

(defn part2 []
  (let [input (parseInput)
        numbers (first input)
        boards (second input)] (last (loop [boards boards numbers numbers wins []]
                                       (let [x (first numbers)
                                             markedBoards (map #(markBoard x %) boards)
                                             candidates (zip (map checkBoard markedBoards) (range) markedBoards)
                                             canditate (filter #(first %) candidates)]
                                         (if (empty? numbers) wins
                                             (if (not (= 0 (count canditate)))
                                               (recur (drop-nths (map second canditate) markedBoards)
                                                      (drop 1 numbers)
                                                      (concat wins
                                                              (map #(* x (sumBoard %)) (map #(nth % 2) canditate))))
                                               (recur markedBoards
                                                      (drop 1 numbers)
                                                      wins))))))))

