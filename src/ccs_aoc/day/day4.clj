(ns ccs-aoc.day.day4
  (:require
   [ccs-aoc.util :as u]
   [clojure.core.match :refer [match]]))

(defn parse-board [lines]
  (loop [xs lines boards []]
    (if (= 0 (count xs))
      boards
      (recur
       (drop 6 xs)
       (conj boards (map u/parse-line-int (take 5 xs)))))))

(defn parse-input []
  (->> (vector numbers boards)
       (let [lines (u/read-lines "input/day4.txt")
             numbers (u/parse-line-int #"," (first lines))
             boards (parse-board (drop 2 lines))])))

(defn sum-board [board]
  (match [board]
    [([] :seq)] 0
    [([xs & r] :seq)] (+ (reduce + (filter #(not (= -1 %)) xs)) (sum-board r))))

(defn check-line [xs]
  (= 5 (count (filter #(= -1 %) xs))))

(defn check-board [board]
  (let [boardT (apply mapv vector board)]
    (not
     (nil?
      (or
       (some true? (map check-line board))
       (some true? (map check-line boardT)))))))

(defn mark-board [x board]
  (u/map2d #(if (= % x) -1 %) board))

(defn part1 []
  (let [input (parse-input)
        numbers (first input)
        boards (second input)] (loop [boards boards numbers numbers]
                                 (let [x (first numbers)
                                       markedBoards (map #(mark-board x %) boards)
                                       candidates (u/zip (map check-board markedBoards) markedBoards)
                                       canditate (filter #(first %) candidates)]
                                   (if (not (= 0 (count canditate)))
                                     (* x (sum-board (second (first canditate))))
                                     (recur markedBoards (drop 1 numbers)))))))

(defn part2 []
  (let [input (parse-input)
        numbers (first input)
        boards (second input)] (last (loop [boards boards numbers numbers wins []]
                                       (let [x (first numbers)
                                             markedBoards (map #(mark-board x %) boards)
                                             candidates (u/zip (map check-board markedBoards) (range) markedBoards)
                                             canditate (filter #(first %) candidates)]
                                         (if (empty? numbers) wins
                                             (if (not (= 0 (count canditate)))
                                               (recur (u/drop-nths (map second canditate) markedBoards)
                                                      (drop 1 numbers)
                                                      (concat wins
                                                              (map #(* x (sum-board %)) (map #(nth % 2) canditate))))
                                               (recur markedBoards
                                                      (drop 1 numbers)
                                                      wins))))))))

