insert into cozinha (id, nome) values (1, 'Brasileira');
insert into cozinha (id, nome) values (2, 'Tailandesa');
insert into cozinha (id, nome) values (3, 'Mexicana');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Churrascaria Batista', 4.0, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Restaurante Abaete', 3.0, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Foodtruck', 3.0, 2);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Taquitos', 3.0, 3);

insert into estado (nome) values ('Minas Gerais');
insert into estado (nome) values ('Sao Paulo');
insert into estado (nome) values ('Santa Catarina');

insert into cidade (nome, estado_id) values ('Belo Horizonte', 1);
insert into cidade (nome, estado_id) values ('Campinas', 2);
insert into cidade (nome, estado_id) values ('Florianopolis', 3);