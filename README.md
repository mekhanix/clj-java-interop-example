# clj-java-interop-example
I'm learning *clojure-java* interoperability. After a bit of googling and stackoverflowing, I choose implementing `ScheduledExecutorService` from Clojure because why not, in fact, now I know how to run simple scheduled tasks without java verbosity.

## Usage
    $ lein uberjar
    $ java -jar target/uberjar/clj-java-interop-example-0.1.0-SNAPSHOT-standalone.jar