(ns isomorphic-app.server
  (:require [rum.core :as rum]
            [clojure.data.json :as json]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [isomorphic-app.app :as app]))

(defn dehydrate-state [state]
  (str "window." app/app-state-name "=" (json/write-str state)))

(rum/defc document [state]
  [:html {}
   [:head {}
    [:meta {:charset "utf-8"}
     [:meta {:name "viewport"
             :content "width=device-width, initial-scale=1"}]]]
   [:body {}
    [:script {:type "text/javascript" :dangerouslySetInnerHTML {:__html (dehydrate-state state)}}]
    [:div#app {:dangerouslySetInnerHTML {:__html (rum/render-html (app/root state))}}]
    [:script {:src "js/compiled/figwheel_app.js" :type "text/javascript"}]]])

(defroutes app-routes
  (GET "/" [] (rum/render-static-markup (document {:murder "REDRUM"})))
  (route/not-found "<h1>Page not found</h1>"))

(def handler
  (wrap-defaults app-routes site-defaults))
