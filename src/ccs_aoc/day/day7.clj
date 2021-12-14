(ns ccs-aoc.day.day7
  (:require
   [ccs-aoc.util :as u]))

(defn parse-input []
  (->> (u/read-lines "input/day7.txt")
       first
       (u/parse-line-int #",")))

(defn part1 []
  (let [xs (parse-input)
        mid (u/median xs)]
    (apply + (map
              #(Math/abs (- % mid))
              xs))))

(defn cost [xs y]
  (let [cost' (fn [x]
                (let [d (u/abs (- x y))]
                  (/ (* d (+ d 1)) 2)))]
    (u/sum (map cost' xs))))

(defn part2 []
  (let [xs (parse-input)
        min' (apply min xs)
        max' (apply max xs)
        ys (range min' max')]
    (apply min
           (map #(cost xs %) ys))))
