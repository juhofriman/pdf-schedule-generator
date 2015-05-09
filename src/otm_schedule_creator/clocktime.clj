(ns otm-schedule-creator.clocktime
  (:require [clojure.string :as string]))

(defn
  ^{:private true
    :doc "Throws if hours or minutes are illeagal" }
  fail-if-illegal
  [[hours mins :as valuevec]]
    (cond
      (> mins 59)
        (throw (IllegalArgumentException. (str "mins " mins " is illegal")))
      (> hours 23)
        (throw (IllegalArgumentException. (str "hours " hours " is illegal")))
      :else valuevec))

(defn
  ^{:private true
    :doc "Removes leading 0 from string" }
  remove-preceeding-zero
  [s]
  (let [a (seq s)]
    (if (= \0 (first a))
      (string/join (rest a))
      (string/join a))))

(defn
  ^{:private true
    :doc "Transforms int to string and adds leading 0 if needed" }
  pad-zero-if-needed
  [n]
  (if
    (< n 10)
      (str "0" n)
      (str n)))

(defn
  str-to-clocktime
  "Transforms clocktime string ('12:00') representation to vector [12 0]"
  [s]
    (fail-if-illegal (vec (map #(read-string (remove-preceeding-zero %)) (string/split s #":")))))

(defn
  clocktime-to-str
  "Transforms clocktime vector [12 0] to string 12:00"
  [[hours minutes]]
  (str (pad-zero-if-needed hours) ":" (pad-zero-if-needed minutes)))

(defn
  clocktime-add
  "Adds minutes and/or hours to clocktime vector"
  [[h m] & {:keys [minutes hours]
            :or { minutes 0 hours 0}}]
    (let [rawh (+ h hours)  rawm(+ m minutes)]
      [(rem (+ rawh (quot rawm 60)) 24) (rem rawm 60)]))

