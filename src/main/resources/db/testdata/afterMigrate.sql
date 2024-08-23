set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;

insert into cozinha (id, nome) values (1, 'Brasileira');
insert into cozinha (id, nome) values (2, 'Tailandesa');
insert into cozinha (id, nome) values (3, 'Mexicana');

insert into estado (nome) values ('Minas Gerais');
insert into estado (nome) values ('Sao Paulo');
insert into estado (nome) values ('Santa Catarina');

insert into cidade (nome, estado_id) values ('Belo Horizonte', 1);
insert into cidade (nome, estado_id) values ('Campinas', 2);
insert into cidade (nome, estado_id) values ('Florianopolis', 3);

insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, data_hora_cadastro, data_hora_atualizacao, ativo, aberto) values (1, 'Churrascaria Batista', 4.0, 1, 1, '53523-100', 'Rua Minas Gerais', '123', null, 'Centro', utc_timestamp, utc_timestamp, 1, 0);
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, data_hora_cadastro, data_hora_atualizacao, ativo, aberto) values (2, 'Restaurante Abaete', 3.0, 1, 1, '53523-160', 'Rua Pio XII', '378', "Andar 2", 'Santa Maria', utc_timestamp, utc_timestamp, 1, 0);
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, data_hora_cadastro, data_hora_atualizacao, ativo, aberto) values (3, 'Thai Foodtruck', 3.0, 2, 1, '78525-090', 'Praça Santuário', '1180', null, 'Centro', utc_timestamp, utc_timestamp, 1, 0);
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, data_hora_cadastro, data_hora_atualizacao, ativo, aberto) values (4, 'Taquitos', 3.0, 3, 1,'45090-080', 'Rua do Peri', '90', null, 'São João', utc_timestamp, utc_timestamp, 1, 0);

insert into forma_pagamento (descricao) values ('Cartão de crédito');
insert into forma_pagamento (descricao) values ('Cartão de débito');
insert into forma_pagamento (descricao) values ('PIX');
insert into forma_pagamento (descricao) values ('Dinheiro');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1,3), (1,4);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (2, 1), (2, 2), (2,3), (2,4);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (3, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (4, 2), (4, 3), (4,4);

insert into produto (restaurante_id, nome, descricao, preco, ativo) values (1, 'Churrasco', 'Porção de churrasco com carnes variadas', 45.0, true);
insert into produto (restaurante_id, nome, descricao, preco, ativo) values (1, 'Fraldinha', 'Porção de fraldinha', 50.0, true);
insert into produto (restaurante_id, nome, descricao, preco, ativo) values (2, 'Marmitex pequeno', 'Arroz, feijão, carne do dia e salada. Serve 1 pessoa', 20.0, true);
insert into produto (restaurante_id, nome, descricao, preco, ativo) values (2, 'Marmitex grande', 'Arroz, feijão, carne do dia e salada. Serve 2 pessoas', 25.0, true);
insert into produto (restaurante_id, nome, descricao, preco, ativo) values (3, 'Pad Thai', "Macarrão frito com camarão e especiarias", 38.00, true);
insert into produto (restaurante_id, nome, descricao, preco, ativo) values (3, 'Tom Yum', "Ensopado picante com camarão, cogumelos e especiarias", 34.50, true);
insert into produto (restaurante_id, nome, descricao, preco, ativo) values (3, 'Som Tam', "Salada picante de mamão verde", 27.50, true);
insert into produto (restaurante_id, nome, descricao, preco, ativo) values (4, 'Taquitos de carne bovina', 'Tacos recheados com carne moída, cebola e molho spice', 10.0, true);
insert into produto (restaurante_id, nome, descricao, preco, ativo) values (4, 'Taquitos vegetarianos', 'Tacos recheados com feijão, cebola e molho spice', 10.0, true);
insert into produto (restaurante_id, nome, descricao, preco, ativo) values (4, 'Guaraná Lata', null, 5.0, false);

insert into grupo (nome) values ('Administração');
insert into grupo (nome) values ('Operacional');
insert into grupo (nome) values ('Estoque');

insert into usuario (nome, email, senha, data_hora_cadastro) values ('João da Silva', 'joao_silva@algafood.com', '123', CURRENT_DATE);
insert into usuario (nome, email, senha, data_hora_cadastro) values ('Marta Vania Rocha', 'martav@algafood.com', '123', CURRENT_DATE);
insert into usuario (nome, email, senha, data_hora_cadastro) values ('Elisa Fernandes Oliveira', 'elisa_oliveirafer@algafood.com', '123', CURRENT_DATE);
