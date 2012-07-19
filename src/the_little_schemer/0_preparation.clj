(ns the-little-schemer.0-preparation)

(defn atom?
  [x]
  (not (coll? x)))
