(defproject otm-schedule-creator "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clj-pdf "2.0.3"]
                 [org.clojure/tools.cli "0.3.1"]]
  :main otm-schedule-creator.core
  :repl-options {:init-ns otm-schedule-creator.clocktime})
