## real-time-chat-app

### Project overview
Microservice based chat application that eanbles one to one messaging between users using WebScokets.

### Project demo
![ezgif com-gif-maker(1)](https://user-images.githubusercontent.com/54250129/130152700-0d87f851-e029-43cd-b3b3-e6c5978c585d.gif)

### Project architecture
![chat-app-archi](https://user-images.githubusercontent.com/54250129/130061054-7bc3fbc3-c5c4-4459-a730-274abd8462ff.png)

### Technologies and frameworks used:
Java 11, Maven, Spring, Spring Boot, Spring Security, Spring Cloud, Angular, RXJS, MySQL, WebSockets.


### Messaging-service
Responsible for handling messages and persisting it MySQL database. In order to achieve realtime real time messaging it uses webscoket protocol, which provides full
duplex communication channels over TCP.

### Statistic-service
Responsible for gathering analytics between users. Uses MySQL queries with Spring jdbc template.

### Auth-service
Responsible for registration of users and generating JWT token for authenitcation, to achieve this it uses Spring security.

### Api-gateway
Responsible for siting between a client and backend microservices, used for redirecting requests to appropriate microservice as well as providing authentication
where it validates JWT token.

### Discovery-srvice
Eureka service which holds information of all other microservices. Microservices will registrate on the eureka server providing the server with name, ip and port.

### Client
Developed in Angular, provides an UI for the user and communication between backend micorservices.
