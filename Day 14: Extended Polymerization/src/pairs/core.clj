(ns pairs.core
  (:require [clojure.string :as str]))

(def sample (slurp "sample"))
(def input (slurp "input"))

(defn parse-inital [data]
  (reduce (fn [coll pair] (update coll pair (fnil inc 0)))
          {} (partition 2 1 (first (str/split data #"\n\n")))))

(partition 2 1 (first (str/split sample #"\n\n")))

(parse-inital sample)

(defn parse-insertions [data]
  (map #(map seq (str/split % #" -> "))
       (str/split (second (str/split sample #"\n\n")) #"\n")))

(parse-insertions sample)

(defn insert-instruction [old coll [pair [new]]]
  (let [pair1 (list (first pair) new)
        pair2 (list new (second pair))]
    (if (contains? old pair)
      (merge-with + coll {pair1 1 pair2 1})
      coll))
  )


(reduce (partial insert-instruction (parse-inital sample))
        {} (parse-insertions sample))


(defn apply-instructions [instructions data]
  (reduce (partial insert-instruction data)
        {} instructions))



(reduce (partial insert-instruction
                 (reduce (partial insert-instruction (parse-inital sample))
                         {} (parse-insertions sample)))
        {} (parse-insertions sample))

(let [result (-> (reduce (fn [coll [key val]]
                           (-> coll
                               (update (first key) (fnil #(+ val %) 0))
                               (update (second key) (fnil #(+ val %) 0))))
                         {} ((apply comp (repeat 10 (partial apply-instructions (parse-insertions sample)))) (parse-inital sample)))
                 (update (first (first (str/split sample #"\n\n"))) inc)
                 (update (last (first (str/split sample #"\n\n"))) inc)
                 )]
  (- (/ (apply max (vals result)) 2) (/ (apply min (vals result)) 2)))
