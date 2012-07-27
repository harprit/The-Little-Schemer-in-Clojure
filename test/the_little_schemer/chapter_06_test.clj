(ns the-little-schemer.6-shadows-test
  (:use clojure.test
        the-little-schemer.6-shadows))

(deftest numbered?-test
  (testing "算術式の表現に +, *, ** を除いて、数だけを含んでいる場合、true を返す"
    (are [aexp]
      (numbered? aexp)
      1
      '(3 + (4 * 5))
      '(3 + (4 ** 5))))

 (testing "算術式の表現に +, *, ** と数以外を含んでいる場合、false を返す"
    (are [aexp]
      (not (numbered? aexp))
      'x
      '(3 + (4 * x)))))


(deftest value-test
  (testing "算術式の値を返す"
    (are [nexp expected]
      (= (value nexp) expected)
      1 1
      '(3 + (4 * 5)) 23
      '(1 + (3 ** 4)) 82)))


(deftest value2-test
  (testing "算術式の値を返す"
    (are [nexp expected]
      (= (value2 nexp) expected)
      1 1
      '(+ 3 (* 4 5)) 23
      '(+ 1 (** 3 4)) 82)))


(deftest sero?-test
  (testing "リストを使った数の表現が 0 ならば、true を返す"
    (is (sero? '() ; 0
               )))

  (testing "リストを使った数の表現が 0 でなければ、true を返す"
    (is (not (sero? '(()) ; 1
                    )))))


(deftest edd1-test
  (testing "リストを使った数の表現に 1 を加算する"
    (are [n expected]
      (= (edd1 n) expected)
      '() '(()) ; 0 + 1 = 1
      '(() ()) '(() () ()) ; 2 + 1 = 3
      )))


(deftest zub1-test
  (testing "リストを使った数の表現から 1 を減算する"
    (are [n expected]
      (= (zub1 n) expected)
      '(()) '() ; 1 - 1 = 0
      '(() () ()) '(() ()) ; 3 - 1 = 2
      )))


(deftest edd-test
  (testing "リストを使った数の表現 n に m を加算する"
    (are [n m expected]
      (= (edd n m) expected)
      '() '() '() ; 0 + 0 = 0
      '(()) '(() ()) '(() () ()) ; 1 + 2 = 3
      )))


(deftest zub-test
  (testing "リストを使った数の表現 n から m を減算する"
    (are [n m expected]
      (= (zub n m) expected)
      '() '() '() ; 0 - 0 = 0
      '(() () ()) '(() ()) '(()) ; 3 - 2 = 1
      )))
