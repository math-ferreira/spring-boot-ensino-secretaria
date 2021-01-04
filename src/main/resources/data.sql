INSERT INTO `tb_endereco`(`bairro`, `cep`, `cidade`, `estado`, `numero`, `rua`) VALUES ('Vila São Pedro','01387645','Santo Andre','SP',128,'Rua Abolicao');
INSERT INTO `tb_contato`(`email`, `telefone`, `tipo_telefone`) VALUES ('mat.s.ferreira@gmail.com','11981993607','celular');
INSERT INTO `tb_aluno`(`idade`, `nome`, `numero_matricula`, `periodo`, `contato_id`, `endereco_id`) VALUES (17,'Matheus da Silva Ferreira',11049985,1,1,1);

INSERT INTO `tb_endereco`(`bairro`, `cep`, `cidade`, `estado`, `numero`, `rua`) VALUES ('Ipe 1','12445887','Taubaté','SP',055,'Av quartenario');
INSERT INTO `tb_contato`(`email`, `telefone`, `tipo_telefone`) VALUES ('pradomaysa@gmail.com','1925854784','celular');
INSERT INTO `tb_aluno`(`idade`, `nome`, `numero_matricula`, `periodo`, `contato_id`, `endereco_id`) VALUES (15,'Maysa Nascimento do Prado',09872014,5,2,2);

INSERT INTO `tb_endereco`(`bairro`, `cep`, `cidade`, `estado`, `numero`, `rua`) VALUES ('Alpes da Barra','89855470','Botafogo','RJ',9666,'Praca Armando Nogueira');
INSERT INTO `tb_contato`(`email`, `telefone`, `tipo_telefone`) VALUES ('antonio@uol.com','21487542447','casa');
INSERT INTO `tb_aluno`(`idade`, `nome`, `numero_matricula`, `periodo`, `contato_id`, `endereco_id`) VALUES (13,'Antonio Ferreira Miron',09872014,8,3,3);

INSERT INTO `tb_usuario`(`email`, `nome_usuario`, `senha`) VALUES ('mat.s.ferreira@gmail.com', 'matheus', '$2a$10$DzQkbtfkG06K/nBuaf0Aq.e6JXC1rZs1bfm2hlidU0lLNiuGfN6sC');