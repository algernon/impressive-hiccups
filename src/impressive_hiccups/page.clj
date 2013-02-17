(ns impressive-hiccups.page
  (:use [hiccup.element]
        [hiccup.core]
        [hiccup.util]
        [hiccup.page]))

(defmacro impress
  [options & contents]

  (if-not (map? options)
    `(impress {} ~options ~@contents)
    `(let [options# ~options]
       (html {:mode :html}
             (doctype :html5)
             [:html
              [:head
               [:meta {:charset "utf-8"
                       :viewport "width=1024"}]
               [:title (:title options#)]
               [:link {:href (to-uri (or (:css-uri options#)
                                         "css/main.css"))
                       :rel "stylesheet"}]
               [:link {:href (to-uri (or (:font-uri options#)
                                         "http://fonts.googleapis.com/css?family=Josefin+Sans"))
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

               (include-js (or (:impress-uri options#) "js/impress.js"))
               (javascript-tag "(function(window, undefined){ var _onload = window.onload;  window.onload = function() { if(_onload){ _onload(); } impress().init(); }; }(this));")]]))))
