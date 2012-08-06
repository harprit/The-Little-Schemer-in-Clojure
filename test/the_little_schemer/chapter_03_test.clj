(ns the-little-schemer.chapter-03-test
  (:use clojure.test
        the-little-schemer.chapter-03))

(deftest rember-test
  (testing "ラットが空の場合、空のリストを返す"
    (is (= (rember :x '()) '())))

  (testing "アトムがラットに含まれている場合、ラットの中から指定したアトムの最初のものだけを取り除く"
    (are [a lat expected]
      (= (rember a lat) expected)
      'mint '(lamb chops and mint jelly) '(lamb chops and jelly)
      'mint '(lamb chops and mint flavored mint jelly) '(lamb chops and flavored mint jelly)))

  (testing "アトムがラットに含まれていない場合、ラットと同じ値を返す"
    (is (= (rember 'toast '(bacon lettuce and tomato)) '(bacon lettuce and tomato)))))


(deftest firsts-test
  (testing "リストが空の場合、空のリストを返す"
    (is (= (firsts '()) '())))

  (testing "リストが空ではない場合、各々の内部リストの最初の要素からなる、別のリストを返す"
    (are [ls expected]
      (= (firsts ls) expected)
      '((apple peach pumpkin)
        (plum pear cherry)
        (grape raisin pea)
        (bean carrot eggplant)) '(apple plum grape bean)
      '((a b) (c d) (e f)) '(a c e)
      '((five plums)
        (four)
        (eleven green oranges)) '(five four eleven)
      '(((five plums) four)
        (eleven green oranges)
        ((no) more)) '((five plums) eleven (no)))))


(deftest insertR-test
  (testing "ラットが空の場合、空のリストを返す"
    (is (= (insertR :x :y '()) '())))

  (testing "ラットが空ではない場合、lat 内に最初に出現した old の右に new を挿入した、別のラットを返す"
    (are [new old lat expected]
      (= (insertR new old lat) expected)
      'topping 'fudge '(ice cream with fudge for dessert) '(ice cream with fudge topping for dessert)
      'jalapeno 'and '(tacos tamales and salsa) '(tacos tamales and jalapeno salsa)
      'e 'd '(a b c d f g d h) '(a b c d e f g d h))))


(deftest insertL-test
  (testing "ラットが空の場合、空のリストを返す"
    (is (= (insertL :x :y '()) '())))

  (testing "ラットが空ではない場合、lat 内に最初に出現した old の左に new を挿入した、別のラットを返す"
    (are [new old lat expected]
      (= (insertL new old lat) expected)
      'topping 'for '(ice cream with fudge for dessert) '(ice cream with fudge topping for dessert)
      'jalapeno 'salsa '(tacos tamales and salsa) '(tacos tamales and jalapeno salsa)
      'e 'f '(a b c d f g f h) '(a b c d e f g f h))))


(deftest subst-test
  (testing "ラットが空の場合、空のリストを返す"
    (is (= (subst :x :y '()) '())))

  (testing "ラットが空ではない場合、lat 内に最初に出現した old を new に置き換えた、別のラットを返す"
    (are [new old lat expected]
      (= (subst new old lat) expected)
      'topping 'fudge '(ice cream with fudge for dessert) '(ice cream with topping for dessert)
      :b :d '(:a :d :c :d :e) '(:a :b :c :d :e))))


(deftest subst2-test
  (testing "ラットが空の場合、空のリストを返す"
    (is (= (subst2 :x :y :z '()) '())))

  (testing "ラットが空ではない場合、lat 内に最初に出現した o1 または o2 を new に置き換えた、別のラットを返す"
    (are [new o1 o2 lat expected]
      (= (subst2 new o1 o2 lat) expected)
      'vanilla 'chocolate 'banana '(banana ice cream with chocolate topping) '(vanilla ice cream with chocolate topping)
      'vanilla 'chocolate 'banana '(chocolate ice cream with banana topping) '(vanilla ice cream with banana topping))))


(deftest multirember-test
  (testing "ラットが空の場合、空のリストを返す"
    (is (= (multirember :x '()) '())))

  (testing "アトムがラットに含まれている場合、ラットの中から指定したアトムを取り除いた、別のラットを返す"
    (are [a lat expected]
      (= (multirember a lat) expected)
      'cup '(coffee cup tea cup and hick cup) '(coffee tea and hick)))

  (testing "アトムがラットに含まれていない場合、ラットと同じ値を返す"
    (is (= (multirember 'toast '(bacon lettuce and tomato)) '(bacon lettuce and tomato)))))


(deftest multiinsertR-test
  (testing "ラットが空の場合、空のリストを返す"
    (is (= (multiinsertR :x :y '()) '())))

  (testing "ラットが空ではない場合、lat 内に出現した old の右に new を挿入した、別のラットを返す"
    (are [new old lat expected]
      (= (multiinsertR new old lat) expected)
      'topping 'fudge '(ice cream with fudge for dessert) '(ice cream with fudge topping for dessert)
      'fish 'fried '(chips and fried or fried and fish) '(chips and fried fish or fried fish and fish))))


(deftest multiinsertL-test
  (testing "ラットが空の場合、空のリストを返す"
    (is (= (multiinsertL :x :y '()) '())))

  (testing "ラットが空ではない場合、lat 内に出現した old の左に new を挿入した、別のラットを返す"
    (are [new old lat expected]
      (= (multiinsertL new old lat) expected)
      'topping 'for '(ice cream with fudge for dessert) '(ice cream with fudge topping for dessert)
      'fried 'fish '(chips and fish or fish and fried) '(chips and fried fish or fried fish and fried))))


(deftest multisubst-test
  (testing "ラットが空の場合、空のリストを返す"
    (is (= (multisubst :x :y '()) '())))

  (testing "ラットが空ではない場合、lat 内に出現した old を new に置き換えた、別のラットを返す"
    (are [new old lat expected]
      (= (multisubst new old lat) expected)
      'topping 'fudge '(ice cream with fudge for dessert) '(ice cream with topping for dessert)
      'nyan 'cat '(wan cat wan wan cat) '(wan nyan wan wan nyan))))
