(ns isomorphic-app.client
  (:require [rum.core :as rum]
            [isomorphic-app.app :as app]))

(rum/hydrate (app/my-component) (.getElementById js/document "app"))
