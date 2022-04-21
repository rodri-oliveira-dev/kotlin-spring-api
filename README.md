# Estudo de Kotlin com Spring

Tecnologias usadas no estudo:

- Kotlin
- Spring
- Hibernate
- MySQL
- Swagger
- JWT
- Docker
- Docker Compose

API de estudo representando o cadastro de itens de um Fórum:

- Usuário
- Curso
- Tópicos
- Respostas

A API está com autenticação JWT ativa. Para acessar os end-points é necessário autenticar um usuário. Os dados para login e todas as rotas estão na [Collection do Postman](./postman/).

Para subir o ambiente efetue o build do projeto e em seguida acesse a pasta raiz do projeto (onde se encontra o arquivo do dockerfile e execute o comando:


```PowerShell
docker-compose up -d
```

A API está disponível através da porta 8030, o banco de dados MySQL está disponível para acesso local, caso deseje, na porta 3306.
