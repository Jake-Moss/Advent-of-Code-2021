(ns whales.core
  (:require [clojure.string :as str]))

(def sample (map #(Integer/parseInt %) (str/split (str/trim-newline (slurp "sample")) #",")))
(def input (map #(Integer/parseInt %) (str/split (str/trim-newline (slurp "input")) #",")))

sample

(defn median [coll]
  (let [cnt (count coll)
        sorted (sort coll)
        n (quot cnt 2)]
    (if (odd? cnt)
      (nth sorted n)
      (/ (+ (nth sorted n) (nth sorted (- n 1))) 2))))


;; Part 1
(defn part1 [data]
  (let [x (median data)]
  (reduce + (map #(Math/abs (- x %)) data))))


(println (part1 input))

;; Part 1

(defn part2 [data]
  (apply min (for [y (range (apply min data) (+ 1 (apply max data)))]
               (reduce + (map #(let [delta (Math/abs (- % y))](/ (+ (* delta delta) delta) 2)) data)))))

(println (part2 input))
