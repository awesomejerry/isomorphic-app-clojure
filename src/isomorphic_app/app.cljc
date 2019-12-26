(ns isomorphic-app.app
  (:require [rum.core :as rum]))

(defn log [s]
  #?(:cljs (.log js/console s)))

(defn handle-click []
  (log "Clicked"))

(rum/defc label [text]
  [:div {:class "label" :on-click handle-click} text])

(rum/defc my-component []
  [:div (label "My Component")])
