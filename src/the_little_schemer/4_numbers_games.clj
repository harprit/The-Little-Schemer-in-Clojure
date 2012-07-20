(ns the-little-schemer.4-numbers-games
  (:use the-little-schemer.0-preparation))

(defn o+
  [n m]
  (cond
    (zero? m) n
    :else (inc (o+ n (dec m)))))

(defn o-
  [n m]
  (cond
    (zero? m) n
    :else (dec (o- n (dec m)))))

(defn addtup
  [tup]
  (cond
    (empty? tup) 0
    :else (o+ (first tup)
               (addtup (rest tup)))))

(defn mul
  [n m]
  (cond
    (zero? m) 0
    :else (o+ n
               (mul n (dec m)))))

(defn tup+
  [tup1 tup2]
  (cond
    (empty? tup1) tup2
    (empty? tup2) tup1
    :else (cons (o+ (first tup1) (first tup2))
                (tup+ (rest tup1) (rest tup2)))))

(defn gt?
  [n m]
  (cond
    (zero? n) false
    (zero? m) true
    :else (gt? (dec n) (dec m))))

(defn lt?
  [n m]
  (cond
    (zero? m) false
    (zero? n) true
    :else (lt? (dec n) (dec m))))

(defn o=
  [n m]
  (cond
    (gt? n m) false
    (lt? n m) false
    :else true))

(defn exp
  [n m]
  (cond
    (zero? m) 1
    :else (mul n (exp n (dec m)))))

(defn quo
  [n m]
  (cond
    (lt? n m) 0
    :else (inc (quo (o- n m) m))))

(defn length
  [lat]
  (cond
    (empty? lat) 0
    :else (inc (length (rest lat)))))

(defn pick
  [n lat]
  (cond
    (zero? (dec n)) (first lat)
    :else (pick (dec n) (rest lat))))

(defn rempick
  [n lat]
  (cond
    (zero? (dec n)) (rest lat)
    :else (cons (first lat)
                (rempick (dec n) (rest lat)))))

(defn no-nums
  [lat]
  (cond
    (empty? lat) '()
    (number? (first lat)) (no-nums (rest lat))
    :else (cons (first lat)
                (no-nums (rest lat)))))

(defn all-nums
  [lat]
  (cond
    (empty? lat) '()
    (number? (first lat)) (cons (first lat)
                                (all-nums (rest lat)))
    :else (all-nums (rest lat))))

(defn eqan?
  [a1 a2]
  (cond
    (and (number? a1) (number? a2)) (o= a1 a2)
    (or (number? a1) (number? a2)) false
    :else (= a1 a2)))

(defn occur
  [a lat]
  (cond
    (empty? lat) 0
    (= (first lat) a) (inc (occur a (rest lat)))
    :else (occur a (rest lat))))

(defn one?
  [n]
  (o= n 1))

(defn rempick2
  [n lat]
  (cond
    (one? n) (rest lat)
    :else (cons (first lat)
                (rempick (dec n) (rest lat)))))
