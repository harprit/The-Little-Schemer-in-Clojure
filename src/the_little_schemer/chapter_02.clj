(ns the-little-schemer.2-do-it-and-again
  (:use the-little-schemer.0-preparation))

(defn lat?
  [ls]
  (cond
    (empty? ls) true
    (atom? (first ls)) (lat? (rest ls))
    :else false))

(defn member?
  [a lat]
  (cond
    (empty? lat) false
    :else (or (= (first lat) a)
              (member? a (rest lat)))))
