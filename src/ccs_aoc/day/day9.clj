(ns ccs-aoc.day.day9
  (:require
   [ccs-aoc.util :as u]
   [clojure.set :as s]))

(defn parse-input []
  (->> (u/read-lines "input/day9.txt")
       (mapv #(u/parse-line-int #"" %))))

(defn neighbour-coords [pos]
  (map #(mapv + pos %) [[-1 0] [0 1] [1 0] [0 -1]]))

(defn neighbours [pos heights]
  (map #(get-in heights % 9) (neighbour-coords pos)))

(defn low-points [heights]
  (for [[i row] (map-indexed vector heights)
        [j x] (map-indexed vector row)
        :when (every? (partial < x) (neighbours [i j] heights))]
    [i j]))

(defn part1 []
  (let [heights (parse-input)
        points (low-points heights)
        values (map (partial get-in heights) points)
        risks (map inc values)]
    (apply + risks)))

(defn get-basin
  ([start heights] (get-basin #{start} #{} heights))
  ([basin visited heights]
   (if-let [x (first (s/difference basin visited))]
     (recur (into basin
                  (filter #(not= 9 (get-in heights % 9)) (neighbour-coords x)))
            (conj visited x)
            heights)
     basin)))

(defn part2 []
  (let [heights (parse-input)
        points (low-points heights)
        basins (map #(get-basin % heights) points)]
    (->> basins
         (map count)
         sort
         (take-last 3)
         (apply *))))

;(time (part2))
