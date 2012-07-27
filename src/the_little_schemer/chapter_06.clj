(ns the-little-schemer.6-shadows
  (:use the-little-schemer.0-preparation
        [the-little-schemer.4-numbers-games :only (o+ mul pow)]))

(defn numbered?
  [aexp]
  (cond
    (atom? aexp) (number? aexp)
    :else (and (numbered? (first aexp))
               (numbered? (first (rest (rest aexp)))))))

(defn value
  [nexp]
  (cond
    (atom? nexp) nexp
    (= (first (rest nexp)) '+) (o+ (value (first nexp))
                                   (value (first (rest (rest nexp)))))
    (= (first (rest nexp)) '*) (mul (value (first nexp))
                                    (value (first (rest (rest nexp)))))
    :else (pow (value (first nexp))
               (value (first (rest (rest nexp)))))))

(defn first-sub-exp
  [aexp]
  (first (rest aexp)))

(defn second-sub-exp
  [aexp]
  (first (rest (rest aexp))))

(defn operator
  [aexp]
  (first aexp))

(defn value2
  [aexp]
  (cond
    (atom? aexp) aexp
    (= (operator aexp) '+) (o+ (value2 (first-sub-exp aexp))
                               (value2 (second-sub-exp aexp)))
    (= (operator aexp) '*) (mul (value2 (first-sub-exp aexp))
                                (value2 (second-sub-exp aexp)))
    :else (pow (value2 (first-sub-exp aexp))
               (value2 (second-sub-exp aexp)))))

(defn sero?
  [n]
  (empty? n))

(defn edd1
  [n]
  (cons '() n))

(defn zub1
  [n]
  (rest n))

(defn edd
  [n m]
  (cond
    (sero? m) n
    :else (edd1 (edd n (zub1 m)))))

(defn zub
  [n m]
  (cond
    (sero? m) n
    :else (zub1 (zub n (zub1 m)))))
