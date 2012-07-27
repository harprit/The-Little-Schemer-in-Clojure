(ns the-little-schemer.5-it-is-full-of-stars
  (:use the-little-schemer.0-preparation
        [the-little-schemer.4-numbers-games :only (eqan?)]))

(declare eqlist? equal?)

(defn rember*
  [a ls]
  (cond
    (empty? ls) '()
    (atom? (first ls)) (cond
                         (= (first ls) a) (rember* a (rest ls))
                         :else (cons (first ls)
                                     (rember* a (rest ls))))
    :else (cons (rember* a (first ls))
                (rember* a (rest ls)))))

(defn insertR*
  [new old ls]
  (cond
    (empty? ls) '()
    (atom? (first ls)) (cond
                         (= (first ls) old) (cons old
                                                  (cons new
                                                        (insertR* new old (rest ls))))
                         :else (cons (first ls)
                                     (insertR* new old (rest ls))))
    :else (cons (insertR* new old (first ls))
                (insertR* new old (rest ls)))))

(defn occur*
  [a ls]
  (cond
    (empty? ls) 0
    (atom? (first ls)) (cond
                         (= (first ls) a) (inc (occur* a (rest ls)))
                         :else (occur* a (rest ls)))
    :else (+ (occur* a (first ls))
             (occur* a (rest ls)))))

(defn subst*
  [new old ls]
  (cond
    (empty? ls) '()
    (atom? (first ls)) (cond
                         (= (first ls) old) (cons new
                                                  (subst* new old (rest ls)))
                         :else (cons (first ls)
                                     (subst* new old (rest ls))))
    :else (cons (subst* new old (first ls))
                (subst* new old (rest ls)))))

(defn insertL*
  [new old ls]
  (cond
    (empty? ls) '()
    (atom? (first ls)) (cond
                         (= (first ls) old) (cons new
                                                  (cons old
                                                        (insertL* new old (rest ls))))
                         :else (cons (first ls) (insertL* new old (rest ls))))
    :else (cons (insertL* new old (first ls))
                (insertL* new old (rest ls)))))

(defn member*
  [a ls]
  (cond
    (empty? ls) false
    (atom? (first ls)) (or (= (first ls) a)
                           (member* a (rest ls)))
    :else (or (member* a (first ls))
              (member* a (rest ls)))))

(defn leftmost
  [ls]
  (cond
    (atom? (first ls)) (first ls)
    :else (leftmost (first ls))))

(defn eqlist?
  [l1 l2]
  (cond
    (and (empty? l1) (empty? l2)) true
    (or (empty? l1) (empty? l2)) false
    :else (and (equal? (first l1) (first l2))
               (eqlist? (rest l1) (rest l2)))))

;; Clojure では = を使えばよい
(defn equal?
  [s1 s2]
  (cond
    (and (atom? s1) (atom? s2)) (eqan? s1 s2)
    (or (atom? s1) (atom? s2)) false
    :else (eqlist? s1 s2)))

(defn rember
  [s l]
  (cond
    (empty? l) '()
    (equal? (first l) s) (rest l)
    :else (cons (first l)
                (rember s (rest l)))))
