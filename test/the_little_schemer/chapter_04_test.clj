(ns the-little-schemer.chapter-04-test
  (:use clojure.test
        the-little-schemer.chapter-04))

(deftest o+-test
  (testing "加算数が 0 の場合は、被加算数を返す"
    (is (= (o+ 5 0) 5)))

  (testing "加算数が 1以上の場合は、被加算数を加算数で加算した数を返す"
    (are [n m expected]
      (= (o+ n m) expected)
      1 1 2
      46 12 58)))


(deftest o--test
  (testing "減算数が 0 の場合は、被減算数を返す"
    (is (= (o- 5 0) 5)))

  (testing "減算数が 1以上の場合は、被減算数を減算数で減算した数を返す"
    (are [n m expected]
      (= (o- n m) expected)
      1 1 0
      14 3 11)))


(deftest addtup-test
  (testing "タップが空の場合は、0 を返す"
    (is (zero? (addtup '()))))

  (testing "タップが空ではない場合、タップ内の全ての数を合計した数を返す"
    (are [tup expected]
      (= (addtup tup) expected)
      '(3 5 2 8) 18
      '(15 6 7 12 3) 43)))


(deftest mul-test
  (testing "乗算数が 0 の場合は、0 を返す"
    (is (= (mul 5 0) 0)))

  (testing "乗算数が 1以上の場合は、被乗算数を乗算数で乗算した数を返す"
    (are [n m expected]
      (= (mul n m) expected)
      2 1 2
      5 3 15)))


(deftest tup+-test
  (testing "両方のタップが空の場合、空のリストを返す"
    (is (= (tup+ '() '()) '())))

  (testing "タップの要素数が同じ場合、双方のタップの同じ位置にある数を足し合わせた、別のタップを返す"
    (are [tup1 tup2 expected]
      (= (tup+ tup1 tup2) expected)
      '(1) '(2) '(3)
      '(1 2) '(3 4) '(4 6)))

  (testing "タップの要素数が異なり数を加算できない場合、片方のタップの数をそのまま用いた、別のタップを返す"
    (are [tup1 tup2 expected]
      (= (tup+ tup1 tup2) expected)
      '(1 4) '(2 5 8) '(3 9 8)
      '(4 2 6) '(8 3) '(12 5 6))))

(deftest gt?-test
  (testing "n が m よりも大きい場合、true を返す"
    (are [n m]
      (gt? n m)
      2 1
      5 3))

  (testing "n が m よりも小さい場合、false を返す"
    (are [n m]
      (not (gt? n m))
      1 2
      3 5))

  (testing "n と m が同じ数の場合、false を返す"
    (are [n m]
      (not (gt? n m))
      1 1
      3 3)))


(deftest lt?-test
  (testing "n が m よりも小さい場合、true を返す"
    (are [n m]
      (lt? n m)
      1 2
      3 5))

  (testing "n が m よりも大きい場合、false を返す"
    (are [n m]
      (not (lt? n m))
      2 1
      5 3))

  (testing "n と m が同じ数の場合、false を返す"
    (are [n m]
      (not (lt? n m))
      1 1
      3 3)))


(deftest o=-test
  (testing "n が m よりも小さい場合、false を返す"
    (are [n m]
      (not (o= n m))
      1 2
      3 5))

  (testing "n が m よりも大きい場合、false を返す"
    (are [n m]
      (not (o= n m))
      2 1
      5 3))

  (testing "n と m が同じ数の場合、true を返す"
    (are [n m]
      (o= n m)
      1 1
      3 3)))


(deftest pow-test
  (testing "べき乗数が 0 の場合は、1 を返す"
    (is (= (pow 5 0) 1)))

  (testing "べき乗数が 1以上の場合は、被べき乗数をべき乗数でべき乗した数を返す"
    (are [n m expected]
      (= (pow n m) expected)
      1 1 1
      2 3 8)))


(deftest quo-test
  (testing "除算数が被除算数よりも小さい場合は、0 を返す"
    (are [n m]
      (zero? (quo n m))
      1 2
      2 5))

  (testing "除算数が被除算数以上の場合、被除算数を除算数で除算した数を返す"
    (are [n m expected]
      (= (quo n m) expected)
      2 1 2
      15 3 5
      15 4 3)))


(deftest length-test
  (testing "ラットが空の場合、0 を返す"
    (is (zero? (length '()))))

  (testing "ラットが空ではない場合、ラットの要素数を返す"
    (are [lat expected]
      (= (length lat) expected)
      '(hotdogs with mustard sauerkraut and pickles) 6
      '(ham and cheese on rye) 5)))


(deftest pick-test
  (testing "ラットの n 番目の要素を返す"
    (are [n lat expected]
      (= (pick n lat) expected)
      4 '(lasagna spaghetti ravioli macaroni meatball) 'macaroni)))


(deftest rempick-test
  (testing "ラットの n 番目の要素を取り除いた、別のラットを返す"
    (are [n lat expected]
      (= (rempick n lat) expected)
      3 '(hotdogs with hot mustard) '(hotdogs with mustard))))


(deftest no-nums-test
  (testing "ラットが空の場合、空のリストを返す"
    (is (= (no-nums '()) '())))

  (testing "ラットが空ではない場合、ラットから数を取り除いた、別のラットを返す"
    (are [lat expected]
      (= (no-nums lat) expected)
      '(5 pears 6 prunes 9 dates) '(pears prunes dates))))


(deftest all-nums-test
  (testing "ラットが空の場合、空のリストを返す"
    (is (= (all-nums '()) '())))

  (testing "ラットが空ではない場合、ラットから数以外を取り除いた、タップを返す"
    (are [lat expected]
      (= (all-nums lat) expected)
      '(5 pears 6 prunes 9 dates) '(5 6 9))))


(deftest occur-test
  (testing "ラットが空の場合、0 を返す"
    (is (zero? (occur 'x '()))))

  (testing "ラットが空ではない場合、ラット内にアトム a が現れた数を返す"
    (are [a lat expected]
      (= (occur a lat) expected)
      'foo '(foo bar piyo foo) 2
      'hoge '(foo bar piyo) 0)))


(deftest eqan?-test
  (testing "a1, a2 が同じアトムの場合、true を返す"
    (are [a1 a2]
      (eqan? a1 a2)
      1 1
      'x 'x))

  (testing "a1, a2 が違うアトムの場合、false を返す"
    (are [a1 a2]
      (not (eqan? a1 a2))
      1 2
      'x 'y
      1 'x)))


(deftest one?-test
  (testing "与えられた数が 1 の場合、true を返す"
    (is (one? 1)))

  (testing "与えられた数が 1以外の場合、false を返す"
    (are [n]
      (not (one? n))
      0
      2
      100)))
