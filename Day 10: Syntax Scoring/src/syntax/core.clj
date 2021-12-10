(ns sytanx.core
  (:require [clojure.string :as str]))

(def sample (str/split (slurp "sample") #"\n"))
(def input (str/split (slurp "input") #"\n"))

(defn opening? [char]
  (some #(= char %) [\( \{ \[ \<]))

(defn closing? [char]
  (some #(= char %) [\) \} \] \>]))

(defn pair? [opening closing]
  (case opening
    \( (= closing \))
    \[ (= closing \])
    \{ (= closing \})
    \< (= closing \>)
    false))

(defn opp [char]
  (case char
    \( \)
    \[ \]
    \{ \}
    \< \>
    \) \(
    \] \[
    \} \{
    \> \<
    "tf"))

(defn score-part1 [char]
  (case char
    \) 3
    \] 57
    \} 1197
    \> 25137))

;; Part 1
(defn part1 [data]
  (let [stack (reduce (fn f [stack paren]
                        (if (or (opening? paren) (empty? stack))
                          (conj stack paren)
                          (if (pair? (first stack) paren)
                            (rest stack)
                            (do
                              (println (format "Expected %s, but found %s instead." (opp (first stack)) paren))
                              (reduced paren)))))
                      () data)]
    (if (char? stack)
      (score-part1 stack)
      0)))

(println (reduce + (map part1 input)))

;; Part 2
(defn score-part2 [char]
  (case char
    \) 1
    \] 2
    \} 3
    \> 4))

(defn middle-value [vect]
  (when-not (empty? vect)
    (nth vect (quot (count vect) 2))))

(defn part2 [data]
  (let [stack (reduce (fn f [stack paren]
                        (if (or (opening? paren) (empty? stack))
                          (conj stack paren)
                          (if (pair? (first stack) paren)
                            (rest stack)
                            (reduced ()))))
                      () data)]
    stack))

(println (middle-value (sort (filter #(not (zero? %))
        (map #(reduce (fn [sum next] (+ (score-part2 next) (* 5 sum))) 0 %)
             (map #(map opp %) (map part2 input)))))))
