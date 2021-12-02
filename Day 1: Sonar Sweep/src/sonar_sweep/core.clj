(ns sonar-sweep.core
  (:require [clojure.string :as str]))


(def sample (map #(Integer/parseInt %) (str/split (slurp "sample") #"\n")))

(def input (map #(Integer/parseInt %) (str/split (slurp "input") #"\n")))

(defn change
  [x y]
  (if (> y x)
    1
    0))

;; Part 1

(defn part1
  [input]
(reduce + (map change (drop-last input) (rest input))))

;; (part1 sample)

(part1 input)

;; Part 2

(defn part2
  [input]
  (part1 (map #(reduce + %) (partition 3 1 input))))

;; (part2 sample)

(part2 input)
