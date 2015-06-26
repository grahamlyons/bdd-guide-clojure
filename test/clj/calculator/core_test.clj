(ns calculator.core-test
  (:require [clojure.test :refer [deftest]])
  (:import (cucumber.runtime RuntimeOptions)
           (cucumber.runtime.io MultiLoader)))

(deftest run-cukes
  (let [classloader (.getContextClassLoader (Thread/currentThread))
        runtime-options (RuntimeOptions.
                          (System/getProperties)
                          (into-array ["--format"
                                       "pretty"
                                       "--glue"
                                       "test/acceptance/step_definitions"
                                       "test/acceptance/features"]))
        runtime (cucumber.runtime.Runtime.
                  (MultiLoader. classloader)
                  classloader
                  runtime-options)]
    (doto runtime
      (.writeStepdefsJson)
      (.run))
    ))

