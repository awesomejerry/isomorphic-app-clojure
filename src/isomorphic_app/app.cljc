(ns isomorphic-app.app
  (:require [rum.core :as rum]))

(def app-state-name "APP_STATE")

(defn log [s]
  #?(:cljs (.log js/console s)))

(defn handle-click []
  (log "Clicked"))

(rum/defc label [text]
  [:div {:class "label" :on-click handle-click} text])

(rum/defc root [app-state]
  [:div (label (str "MURDER | " (:murder app-state)))])
