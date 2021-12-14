(ns ccs-aoc.day.day5
  (:require
   [ccs-aoc.util :as u]
   [clojure.string :as str]))

(defn parse-line [line]
  (let [parts (str/split line #" -> ")
        parsePoint #(map u/parse-int (str/split % #","))]
    (map parsePoint parts)))

(defn parse-inpput []
  (let [lines (u/read-lines "input/day5.txt")]
    (map parse-line lines)))

(defn add-point [hmap point]
  (if (contains? hmap point)
    (assoc hmap point (inc (get hmap point)))
    (assoc hmap point 1)))

(defn gen-points-between [a b]
  (->> (loop [a a b b points []]
         (if (= a b)
           (conj points a)
           (recur (map + dir a) b (conj points a))))
       (let [comp-dir (fn [x0 x1]
                        (if (= x0 x1)
                          0
                          (if (< x0 x1) 1 -1)))
             dirX (comp-dir (first a) (first b))
             dirY (comp-dir (second a) (second b))
             dir [dirX dirY]])))

(defn is-parallel-to-axes [a b]
  (or (= (first a) (first b))
      (= (second a) (second b))))

(defn part1 []
  (->>
   (parse-inpput)
   (filter #(is-parallel-to-axes (first %) (second %)))
   (mapcat #(gen-points-between (first %) (second %)))
   (reduce add-point {})
   (#(select-keys % (for [[k v] % :when (< 1 v)] k)))
   count))

(defn part2 []
  (->>
   (parse-inpput)
   (mapcat #(gen-points-between (first %) (second %)))
   (reduce add-point {})
   (#(select-keys % (for [[k v] % :when (< 1 v)] k)))
   count))
