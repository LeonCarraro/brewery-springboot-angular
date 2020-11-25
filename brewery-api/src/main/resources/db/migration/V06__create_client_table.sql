CREATE TABLE tb_client (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR (80) NOT NULL,
    client_type VARCHAR (10) NOT NULL,
    document VARCHAR (20) NOT NULL,
    phone_number VARCHAR (15),
    email VARCHAR (80) NOT NULL,
    cep VARCHAR (10) NOT NULL,
    number VARCHAR (10) NOT NULL,
    complement VARCHAR (20),
    city_id BIGINT NOT NULL,

    FOREIGN KEY (city_id) REFERENCES tb_city (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
