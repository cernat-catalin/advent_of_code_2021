(ns ccs-aoc.day.day3
  (:require
   [clojure.string :as str]
   [clojure.core.match :refer [match]]))

(def inputFile "input/day3.txt")

(defn readLines []
  (->> inputFile
       slurp
       str/split-lines
       (map #(str/split % #"")) ; split string into characters
       (map (partial map #(Integer/parseInt %))))) ; parse as int

; Part 1
(defn compl [lines] ; 2 ^ (#bits in numbers)
  (->> lines
       first
       count
       (bit-shift-left 1)))

(defn binaryToDecimal [xs]
  (->> xs
       reverse
       (#(map vector % (range)))
       (reduce (fn [acc x] (+ acc (bit-shift-left (first x) (second x)))) 0)))

(defn gammaRate [lines]
  (->> lines
       (apply mapv vector) ; transpose matrix
       (map (partial apply +)) ; sum rows
       (map (partial + m)) ; subtract line count / 2
       (map #(if (> % 0) 1 0)) ; get majority bit
       binaryToDecimal
       (let [n (count lines)
             m (- (/ n 2))])))

(defn part1 []
  (let [lines (readLines)
        c (compl lines)
        gamma (gammaRate lines)
        epsilon (- c (+ 1 gamma))]
    (* gamma epsilon)))

; Part 2
(defn majority [xs i b] ; check if bit `b` is in majority for bits in pos `i`
  (->> xs
       (map #(nth % i))
       (filter #(= b %))
       count
       (#(if (<= n %) true false))
       (let [n (/ (count xs) 2)])))

(defn pickNumbers [lines major]
  (->> lines
       (#(loop [xs % i 0]
           (match [xs]
             [([x] :seq)] x
             :else (recur (match [major]
                            [true]  (if (majority xs i 1) (pick xs i 1) (pick xs i 0))
                            [false] (if (not (majority xs i 1)) (pick xs i 1) (pick xs i 0)))
                          (+ i 1)))))
       binaryToDecimal
       (let [pick (fn [xs i b] (filter #(= b (nth % i)) xs))]))) ; pick numbers with bit `b` in pos `i`

(defn part2 []
  (let [lines (readLines)
        generator (pickNumbers lines true)
        scrubber (pickNumbers lines false)]
    (* generator scrubber)))

