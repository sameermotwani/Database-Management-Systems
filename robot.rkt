;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname robot) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; A Position is a (list Integer Integer)
;; (list x y) represents the position (x,y).
;; Note: this is not to be confused with the built-in data type Posn.



;; A Move is a (list Direction PosInt)
;; Interp: a move of the specified number of steps in the indicated
;; direction.


;; A Direction is one of
;; -- "ne"
;; -- "se"
;; -- "sw"
;; -- "nw"


;; A Plan is a ListOfMove
;; WHERE: the list does not contain two consecutive moves in the same
;; direction.
;; INTERP: the moves are to be executed from the first in the list to
;; the last in the list.


;;eval-plan : Position ListOfPosition Plan ->  MaybePosition
;;GIVEN:
;; 1. the starting position of the robot,
;; 2. A list of the blocks on the board
;; 3. A plan for the robot's motion
;;RETURNS: The position of the robot at the end of executing the plan, or false
;;         if  the plan sends the robot to or  through any block.

(define INITIAL 0)
(define ONE 1)

(define (eval-plan p blocks moves)
  (result-eval-plan p blocks moves INITIAL))

(define (result-eval-plan p blocks moves INITIAL)
  (cond
    [(equal? INITIAL ONE) false]
    [(empty? moves)p]
    [else (traverse-moves p blocks moves)]))


(define (traverse-moves p blocks moves)
  (foldl (lambda (n p)
           (if(equal? false p)
           (result-eval-plan p blocks moves ONE)
           (posn-after-move n blocks p))) p moves))


;
;(define (posn-after-move move blocks p)
;(cond
;  [(equal? (first move) "ne")
;  (move-robot p blocks (second move) 0 "a" + +)]
;  [(equal? (first move) "nw")
;  (move-robot p blocks (second move) 0 "a" - +)]
;  [(equal? (first move) "se")
;  (move-robot p blocks (second move) 0 "a" + -)]
;  [(equal? (first move) "sw")
;  (move-robot p blocks (second move) 0 "a" - -)]))


(define (posn-after-move move blocks p)
(cond
  [(equal? (first move) "ne")
  (move-robot p blocks (second move) 0 + +)]
  [(equal? (first move) "nw")
  (move-robot p blocks (second move) 0 - +)]
  [(equal? (first move) "se")
  (move-robot p blocks (second move) 0 + -)]
  [(equal? (first move) "sw")
  (move-robot p blocks (second move) 0 - -)]))


;(define (move-robot p blocks halt initial str operator1 operator2)
; (if(and (not(= halt initial))
;        (not(string=? str "b"))) 
;    (if(check-present? (list (operator1 (first p) 1)
;                                (operator2 (second p) 1)) blocks)
;       (move-robot p blocks halt initial "b" operator1 operator2)
;       (move-robot (list (operator1 (first p) 1)
;                                (operator2 (second p) 1)) blocks halt (+ initial 1) str operator1 operator2))
;       
;    (if (= halt initial)
;        p
;        false)))  

(define (move-robot p blocks halt initial operator1 operator2)
(if(not(= halt initial))
   (check-next-move-valid? p blocks halt initial operator1 operator2)
   p))


(define (check-next-move-valid? p blocks halt initial operator1 operator2)
  (if(member? (next-robot-posn p operator1 operator2) blocks)
     (result-eval-plan p blocks empty ONE)
     (move-robot (next-robot-posn p operator1 operator2)
                 blocks
                 halt
                 (+ initial ONE)
                 operator1
                 operator2)))





(define (next-robot-posn p operator1 operator2)
  (list (operator1 (first p) 1) (operator2 (second p) 1)))


  
;(define (check-present? robot-posn blocks)
;(member? robot-posn blocks))

  
(define wall1
  '((3 3)(2 3)(4 3)
    (0 5)     (4 5)
    (0 7)(2 7)(4 7)))

(eval-plan (list 1 1) wall1 (list (list "ne" 4)(list "se" 1)(list "sw" 2)(list "nw" 2)))

  
(define (path initial target lop)
  (cond
    [(member? initial lop) #false]
    [(member? target lop) #false]
    [else (if (integer? (/ (+ (- (first target) (first initial))
                              (- (second target) (second initial)))
                           2))
              (find-path initial target lop empty)
              false)]))


(define (find-path initial target lop lst)
  (move-robot-in-direction
   (find-direction (- (first target) (first initial))
                  (- (second target) (second initial))) initial target lop lst))





  
(define (move-robot-in-direction dir initial target lop lst)
(cond 
  [(equal? initial target) lst]
  [(string=? dir "ne") (if(member (list(+(first initial) 1)
                                       (+(second initial) 1))
                                  lop)
                         (move-robot-in-direction "se" initial target lop lst) 
                         (find-path  (list(+(first initial) 1)(+(second initial) 1))
                                     target
                                     lop
                                     (cons (list dir (list(+(first initial) 1)
                                                     (+(second initial) 1))) lst)))]
  
[(string=? dir "se") (if(member (list(+(first initial) 1)
                                       (-(second initial) 1))
                                  lop)
                         (move-robot-in-direction "sw" initial target lop lst) 
                         (find-path  (list(+(first initial) 1)(-(second initial) 1))
                                     target
                                     lop
                                     (cons (list dir (list(+(first initial) 1)
                                                     (-(second initial) 1))) lst)))]
[(string=? dir "sw") (if(member (list(-(first initial) 1)
                                       (-(second initial) 1))
                                  lop)
                         (move-robot-in-direction "nw" initial target lop lst) 
                         (find-path  (list(-(first initial) 1)(-(second initial) 1))
                                     target
                                     lop
                                     (cons (list dir (list(-(first initial) 1)
                                                     (-(second initial) 1))) lst)))]
[(string=? dir "nw") (if(member (list(-(first initial) 1)
                                       (+(second initial) 1))
                                  lop)
                         (move-robot-in-direction "ne" initial target lop lst) 
                         (find-path  (list(-(first initial) 1)(+(second initial) 1))
                                     target
                                     lop
                                     (cons (list dir (list(-(first initial) 1)
                                                     (+(second initial) 1))) lst)))]
  
  )) 

(define (find-direction x y)
(cond
  [(and (positive? x) (positive? y)) "ne"]
  [(and (negative? x) (positive? y)) "nw"]
  [(and (positive? x) (negative? y)) "se"]
  [(and (negative? x) (negative? y)) "sw"]
  [(and (equal? 0 x) (positive? y)) "ne"]
  ;; [(and (positive? x) (equal? y 0)) "ne"]
  [else "false"]))



(define wall2
  '((0 3)(2 3)(4 3)
    (0 5)     (4 5)
    (0 7)(2 7)(4 7)))

(path (list 2 5) (list 4 9) wall2)
;; (path (list 2 5) (list 4 9) (rest wall1))

(define two-walls
  '((0 3)(4 3)
    (0 5)(4 5)
    (0 7)(4 7) 
    (0 9)(4 9)
    (0 11)(4 11)))
(path (list -3 6) (list 7 6) two-walls)