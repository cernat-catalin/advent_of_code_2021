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

(defn zip
  ([xs ys] (map list xs ys))
  ([xs ys zs] (map list xs ys zs)))

(defn drop-nth [n coll]
  (keep-indexed #(if (not= %1 n) %2 nil) coll))

(defn in? [x coll]
  (some #{x} coll))

(defn drop-nths [xs coll]
  (keep-indexed #(if (not (in? %1 xs)) %2 nil) coll))

(defmacro dbg [x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))
