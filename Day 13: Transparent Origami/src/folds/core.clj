(ns folds.core
  (:require [clojure.string :as str]))

(def sample (slurp "sample"))

(def input (slurp "input"))

(defn parse-folds [data]
  (let [folds (str/split (second (str/split data #"\n\n")) #"\n")]
    (map (fn [[axis num]] [(last axis) (Integer/parseInt num)])
         (map #(str/split % #"=") folds))))

(defn parse-points [data]
  (map (fn [pair] (map #(Integer/parseInt %) (str/split pair #",")))
       (str/split (first (str/split data #"\n\n")) #"\n")))

(defn fold [[dir line] point]
  (case dir
    \x [(- line (Math/abs (- x line))) y]
    \y [x (- line (Math/abs (- y line)))]))


;; Part 1
(defn part1 [data]
  (let [folds (parse-folds data)
        points (parse-points data)]
    (count (set (map #(fold (first folds) %) points)))))

(println (part1 input))


;; Part 2
(defn apply-folds [data]
  (loop [folds (parse-folds data)
         points (parse-points data)]
    (if (empty? folds)
      (distinct points)
      (recur (rest folds) (map #(fold (first folds) %) points)))))

(defn make-grid [points]
  (for [y (range (+ 1 (apply max (map second points))))]
    (conj (for [x (range (+ 1 (apply max (map first points))))]
      (if (some #(= [x y] %) points)
        "#"
        " ")) "\n")))

(defn part2 [data]
 (print (apply str (flatten (make-grid (apply-folds input))))))

(println (part2 input))
