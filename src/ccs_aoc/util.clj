(ns ccs-aoc.util
  (:require
   [clojure.string :as str]))

(defn read-lines [file]
  (str/split-lines (slurp file)))

(defn parse-int [x]
  (Integer/parseInt x))

(defn parse-line-int 
  ([line] (parse-line-int #"\s+" line))
  ([sep line]
   (->> line
        str/trim
        (#(str/split % sep))
        (mapv parse-int))))

(defn map2d [f xss]
  (for [xs xss]
    (map f xs)))

(defn zip
  ([xs ys] (map list xs ys))
  ([xs ys zs] (map list xs ys zs)))

(defn drop-nth [n coll]
  (keep-indexed #(if (not= %1 n) %2 nil) coll))

(defn in? [x coll]
  (some #{x} coll))

(defn drop-nths [xs coll]
  (keep-indexed #(if (not (in? %1 xs)) %2 nil) coll))

(defn rotate [xs]
  (->> xs
       cycle
       (drop 1)
       (take (count xs))))

(defmacro dbg [x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))

(defn median [xs]
  (let [mid (/ (count xs) 2)]
    (nth (sort xs) mid)))

(defn sum [xs]
  (apply + xs))

(defn abs [x]
  (Math/abs x))
