CREATE TABLE tb_user (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR (100) NOT NULL,
    email VARCHAR (100) NOT NULL,
    password VARCHAR (200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
