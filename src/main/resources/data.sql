INSERT INTO `tb_endereco`(`bairro`, `cep`, `cidade`, `estado`, `numero`, `rua`) VALUES ('Vila São Pedro','01387645','Santo Andre','SP',128,'Rua Abolicao')
INSERT INTO `tb_contato`(`email`, `telefone`, `tipo_telefone`) VALUES ('mat.s.ferreira@gmail.com','11981993607','celular')
INSERT INTO `tb_aluno`(`idade`, `nome`, `numero_matricula`, `periodo`, `contato_id`, `endereco_id`) VALUES (25,'Matheus da Silva Ferreira',11049985,1,1,1)
INSERT INTO `tb_usuario_details`(`id`, `email`, `nome_usuario`, `senha`) VALUES (1,'mat.s.ferreira@gmail.com', 'matheus', '$2a$10$DzQkbtfkG06K/nBuaf0Aq.e6JXC1rZs1bfm2hlidU0lLNiuGfN6sC')