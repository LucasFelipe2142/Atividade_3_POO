-- Criação da tabela contracts
CREATE TABLE contracts (
  id INT PRIMARY KEY AUTO_INCREMENT,
  redacao VARCHAR(100000) NOT NULL,
  ultimaAtualizacao DATE NOT NULL,
  cliente_id INT,
  FOREIGN KEY (cliente_id) REFERENCES clients(id)
);

-- Criação da tabela clients
CREATE TABLE clients (
  id INT PRIMARY KEY AUTO_INCREMENT,
  cpf BIGINT NOT NULL,
  name VARCHAR(45) NOT NULL
);
