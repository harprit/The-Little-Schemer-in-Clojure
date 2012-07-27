(ns the-little-schemer.5-it-is-full-of-stars-test
  (:use clojure.test
        the-little-schemer.5-it-is-full-of-stars))

(deftest rember*-test
  (testing "リストが空の場合、空のリストを返す"
    (is (= (rember* 'x '()) '())))

  (testing "ls から a を取り除いた、別のリストを返す"
    (are [a ls expected]
      (= (rember* a ls) expected)
      'cup '((coffee) cup ((tea) cup) (and (hick)) cup)
      '((coffee) ((tea)) (and (hick)))

      'sauce '(((tomato sauce)) ((bean) sauce) (and ((flying)) sauce))
      '(((tomato)) ((bean)) (and ((flying)))))))


(deftest insertR*-test
  (testing "リストが空の場合、空のリストを返す"
    (is (= (insertR* 'x 'y '()) '())))

  (testing "ls 内の old の右に new を挿入した、別のリストを返す"
    (are [new old ls expected]
      (= (insertR* new old ls) expected)
      'roast 'chuck
      '((how much (wood)) could ((a (wood) chuck)) (((chuck)))
        (if (a) ((wood chuck))) could chuck wood)
      '((how much (wood)) could ((a (wood) chuck roast)) (((chuck roast)))
        (if (a) ((wood chuck roast))) could chuck roast wood))))


(deftest occur*-test
  (testing "リストが空の場合、0 を返す"
    (is (zero? (occur* 'x '()))))

  (testing "ls 内に出現する a の数を返す"
    (are [a ls expected]
      (= (occur* a ls) expected)
      'banana
      '((banana) (split ((((banana ice))) (cream (banana)) sherbet))
        (banana) (bread) (banana brandy))
      5)))


(deftest subst*-test
  (testing "リストが空の場合、空のリストを返す"
    (is (= (subst* 'x 'y '()) '())))

  (testing "ls 内に出現する old を new で置き換えた、別のリストを返す"
    (are [new old ls expected]
      (= (subst* new old ls) expected)
      'orange 'banana
      '((banana) (split ((((banana ice))) (cream (banana)) sherbet))
        (banana) (bread) (banana brandy))
      '((orange) (split ((((orange ice))) (cream (orange)) sherbet))
        (orange) (bread) (orange brandy)))))


(deftest insertL*-test
  (testing "リストが空の場合、空のリストを返す"
    (is (= (insertL* 'x 'y '()) '())))

  (testing "ls 内に出現する old の左に new を挿入した、別のリストを返す"
    (are [new old ls expected]
      (= (insertL* new old ls) expected)
      'pecker 'chuck
      '((how much (wood)) could ((a (wood) chuck))
        (((chuck))) (if (a) ((wood chuck))) could chuck wood)
      '((how much (wood)) could ((a (wood) pecker chuck))
        (((pecker chuck))) (if (a) ((wood pecker chuck))) could pecker chuck wood))))


(deftest member*-test
  (testing "リストが空の場合、false を返す"
    (is (not (member* 'x '()))))

  (testing "ls の中に a が存在する場合、true を返す"
    (are [a ls]
      (member* a ls)
      'chips '((potato) (chips ((with) fish) (chips)))))

  (testing "ls の中に a が存在しない場合、false を返す"
    (are [a ls]
      (not (member* a ls))
      'chicken '((potato) (chips ((with) fish) (chips))))))


(deftest leftmost-test
  ; 空リストを含まない、空でないリストを受けとることを、事前の条件としている
  (testing "リストの中で一番左のアトムを返す"
    (are [ls expected]
      (= (leftmost ls) expected)
      '((potato) (chips ((with) fish) (chips))) 'potato
      '(((hot) (tuna (and))) cheese) 'hot)))


(deftest eqlist?-test
  (testing "l1 と l2 の要素が全て同じならば、true を返す"
    (are [l1 l2]
      (eqlist? l1 l2)
      '(strawberry ice cream) '(strawberry ice cream)
      '(beef ((sausage)) (and (soda))) '(beef ((sausage)) (and (soda)))))

  (testing "l1 と l2 の要素が一つでも異なれば、false を返す"
    (are [l1 l2]
      (not (eqlist? l1 l2))
      '(strawberry ice cream) '(strawberry cream ice)
      '(banana ((split))) '((banana) (split))
      '(beef ((sausage)) (and (soda))) '(beef ((salami)) (and (soda))))))


(deftest rember-test
  (testing "l の中で最初に出現する s を取り除き、別のリストを返す"
    (are [s l expected]
      (= (rember s l) expected)
      '((sausage)) '(beef ((sausage)) (and (soda))) '(beef (and (soda))))))
