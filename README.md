<h1 align="center">
  API de produtos com RabbitMQ   <br /> <img align="center" alt="Java" height="50px" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-plain.svg" />
  <img align="center" alt="Docker" height="50px" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-plain.svg" />
</h1>

## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [RabbitMQ](https://rabbitmq.com/)
- [Docker](https://hub.docker.com/)

## Práticas adotadas

- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Geração automática do Swagger
- RabbitMQ
- Docker
- Banco de dados H2

## Como Executar

- Clonar repositório git
- Tenha o Docker em sua maquina para iniciar o RabbitMQ
  
- 1º Passo - No terminal execute o comando para subir o container
```
$ docker-compose up 
```
- Para parar o container execute
```
$ docker-compose down
```

- 2º Passo - Start a aplicação Springboot
- 3º Passo - O RabbitMQ ficara disponivel em localhost:15672

O Swagger poderá ser visualizado em [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

