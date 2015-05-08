(ns otm-schedule-creator.clocktime
  (:require [clojure.string :as string]))

(defn ^:private
  fail-if-illegal
  [[hours mins :as valuevec]]
    (cond
      (> mins 59)
        (throw (IllegalArgumentException. (str "mins " mins " is illegal")))
      (> hours 23)
        (throw (IllegalArgumentException. (str "hours " hours " is illegal")))
      :else valuevec))

(defn ^:private
  remove-preceeding-zero
  [s]
  (let [a (seq s)]
    (if (= \0 (first a))
      (string/join (rest a))
      (string/join a))))

(defn
  str-to-clocktime
  [s]
    (fail-if-illegal (vec (map #(read-string (remove-preceeding-zero %)) (string/split s #":")))))

(defn ^:private
  pad-zero-if-needed
  [n]
  (if
    (< n 10)
      (str "0" n)
      (str n)))

(defn
  clocktime-to-str
  [[hours minutes]]
  (str (pad-zero-if-needed hours) ":" (pad-zero-if-needed minutes)))

(defn ^:private
  pad-extra-minutes-to-hours
  [[h m]]
  [(rem (+ h (quot m 60)) 24) (rem m 60)])

(defn
  clocktime-add
  [[h m] & {:keys [minutes hours]
            :or { minutes 0 hours 0}}]
    (pad-extra-minutes-to-hours [(+ h hours) (+ m minutes)]))


