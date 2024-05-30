insert into cozinha (id, nome) values (1, 'Brasileira');
insert into cozinha (id, nome) values (2, 'Tailandesa');
insert into cozinha (id, nome) values (3, 'Mexicana');

insert into estado (nome) values ('Minas Gerais');
insert into estado (nome) values ('Sao Paulo');
insert into estado (nome) values ('Santa Catarina');

insert into cidade (nome, estado_id) values ('Belo Horizonte', 1);
insert into cidade (nome, estado_id) values ('Campinas', 2);
insert into cidade (nome, estado_id) values ('Florianopolis', 3);

insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro) values (1, 'Churrascaria Batista', 4.0, 1, 1, '53523-100', 'Rua Minas Gerais', '123', null, 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro) values (2, 'Restaurante Abaete', 3.0, 1, 1, '53523-160', 'Rua Pio XII', '378', "Andar 2", 'Santa Maria');
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro) values (3, 'Thai Foodtruck', 3.0, 2, 1, '78525-090', 'Praça Santuário', '1180', null', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro) values (4, 'Taquitos', 3.0, 3, 1,'45090-080', 'Rua do Peri', '90', null, 'São João');

insert into forma_pagamento (descricao) values ('Cartão de crédito');
insert into forma_pagamento (descricao) values ('Cartão de débito');
insert into forma_pagamento (descricao) values ('PIX');
insert into forma_pagamento (descricao) values ('Dinheiro');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1,3), (1,4);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (2, 1), (2, 2), (2,3), (2,4);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (3, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (4, 2), (4, 3), (4,4);