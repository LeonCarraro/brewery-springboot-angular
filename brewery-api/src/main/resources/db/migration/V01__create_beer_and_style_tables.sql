CREATE TABLE tb_style (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR (50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE tb_beer (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    sku VARCHAR (6) NOT NULL,
    name VARCHAR (50) NOT NULL,
    description VARCHAR (255) NOT NULL,
    volume MEDIUMINT NOT NULL,
    value DECIMAL (11,2) NOT NULL,
    alcohol_content DECIMAL (5,2) NOT NULL,
    comission DECIMAL (5,2) NOT NULL,
    stock_quantity INT NOT NULL,
    origin VARCHAR (50) NOT NULL,
    flavor VARCHAR (50) NOT NULL,
    style_id BIGINT NOT NULL,

    FOREIGN KEY (style_id) REFERENCES tb_style (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
