(ns
  ^{:author vijayrc}
  com.vijayrc.clj.features.regex)

(println "get match groups=" (re-seq #"(\d+)-(\d+)" "1-3"))
