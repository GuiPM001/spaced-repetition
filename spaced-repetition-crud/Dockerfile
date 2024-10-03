FROM clojure:latest

WORKDIR /app

COPY project.clj .
RUN lein deps

COPY . .
RUN lein uberjar

EXPOSE 3300

CMD ["java", "-jar", "target/uberjar/spaced-repetition-crud-0.1.0-SNAPSHOT-standalone.jar"]