insert into cliente (id, nome, cpf, email, telefone, endereco) values ('93c8ea60-833d-4834-8f03-30798252ec83', 'Fulano', '12548796325', 'fulano@teste.com.br', '11999999999', 'Rua A 120');
insert into carrinho(id, cliente_id) values ('b57f5067-1f5c-4abd-91d7-2b51ddf63655', '93c8ea60-833d-4834-8f03-30798252ec83');
insert into categoria(id, nome, descricao) values ('ff8fa781-6a7b-479e-a39b-f2d5bbd231a8', 'LIVROS', 'LIVROS');

insert into produto(id, nome, descricao, valor) values ('72374b6f-cbd1-4315-b5ec-f4d9cbedfad1', 'LIVRO XYZ', 'LIVRO COM 500 Páginas', 39.90);
insert into produto_categoria(produto_id, categoria_id) values ('72374b6f-cbd1-4315-b5ec-f4d9cbedfad1', 'ff8fa781-6a7b-479e-a39b-f2d5bbd231a8');

insert into produto(id, nome, descricao, valor) values ('297a67e0-bf28-4955-9996-f6fb98eb473c', 'LIVRO ABC', 'LIVRO COM 100 Páginas ABC', 50.90);
insert into produto_categoria(produto_id, categoria_id) values ('297a67e0-bf28-4955-9996-f6fb98eb473c', 'ff8fa781-6a7b-479e-a39b-f2d5bbd231a8');

insert into produto(id, nome, descricao, valor) values ('2c388506-e385-42c8-8eb7-7c5f43cc8d2d', 'LIVRO ZPL', 'LIVRO COM 20 Páginas ZPL', 60.90);
insert into produto_categoria(produto_id, categoria_id) values ('2c388506-e385-42c8-8eb7-7c5f43cc8d2d', 'ff8fa781-6a7b-479e-a39b-f2d5bbd231a8');
