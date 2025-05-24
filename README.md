# Sistema de Gestão Bancária

Este projeto é um sistema de gestão bancária desenvolvido com Spring Boot. Ele permite o cadastro de usuários,
autenticação via JWT, e o gerenciamento de contas bancárias, respeitando as regras de acesso por usuário.

## 📌 Funcionalidades

- Cadastro de usuários
- Autenticação com JWT
- Cadastro e consulta de contas bancárias
- Cada usuário só pode acessar as contas que ele mesmo criou

## 🔒 Regras de Acesso

- A única URL pública (sem autenticação) é:  /api/user/**
- Todas as outras URLs exigem **autenticação JWT**
- Um usuário pode cadastrar várias contas
- Cada usuário só pode acessar suas próprias contas

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.4.6
- Spring Security
- JWT (JSON Web Token)
- JPA / Hibernate
- Banco em memória H2

## 📩 Coleção de Requisições para Postman

Para facilitar os testes, disponibilizamos uma coleção de requisições para o Postman:

🔗 [Acessar Coleção Postman](https://drive.google.com/drive/u/0/folders/1iDIz-24Cpil9AFxhEcY--cpb6GW6130v)
