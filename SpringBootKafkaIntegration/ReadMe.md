#Introduction
1. This project is about the spring boot kafka connectivity.
2. I have made Producer as well as Consumer myself with the help of ChatGPT mainly.
3. I have also added Code for SSL authentication and encryption
4. I have also added DLQ logic in it.
5. The consumer make few Retries to consume message. If it cannot then it redirects the message to DLQ.
6. So basically we have two topics. One is the main topic and another is the DLQ Topic

# How to Make it work
1. First you need to start kafka. Please refer 'Kafka -> Starting Kafka' Note for this.
2. Start this application.
3. Then make localhost call from browser.
4. http://localhost:8080/callKafkaProducer



