(ns ccs-aoc.util
  (:require
   [clojure.string :as str]))

(defn readLines [file]
  (str/split-lines (slurp file)))

(defn parseLineInt
  ([line] (parseLineInt #"\s+" line))
  ([sep line]
   (->> line
        str/trim
        (#(str/split % sep))
        (map #(Integer/parseInt %)))))

(defn map2d [f xss]
  (for [xs xss]
    (map f xs)))

(defn zip [xs ys]
  (map list xs ys))

(defn zip3 [xs ys zs]
  (map list xs ys zs))
