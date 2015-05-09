(ns otm-schedule-creator.pdf
  (:require [clj-pdf.core :as pdf]))

(defn
  create-schedule-representation
  [& { :keys [header date location eventsvec]
       :or {eventsvec []}}]
    [{}
       [:heading (str header " " date)]
       [:line]
       [:spacer]
       [:paragraph location]
       [:spacer]
       [:spacer]
       (into [:table { :border false :cell-border false :widths [20 80]}]
             (for [e eventsvec]
               [[:cell [:chunk {:style :bold} (:time e)]]
                [:cell (:text e)]]))])


(defn materialize-pdf [data filename]
  (pdf/pdf
    data
    filename))


