(ns the-little-schemer.chapter-07
  (:use the-little-schemer.preparation
        [the-little-schemer.chapter-02 :only (member?)]
        [the-little-schemer.chapter-03 :only (multirember firsts)]))

(defn myset?
  [lat]
  (cond
    (empty? lat) true
    (member? (first lat) (rest lat)) false
    :else (myset? (rest lat))))

(defn makeset
  [lat]
  (cond
    (empty? lat) '()
    :else (cons (first lat)
                (makeset (multirember (first lat) (rest lat))))))

(defn subset?
  [set1 set2]
  (cond
    (empty? set1) true
    :else (and (member? (first set1) set2)
               (subset? (rest set1) set2))))

(defn eqset?
  [set1 set2]
  (and (subset? set1 set2)
       (subset? set2 set1)))

(defn intersect?
  [set1 set2]
  (cond
    (empty? set1) false
    :else (or (member? (first set1) set2)
              (intersect? (rest set1) set2))))

(defn intersect
  [set1 set2]
  (cond
    (empty? set1) '()
    (member? (first set1) set2) (cons (first set1)
                                      (intersect (rest set1) set2))
    :else (intersect (rest set1) set2)))

(defn union
  [set1 set2]
  (cond
    (empty? set1) set2
    (empty? set2) set1
    (member? (first set1) set2) (union (rest set1) set2)
    :else (cons (first set1)
                (union (rest set1) set2))))

(defn intersectall
  [l-set]
  (cond
    (empty? (rest l-set)) (first l-set)
    :else (intersect (first l-set)
                     (intersectall (rest l-set)))))

(defn a-pair?
  [x]
  (cond
    (atom? x) false
    (empty? x) false
    (empty? (rest x)) false
    (empty? (rest (rest x))) true
    :else false))

;; first, second は clojure.core ですでに定義されているため、
;; build だけ定義する。
(defn build
  [a1 a2]
  (cons a1
        (cons a2 '())))

(defn fun?
  [rel]
  (myset? (firsts rel)))

(declare revpair)

(defn revrel
  [rel]
  (cond
    (empty? rel) '()
    :else (cons (revpair (first rel))
                (revrel (rest rel)))))

(defn revpair
  [pair]
  (build (second pair)
         (first pair)))

(declare seconds)

(defn fullfun?
  [fun]
  (myset? (seconds fun)))

(defn seconds
  [ls]
  (cond
    (empty? ls) '()
    :else (cons (second (first ls))
                (seconds (rest ls)))))

(defn one-to-one?
  [fun]
  (fun? (revrel fun)))
