(ns the-little-schemer.2-do-it-and-again-test
  (:use clojure.test
        the-little-schemer.2-do-it-and-again))

(deftest test-lat?
  (testing "リストが空の場合は false を返す"
    (is  (lat? '())))

  (testing "リストの要素が全てアトムの場合は true を返す"
    (is (lat? '(Jack Sprat could eat no chicken fat))))

  (testing "リスト内にリストが含まれる場合は false を返す"
    (are [x]
      (not (lat? x))
      '((Jack) Sprat could eat no chicken fat)
      '(Jack (Sprat could) eat no chicken fat))))


(deftest test-member?
  (testing "ラットが空の場合は false を返す"
    (is (not (member? :x '()))))

  (testing "アトムがラット内に含まれる場合は true を返す"
    (is (member? 'tea '(coffee tea or milk))))

  (testing "アトムがラット内に含まれない場合は false を返す"
    (is (not (member? 'poached '(fried eggs and scrambled eggs))))))
