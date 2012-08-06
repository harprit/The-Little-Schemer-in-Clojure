(ns the-little-schemer.preparation)

(defn atom?
  [x]
  (not (coll? x)))
