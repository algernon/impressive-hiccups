(ns impressive-hiccups.page
  (:use [hiccup.element]
        [hiccup.core]))

(defmacro impress
  [title & contents]

  `(html {:mode :html}
         (doctype :html5)
         [:html
          [:head
           [:meta {:charset "utf-8"
                   :viewport "width=1024"}]
           [:title ~title]
           [:link {:href "css/main.css"
                   :rel "stylesheet"}]
           [:link {:href "http://fonts.googleapis.com/css?family=Josefin+Sans"
                   :rel "stylesheet"}]]

          [:body
           [:div {:class "fallback-message"}
            [:p "Your browser "
             [:strong "doesn't support the features required"]
             " by impress.js, so you are preseqnted with a "
             "simplified version of this presentation."]

            [:p "For the best experience please use the latest"
             [:strong "Chrome"] ", "
             [:strong "Safari"] " or "
             [:strong "Firefox"] " browser."]]

           [:div {:id "impress"}
            ~@contents]
            
           (include-js "js/impress.js")
           (javascript-tag "(function(window, undefined){ var _onload = window.onload;  window.onload = function() { if(_onload){ _onload(); } impress().init(); }; }(this));")]]))
