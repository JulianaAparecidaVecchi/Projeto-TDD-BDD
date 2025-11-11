CREATE DATABASE biblioteca;
USE biblioteca;

-- Tabela de usuários
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL
);

-- Tabela de livros
CREATE TABLE livro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    categoria VARCHAR(100),
    status_leitura ENUM('não lido', 'lendo', 'lido') DEFAULT 'não lido',
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

-- Tabela de avaliações
CREATE TABLE avaliacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_livro INT,
    nota INT CHECK (nota BETWEEN 0 AND 5),
    FOREIGN KEY (id_livro) REFERENCES livro(id)
);
