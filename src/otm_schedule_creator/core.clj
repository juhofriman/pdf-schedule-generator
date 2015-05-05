(ns otm-schedule-creator.core
  (:gen-class)
  (:require [clojure.tools.cli :refer [parse-opts]])
  (:use clj-pdf.core))

(def cli-options
  ;; An option with a required argument
  [["-s" "--starttime STARTTIME" "Start time"
    :default "7:30"]
   ["-e" "--endtime ENDTIME" "End time"
    :default "18:30"]
   ["-f" "--file FILE" "file"
    :default "raport.pdf"]])

(defn print-usage [usage notifications]
  (do
    (println "OTM-raport generator!")
    (println ".run [OPTS] DATE")
    (println)
    (println "OPTS:")
    (println usage)
    (println)
    (doseq [n notifications]
      (println n))))


(defn create-pdf [header date location events filename]
  (pdf
    [{}
     [:heading (str header " " date)]
     [:line]
     [:spacer]
     [:paragraph location]
     [:spacer]
     [:spacer]
     (into [:table { :border false :cell-border false :widths [20 80]}]
           (for [e events]
             [[:cell [:chunk {:style :bold} (:time e)]]
              [:cell (:text e)]]))]
    filename))



(defn generate-raport [[date] opts]
  (create-pdf
   "OTM-työtä Helsingissä"
   date
   "Arkadiankatu 28"
   [{:time (:starttime opts) :text "Lähtö kotoa ja junamatka Helsinkiin"}
    {:time "10-12" :text "Saapuminen Arkadiankadulle. Työskentelyä."}
    {:time "12-12:30" :text "Omakustanteinen lounas"}
    {:time "12:30-16" :text "Työskentelyä"}
    {:time (:endtime opts) :text "Junamatka Tampereelle"}]
   (:file opts)))

(defn -main [& args]
  (let [parsedArgs (parse-opts args cli-options)]
    (cond
      (not (empty? (:errors parsedArgs)))
        (print-usage (:summary parsedArgs) (:errors parsedArgs))
      (empty? (:arguments parsedArgs))
        (print-usage (:summary parsedArgs) ["DATE is mandatory"])

      :else (generate-raport (:arguments parsedArgs) (:options parsedArgs)))))
