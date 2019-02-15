(ns play-10-print.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def scale 20)

(defn initialise
  []
  (q/smooth)
  (q/color-mode :hsb 360 100 100)
  (q/background 193 100 21) ; Solarized base03.
  (q/stroke 237 45 77) ; Solarized violet.
  [0 0])

(defn next-state
  [[x y]]
  (let [x' (+ x scale)]
    (if (>= x' (q/width))
      [0 (+ y scale)]
      [x' y])))

(defn draw-state
  [[x y]]
  (if (>= y (q/height))
    (q/no-loop)
    (if (> 0.5 (q/random 1))
      (q/line x y (+ x scale) (+ y scale))
      (q/line x (+ y scale) (+ x scale) y))))


(q/defsketch play-10-print
  :title "10PRINT"
  :size [800 800]
  :setup initialise
  :update next-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])
