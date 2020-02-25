(ns isomorphic-app.client
  (:require [rum.core :as rum]
            [isomorphic-app.app :as app]
            [clojure.walk :as walk]))

(defn hydrate-state [js-json]
  (walk/keywordize-keys (js->clj js-json)))

(rum/hydrate
 (app/root (hydrate-state (aget js/window app/app-state-name)))
 (.getElementById js/document "app"))
