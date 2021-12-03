(ns dive.core
  (:require [clojure.string :as str]))

(defn parse [text]
  (let [[x y] (str/split text #" ")]
   [x (Integer/parseInt y)]))


(def sample (map parse (str/split (slurp "sample") #"\n")))

(def input (map parse (str/split (slurp "input") #"\n")))


;; Part 1

(defn move [pos [direction x]]
  (case direction
    "down" (update pos :depth + x)
    "up" (update pos :depth - x)
    "forward" (update pos :horizontal + x)))

(defn part1 [text]
  (reduce * (vals (reduce move {:depth 0 :horizontal 0} text))))

;; (part1 sample)

(part1 input)


;; Part 2

(defn move2 [pos [direction x]]
  (case direction
    "down" (update pos :aim + x)
    "up" (update pos :aim - x)
    "forward" (update (update pos :horizontal + x) :depth + (* (:aim pos) x))))

(defn part2 [text]
  (reduce * (vals (dissoc (reduce move2 {:depth 0 :horizontal 0 :aim 0} text) :aim))))

;; (part2 sample)

(part2 input)
