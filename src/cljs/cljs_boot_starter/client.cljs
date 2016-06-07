(ns cljs-boot-starter.client
  (:require [reagent.core :as ra :refer [atom render]]))

(enable-console-print!)

(defn hello []
 (let [name (atom "hai")]
   (fn []
      [:div
       [:input {:type "text"
                :value @name
                :on-change #(reset! name (-> % .-target .-value))}]
       [:div @name]])))

;;Javascript interop

;;(def text js/globalName)
;;(js/console.log text)
;;(js/console.log js/globalName)
;;(js/console.log (aget js/globalArray 3))
;;(js/console.log (aget js/globalArray 1))
;;(js/console.log (aget js/globalArray 3 1))
;;(js/console.log js/globalObject.b)
;;(js/console.log js/globalObject.c.f)
;;(.hello js/window)
;;(js/window.helloAgain "sailaja")
;;(js/console.log (js/MyType.))
;;(def t (js/MyComplexType. "samhitha"))
;;(js/console.log t)
;;(def my-type (js/MyType.)) 
;;(def value (.-name my-type))
;;(js/console.log value)
;;(js/console.log (.-name t))
;;(js/console.log (aget t "name"))
;;(js/console.log (aset my-type "name" "Bob"))
;;(js/console.log (set! (.-name t) "Jack"))

;;creating javascript objects

(def my-object (js-obj "a" 1 "b" true "c" nil))
(js/console.log my-object)
;;(def js-object (js-obj  :a 1 :b [1 2 3] :c #{"d" true :e nil}))
;;(js/console.log js-object)
;;(js/console.log (aget my-object "b"))
;;(js/console.log (aget js-object :a))
(def js-object (clj->js  {:a 1 :b [1 2 3] :c #{"d" true :e nil}}))
(js/console.log js-object)
(js/console.log (aget js-object "b"))
(def my-array (js->clj (.-globalArray js/window)))
(js/console.log my-array)
(js/console.log (get my-array 2))
(def my-obj (js->clj (.-globalObject js/window)))
(js/console.log (get my-obj "d"))
(def my-obj1 (js->clj (.-globalObject js/window) :keywordize-keys true))
(js/console.log my-obj1)
(def a (:b my-obj1))
(def b (get-in my-obj1 [:c :e]))
(js/console.log (+ a b)) 


(defn init []
  (render [hello] (.getElementById js/document "my-app-area")))

(init)
