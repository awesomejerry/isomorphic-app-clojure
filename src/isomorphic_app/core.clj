(ns isomorphic-app.core
  (:require [rum.core :as rum]))

(rum/defc label [text]
  [:div {:class "label"} text])

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (rum/render-html (label "hello"))})
