DELETE FROM tb_beer;
DELETE FROM tb_style;
DELETE FROM tb_user;

ALTER TABLE tb_style AUTO_INCREMENT = 1;
ALTER TABLE tb_beer AUTO_INCREMENT = 1;
ALTER TABLE tb_user AUTO_INCREMENT = 1;

INSERT INTO tb_style (id, name)
    VALUES  (null, 'Amber Lager'),
            (null, 'Dark Lager'),
            (null, 'Pale Lager'),
            (null, 'Pilsner');

INSERT INTO tb_beer (id, sku, name, description, volume, value, alcohol_content, comission, stock_quantity, origin, flavor, style_id)
    VALUES  (null, 'AA0001', 'Cerveja Patagonia', null, 740, 15.99, 4.5, 12.5, 50, 'NACIONAL', 'ADOCICADA', 1),
            (null, 'AA0002', 'Cerveja Patagonia', null, 473, 7.99, 4.5, 5, 110, 'NACIONAL', 'ADOCICADA', 1),
            (null, 'AA0003', 'Cerveja Brahma', null, 355, 3.79, 4.8, 5, 20, 'NACIONAL', 'FORTE', 2),
            (null, 'AA0004', 'Cerveja Pilsner Urquell', null, 500, 22.86, 4.4, 12.5, 20, 'INTERNACIONAL', 'FORTE', 4),
            (null, 'AA0005', 'Cerveja Colorado', null, 740, 14.99, 6.5, 15, 100, 'NACIONAL', 'FRUTADA', 3),
            (null, 'AA0006', 'Cerveja Colorado', null, 350, 10, 6.5, 12.5, 35, 'NACIONAL', 'SUAVE', 3),
            (null, 'AA0007', 'Cerveja Colorado', null, 1000, 20, 6.5, 25, 200, 'NACIONAL', 'FORTE', 2),
            (null, 'AA0008', 'Cerveja Itaipava', null, 269, 1.69, 4.5, 5, 12, 'NACIONAL', 'SUAVE', 4),
            (null, 'AA0009', 'Cerveja Skol', null, 1000, 12, 5, 12.5, 120, 'NACIONAL', 'SUAVE', 4);

INSERT INTO tb_user (id, name, email, password)
    VALUES (null, 'Leonardo Oliveira', 'lcarraro.oliveira@brewery.com', '$2a$10$9iWF45eS1NqsvpkUFva.0OplkXk8VEmQJ429Q9ZSFfNo31I4ikhiO')
