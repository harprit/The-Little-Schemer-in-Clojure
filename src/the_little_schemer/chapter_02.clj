(ns the-little-schemer.chapter-02
  (:use the-little-schemer.preparation))

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
