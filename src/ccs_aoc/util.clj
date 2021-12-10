(ns ccs-aoc.util
  (:require
   [clojure.string :as str]))

(defn readLines [file]
  (str/split-lines (slurp file)))

(defn parseInt [x]
  (Integer/parseInt x))

(defn parseLineInt
  ([line] (parseLineInt #"\s+" line))
  ([sep line]
   (->> line
        str/trim
        (#(str/split % sep))
        (map parseInt))))

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
       (take (count xs)))
  )

(defmacro dbg [x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))
