DELETE FROM tb_beer;
DELETE FROM tb_style;

ALTER TABLE tb_style AUTO_INCREMENT = 1;
ALTER TABLE tb_beer AUTO_INCREMENT = 1;

INSERT INTO tb_style (id, name)
    VALUES  (null, 'Amber Lager'),
            (null, 'Dark Lager'),
            (null, 'Pale Lager'),
            (null, 'Pilsner');

INSERT INTO tb_beer (id, sku, name, description, volume, value, alcohol_content, comission, stock_quantity, origin, flavor, style_id)
    VALUES  (null, 'AA0001', 'Cerveja Patagonia', 'Os maltes Melanoidin, Carared e Carapils usados em sua produção, lhe conferem um aroma sutil de caramelo e boa formação de espuma. Deguste esta cerveja com bacalhau, anchovas ou frutos do mar para realçar ainda mais sua refrescância.',
        740, 15.99, 4.5, 12.5, 50, 'NACIONAL', 'ADOCICADA', 1),
            (null, 'AA0002', 'Cerveja Patagonia', 'Os maltes Melanoidin, Carared e Carapils usados em sua produção, lhe conferem um aroma sutil de caramelo e boa formação de espuma. Deguste esta cerveja com bacalhau, anchovas ou frutos do mar para realçar ainda mais sua refrescância.',
        473, 7.99, 4.5, 5.0, 110, 'NACIONAL', 'ADOCICADA', 1),
            (null, 'AA0003', 'Cerveja Brahma', 'A Brahma Extra Dark Lager é uma cerveja escura que destaca os sabores do malte Munique, trazendo notas de tosta e chocolate. Receita premiada no IBC.',
        355, 3.79, 4.8, 5.0, 20, 'NACIONAL', 'FORTE', 2),
            (null, 'AA0004', 'Cerveja Pilsner Urquell', 'Primeira Pilsner dourada - criada em 1842 e ainda é produzida na mesma cervejaria, com a mesma água de Plzen, o mesmo lúpulo Saaz da cidade de Zatec, a mesma cevada checa da Morávia e a mesma levedura usada na receita original.',
        500, 22.86, 4.4, 12.5, 20, 'INTERNACIONAL', 'FRUTADA', 4);
