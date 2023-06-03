# Projeto Atividade Avaliativa

Este projeto é uma atividade avaliativa para fixar o conteúdo abordado sobre Mapeamento Objeto-Relacional envolvendo relacionamentos.

## Tecnologias utilizadas

- Java
- MySQL
- Maven

## Configuração do banco de dados

Antes de executar o projeto, é necessário configurar o banco de dados MySQL e criar as tabelas necessárias. Siga os passos abaixo:

1. Instale o MySQL em seu sistema, caso ainda não esteja instalado.
2. Crie um banco de dados com o nome `nome_do_banco_de_dados`.
3. Execute os seguintes comandos SQL para criar as tabelas necessárias:

```sql
CREATE TABLE contracts (
  id INT PRIMARY KEY AUTO_INCREMENT,
  redacao VARCHAR(100000) NOT NULL,
  ultimaAtualizacao DATE NOT NULL,
  cliente_id INT,
  FOREIGN KEY (cliente_id) REFERENCES clients(id)
);

CREATE TABLE clients (
  id INT PRIMARY KEY AUTO_INCREMENT,
  cpf BIGINT NOT NULL,
  name VARCHAR(45) NOT NULL
);
```


4. Atualize as informações de conexão com o banco de dados no arquivo `SQLConection.java` no pacote `Sql`.

## Executando o projeto

Para executar o projeto, siga as instruções abaixo:

1. Certifique-se de ter o Java, o Maven e o MySQL instalados em seu sistema.
2. Faça o clone do repositório para o seu ambiente local.
3. Abra o projeto em sua IDE de preferência.
4. Certifique-se de ter configurado corretamente as informações de conexão com o banco de dados no arquivo `SQLConection.java`.
5. Execute o arquivo `Main.java` para iniciar o programa.
6. O programa realizará a criação de clientes e contratos, e exibirá mensagens no console indicando as ações sendo realizadas.

## Contato

- Autor: Lucas Felipe
- Email: lucasfelicio378@gmail.com
