# Review Processing Microservice - Spaced Repetition System

![Clojure](https://img.shields.io/badge/Clojure-%23Clojure.svg?style=for-the-badge&logo=Clojure&logoColor=Clojure)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/Rabbitmq-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white)

This is the **Review Processing Microservice** for the spaced repetition study system. It consumes messages from RabbitMQ, processes user reviews, and calculates the next review date for each flashcard based on the spaced repetition technique.

## Main Features

- **Message Consumption**: Listens to messages from a RabbitMQ queue where each message contains data about a reviewed flashcard.
- **Review Processing**: Processes the review by calculating the next review date based on spaced repetition algorithms.
- **Update Flashcard**: Saves the updated review data, including the next review date, in the database.

## How to Run the Project

### Requirements
- JDK
- Leiningen (https://leiningen.org/)
- Docker

### Steps to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/GuiPM001/spaced-repetition.git
   ```

2. Navigate to the project folder:
   ```bash
   cd spaced-repetition/spaced-repetition-processing
   ```

3. Run the following command to start the application using Docker Compose:
   ```bash
   docker-compose up -d
   ```

4. **Connect to the database** and execute the commands found in the `scripts.sql` file to create the database schema.

5. To run the API in Clojure, run the command below inside the project directory:
   ```
   lein run
   ```

6. Once the services are running, **access the RabbitMQ Management** by navigating to [localhost:15672](localhost:15672) in your browser to publish messages.