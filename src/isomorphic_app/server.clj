(ns isomorphic-app.server
  (:require [rum.core :as rum]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [isomorphic-app.app :as app]))

(rum/defc document [app]
  [:html {}
   [:head {}
    [:meta {:charset "utf-8"}
     [:meta {:name "viewport"
             :content "width=device-width, initial-scale=1"}]]]
   [:body {}
    [:div#app {:dangerouslySetInnerHTML {:__html (rum/render-html (app))}}]
    [:script {:src "js/compiled/figwheel_app.js" :type "text/javascript"}]]])

(defroutes app-routes
  (GET "/" [] (rum/render-static-markup (document app/my-component)))
  (route/not-found "<h1>Page not found</h1>"))

(def handler
  (wrap-defaults app-routes site-defaults))
