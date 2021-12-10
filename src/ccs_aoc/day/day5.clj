(ns ccs-aoc.day.day5
  (:require
   [ccs-aoc.util :refer [readLines parseInt]]
   [clojure.string :as str]))

(defn parseLine [line]
  (let [parts (str/split line #" -> ")
        parsePoint #(map parseInt (str/split % #","))]
    (map parsePoint parts)))

(defn parseInput []
  (let [lines (readLines "input/day5.txt")]
    (map parseLine lines)))

(defn addPoint [hmap point]
  (if (contains? hmap point)
    (assoc hmap point (inc (get hmap point)))
    (assoc hmap point 1)))

(defn genPointsBetween [a b]
  (->> (loop [a a b b points []]
         (if (= a b)
           (conj points a)
           (recur (map + dir a) b (conj points a))))
       (let [compDir (fn [x0 x1]
                       (if (= x0 x1)
                         0
                         (if (< x0 x1) 1 -1)))
             dirX (compDir (first a) (first b))
             dirY (compDir (second a) (second b))
             dir [dirX dirY]])))

(defn isParallelToAxes [a b]
  (or (= (first a) (first b))
      (= (second a) (second b))))

(defn part1 []
  (->>
   (parseInput)
   (filter #(isParallelToAxes (first %) (second %)))
   (mapcat #(genPointsBetween (first %) (second %)))
   (reduce addPoint {})
   (#(select-keys % (for [[k v] % :when (< 1 v)] k)))
   count))

(defn part2 []
  (->>
   (parseInput)
   (mapcat #(genPointsBetween (first %) (second %)))
   (reduce addPoint {})
   (#(select-keys % (for [[k v] % :when (< 1 v)] k)))
   count))
