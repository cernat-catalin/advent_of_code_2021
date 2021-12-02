(ns ccs_aoc.day.day1
  (:gen-class)
  (:require
   [clojure.string :as str]
   [clojure.core.match :refer [match]]))

(def inputFile "input/day1.txt")

(defn readNumbers
  []
  (map #(Integer/parseInt %)
       (str/split-lines
        (slurp inputFile))))

;; Part 1
(defn countIncreasing
  [xs]
  (reduce (fn [acc x] (if x (inc acc) acc)) 0
          (map #(< (first %) (second %))
               (map vector xs (drop 1 xs)))))

(countIncreasing (readNumbers))

;; Part 2
(defn group
  [xs]
  (loop [xs xs ys []]
    (match [xs]
      [([a b c] :seq)] (concat ys [(+ a (+ b c))])
      [([a b c & _] :seq)] (recur (drop 1 xs)
                                  (concat ys [(+ a (+ b c))])))))
(countIncreasing (group (readNumbers)))
