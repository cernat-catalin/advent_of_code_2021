(ns ccs_aoc.day.day1
  (:gen-class)
  (:require
   [clojure.string :as str]
   [clojure.core.match :refer [match]]))

(def inputFile "input/day1.txt")

(defn readNumbers []
  (->> inputFile
       slurp
       str/split-lines
       (map #(Integer/parseInt %))))

;; Part 1
(defn countIncreasing [xs]
  (->> xs
       (#(map vector % (drop 1 %)))
       (map #(< (first %) (second %)))
       (reduce (fn [acc x] (if x (inc acc) acc)) 0)))

(countIncreasing (readNumbers))

;; Part 2
(defn group [xs]
  (loop [xs xs ys []]
    (match [xs]
      [([a b c] :seq)] (concat ys [(+ a (+ b c))])
      [([a b c & _] :seq)] (recur (drop 1 xs)
                                  (concat ys [(+ a (+ b c))])))))

(countIncreasing (group (readNumbers)))
