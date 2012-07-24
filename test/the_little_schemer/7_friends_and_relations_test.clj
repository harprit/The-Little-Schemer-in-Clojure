(ns the-little-schemer.7-friends-and-relations-test
  (:use clojure.test
        the-little-schemer.7-friends-and-relations))

(deftest test-myset?
  (testing "ラットが空の場合、 true を返す"
    (is (myset? '())))

  (testing "ラット内のアトムに重複がない場合、 true を返す"
    (are [lat]
      (myset? lat)
      '(apple peaches pears plums)
      '(foo bar piyo)))

  (testing "ラット内のアトムに重複がある場合、 false を返す"
    (are [lat]
      (not (myset? lat))
      '(apple peaches apple plum)
      '(apple 3 pear 4 9 apple 3 4))))


(deftest test-makeset
  (testing "ラットが空の場合、空のリストを返す"
    (is (makeset '()) '()))

  (testing "ラット内のアトムに重複がない場合、与えられたラットと同じラットを返す"
    (are [lat]
      (= (makeset lat) lat)
      '(apple peaches pears plums)
      '(foo bar piyo)))

  (testing "ラット内のアトムに重複がある場合、重複したアトムを取り除いた、別のラットを返す"
    (are [lat expected]
      (= (makeset lat) expected)
      '(apple peaches apple plum) '(apple peaches plum)
      '(apple 3 pear 4 9 apple 3 4) '(apple 3 pear 4 9))))


(deftest test-subset?
  (testing "最初のセットが空の場合、true を返す"
    (are [set2]
      (subset? '() set2)
      '()
      '(x)))

  (testing "set1 内に含まれるアトムが全て set2 内に含まれる場合、true を返す"
    (are [set1 set2]
      (subset? set1 set2)
      '(5 chicken wings) '(5 hamburgers 2 pieces fried chicken and light duckling wings)))

  (testing "set1 内に含まれるアトムが一つでも set2 内に含まれない場合、false を返す"
    (are [set1 set2]
      (not (subset? set1 set2))
      '(4 pounds of horseradish) '(four pounds chicken and5 ounces horseradish))))


(deftest test-eqset?
  (testing "両方のセットが空の場合、true を返す"
    (is (eqset? '() '())))

  (testing "set1 と set2 内に含まれるアトムの数と値が同じ場合、true を返す"
    (are [set1 set2]
      (eqset? set1 set2)
      '(6 large chickens with wings) '(6 chickens with large wings)))

  (testing "set1 と set2 内に含まれるアトムの数と値が異なる場合、false を返す"
    (are [set1 set2]
      (not (eqset? set1 set2))
      '(6 large chickens with wings) '(6 chickens with very large wings))))


(deftest test-intersect?
  (testing "最初のセットが空の場合、false を返す"
    (are [set2]
      (not (intersect? '() set2))
      '()
      '(x)))

  (testing "set1 内に含まれるアトムが一つでも set2 内に含まれる場合、true を返す"
    (are [set1 set2]
      (intersect? set1 set2)
      '(stewed tomatoes and macaroni) '(macaroni and cheese)))

  (testing "set1 内に含まれるアトムの全てが set2 内に含まれない場合、false を返す"
    (are [set1 set2]
      (not (intersect? set1 set2))
      '(stewed tomatoes) '(macaroni and cheese))))

(deftest test-intersect
  (testing "最初のセットが空の場合、空のリストを返す"
    (are [set2]
      (= (intersect '() set2))
      '()
      '(x)))

  (testing "set1 と set2 内で共通のアトムが存在する場合、set1 と set2 の積集合を返す"
    (are [set1 set2 expected]
      (= (intersect set1 set2) expected)
      '(stewed tomatoes and macaroni) '(macaroni and cheese) '(and macaroni)))

  (testing "set1 と set2 内で共通のアトムが存在しない場合、空のリストを返す"
    (are [set1 set2]
      (= (intersect set1 set2) '())
      '(stewed tomatoes) '(macaroni and cheese))))


(deftest test-union
  (testing "両方のセットが空の場合、空のリストを返す"
    (is (= (union '() '()) '())))

  (testing "片方のセットが空の場合、もう片方のセットを返す"
    (is (= (union '() '(x y z)) '(x y z)))
    (is (= (union '(x y z) '()) '(x y z))))

  (testing "set1 と set2 が共に空ではない場合、set1 と set2 の和集合を返す"
    (are [set1 set2 expected]
      (= (union set1 set2) expected)
      '(stewed tomatoes and macaroni casserole) '(macaroni and cheese)
      '(stewed tomatoes casserole macaroni and cheese))))


(deftest test-intersectall
  (testing "リスト内にセットが一つしか存在しない場合、そのセットを返す"
    (is (= (intersectall '((x y z))) '(x y z))))

  (testing "リスト内のセット間に共通のアトムが存在する場合、リスト内のセットの積集合を返す"
    (are [l-set expected]
      (= (intersectall l-set) expected)
      '((a b c) (c a d e) (e f g h a b)) '(a)
      '((6 pears and) (3 peaches and 6 peppers)
        (8 pears and 6 plums) (and 6 prunes with lots of apples)) '(6 and)))

  (testing "リスト内のセット間に共通のアトムが存在しない場合、空のリストを返す"
    (are [l-set]
      (= (intersectall l-set) '())
      '((a b c) (d e f) (g)))))


(deftest test-a-pair?
  (testing "x がアトムの場合、false を返す"
    (are [x]
      (not (a-pair? x))
      1
      'xyz
      (fn [] true)))

  (testing "x が二つのS式だけからなるリストではない場合、false を返す"
    (are [x]
      (not (a-pair? x))
      '(1 2 3)
      '(foo bar piyo)))

  (testing "x が二つのS式だけからなるリストの場合、true を返す"
    (are [x]
      (a-pair? x)
      '(pear pear)
      '(3 7)
      '((2) (pair))
      '(full (house)))))


(deftest test-fun?
  (testing "(firsts rel) がセットではない場合、false を返す"
    (are [rel]
      (not (fun? rel))
      '((4 3) (4 2) (7 6) (6 2) (3 4))
      '((b 4) (b 0) (b 9) (e 5) (g 4))))

  (testing "(firsts rel) がセットの場合、true を返す"
    (are [rel]
      (fun? rel)
      '((8 3) (4 2) (7 6) (6 2) (3 4)))))


(deftest test-revrel
  (testing "rel が空の場合、空のリストを返す"
    (is (= (revrel '()) '())))

  (testing "rel が空ではない場合、各ペアの第1要素と第2要素を入れ替える"
    (are [rel expected]
      (= (revrel rel) expected)
      '((8 a) (pumpkin pie) (got chicken)) '((a 8) (pie pumpkin) (chicken got)))))


(deftest test-fullfun?
  (testing "fun が全単射ではない場合、false を返す"
    (are [fun]
      (not (fullfun? fun))
      '((8 3) (4 2) (7 6) (6 2) (3 4))
      '((grape raisin) (plum prune) (stewed prune))))

  (testing "fun が全単射の場合、true を返す"
    (are [fun]
      (fullfun? fun)
      '((8 3) (4 8) (7 6) (6 2) (3 4))
      '((grape raisin) (plum prune) (stewed grape)))))


(deftest test-one-to-one?
  (testing "fun が全単射ではない場合、false を返す"
    (are [fun]
      (not (one-to-one? fun))
      '((8 3) (4 2) (7 6) (6 2) (3 4))
      '((grape raisin) (plum prune) (stewed prune))))

  (testing "fun が全単射の場合、true を返す"
    (are [fun]
      (one-to-one? fun)
      '((8 3) (4 8) (7 6) (6 2) (3 4))
      '((grape raisin) (plum prune) (stewed grape)))))
