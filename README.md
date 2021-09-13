# Mail Delivery Service with Apache Kafka

## Prerequisites
* [Get docker](https://docs.docker.com/get-docker/)
* [Install docker compose](https://docs.docker.com/compose/install/)
* [Apache Kafka](https://kafka.apache.org/documentation/)
* [Kafdrop – Kafka Web UI  ](https://github.com/obsidiandynamics/kafdrop)

## Usage

Create a network so that there is communication between services.

```shell 
	$ docker network create mail-delivery-network
```

Running local mail delivery service.
```shell
 $ docker-compose up -d
```

Stop and remove mail delivery service.
```shell
 $ docker-compose down
```


### Project structure

```shell
|   .gitignore
|   docker-compose.yml
|   Dockerfile
|   HELP.md
|   mvnw
|   mvnw.cmd
|   pom.xml
|   README.md
|
+---.mvn
|   \---wrapper
|           maven-wrapper.jar
|           maven-wrapper.properties
|           MavenWrapperDownloader.java
|           
+---src
|   +---main
|   |   +---java
|   |   |   \---io
|   |   |       \---jorgel
|   |   |           \---sendemail
|   |   |               |   SendEmailApplication.java
|   |   |               |   ServletInitializer.java
|   |   |               |   
|   |   |               +---config
|   |   |               |       KafkaConsumerConfig.java
|   |   |               |       TemplateConfig.java
|   |   |               |       
|   |   |               +---models
|   |   |               |       Email.java
|   |   |               |       
|   |   |               \---services
|   |   |                   |   ConsumerService.java
|   |   |                   |   EmailSenderService.java
|   |   |                   |   
|   |   |                   \---impl
|   |   |                           EmailSenderServiceImpl.java
|   |   |                           
|   |   \---resources
|   |       |   application.yml
|   |       |   
|   |       +---static
|   |       \---templates
|   |               confirmation.html
|   |               
|   \---test
|       \---java
|           \---io
|               \---jorgel
|                   \---sendemail
|                           SendEmailApplicationTests.java
|                           
```