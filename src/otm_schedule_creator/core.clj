(ns otm-schedule-creator.core
  (:use clj-pdf.core))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))


(let [date "1.1.2013"
      location "Arkadiankatu 28"
      events [{:time "7:30-10" :text "Lähtö kotoa ja junamatka Helsinkiin"}
              {:time "10-12" :text "Saapuminen Arkadiankadulle. Työskentelyä."}
              {:time "12-12:30" :text "Omakustanteinen lounas"}
              {:time "12:30-16" :text "Työskentelyä"}
              {:time "16:30-18:30" :text "Junamatka Tampereelle"}]]
  (pdf
    [{}
     [:heading (str "OTM-työtä Helsingissä " date)]
     [:line]
     [:spacer]
     [:paragraph location]
     [:spacer]
     [:spacer]
     (into
      [:table
       { :border false :cell-border false
         :widths [20 80]}]
      (for [e events]
         [[:cell [:chunk {:style :bold} (:time e)]] [:cell (:text e)]]))]
    "doc.pdf"))

