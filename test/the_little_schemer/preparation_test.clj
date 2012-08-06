(ns the-little-schemer.preparation-test
  (:use clojure.test
        the-little-schemer.preparation))

(deftest atom?-test
  (testing "指定した値が空リストの場合は false を返す"
    (is (not (atom? '()))))

  (testing "指定した値が空ではないリストの場合は false を返す"
    (is (not (atom? '(x)))))

  (testing "指定した値がリストではない場合は true を返す"
    (are [x]
      (atom? x)
      'a
      1
      "foo"
      (fn [] nil))))
