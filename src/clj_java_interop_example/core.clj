(ns clj-java-interop-example.core
  (:require [clj-http.client :as client]
            [cheshire.core :refer :all])
  (:import [java.util.concurrent Executors ScheduledExecutorService TimeUnit]
           [java.lang Runnable])
  (:gen-class))

(def hello-world 
  (reify Runnable
    (run [this] (println "hello clojure!"))))

(defn fetch-data-from-api [url]
  (parse-string (:body (client/get url)) true))

(defn runnable-fetch-data-from-api [url]
  (reify Runnable
    (run [this]
         (def res-body (fetch-data-from-api url))
        ;  print only email with tld .com
         (println (filter #(re-matches #"(.*\.com)" %) (map #(:email %) res-body))))))

(defn -main
  [& args]
  (def scheduler (Executors/newScheduledThreadPool 1))
  
  ; fetch data from external API every 3 hours
  (.scheduleAtFixedRate scheduler (runnable-fetch-data-from-api "https://jsonplaceholder.typicode.com/comments") 0 3 TimeUnit/HOURS)
  ; Print "hello clojure!" every 5 seconds
  (.scheduleAtFixedRate scheduler hello-world 0 5 TimeUnit/SECONDS))
