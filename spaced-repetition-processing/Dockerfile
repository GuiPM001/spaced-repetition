FROM clojure:latest

WORKDIR /app

ENV DOCKERIZE_VERSION v0.6.1
RUN apt-get update && apt-get install -y wget \
    && wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

COPY project.clj .
RUN lein deps

COPY . .
RUN lein uberjar

CMD ["dockerize", "-wait", "tcp://rabbitmq:5672", "-wait", "tcp://db:3306", "-timeout", "60s", "java", "-jar", "target/spaced-repetition-processing-0.1.0-SNAPSHOT-standalone.jar"]