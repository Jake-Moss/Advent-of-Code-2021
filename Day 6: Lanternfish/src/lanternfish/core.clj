(ns lanternfish.core
  (:require [clojure.string :as str]))

(def sample (map #(Integer/parseInt %) (str/split (str/trim-newline (slurp "sample")) #",")))
(def input (map #(Integer/parseInt %) (str/split (str/trim-newline (slurp "input")) #",")))

;; Very slow
(defn nth-day [data n]
  ((apply comp (repeat n (fn [data] (reduce (fn [col next]
                                              (if (= next 0)
                                                (conj col 6 8)
                                                (conj col (dec next)))) [] data)))) data))

(defn nth-day-count [data n]
  ((apply comp (repeat n
                       (fn [prev]
                         (reduce (fn [col [key val]]
                                   (if (= key 0)
                                     (assoc col 8 (+ (get col 8 0) val)
                                            6 (+ (get col 6 0) val))
                                     (assoc col (dec key) (+ (get col (dec key) 0) val))))
                                 {} prev))))
   (frequencies data)))

;; Tests
(= (count (nth-day sample 18)) 26)
(= (reduce + (vals (nth-day-count sample 18))) 26)

(= (count (nth-day sample 80)) 5934)
(= (reduce + (vals (nth-day-count sample 80))) 5934)

;; (= (count (nth-day sample 256)) 26984457539)
(= (reduce + (vals (nth-day-count sample 256))) 26984457539)

;; Part 1
(println (count (nth-day input 80)))
(println (reduce + (vals (nth-day-count input 80))))

;; Part 2
;; (println (count (nth-day input 256))) ;; This will take *forever*
(println (reduce + (vals (nth-day-count input 256))))
