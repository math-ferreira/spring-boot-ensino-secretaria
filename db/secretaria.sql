CREATE DATABASE  IF NOT EXISTS `secretaria`

drop table if exists tb_aluno;
drop table if exists tb_contato;
drop table if exists tb_endereco;
drop table if exists tb_perfil;
drop table if exists tb_usuario;
drop table if exists tb_usuario_perfil;

create table tb_aluno (
       id bigint not null auto_increment,
        idade integer not null,
        nome varchar(255),
        numero_matricula varchar(255),
        periodo integer not null,
        contato_id bigint not null,
        endereco_id bigint not null,
        primary key (id)
) engine=InnoDB;

create table tb_contato (
       id bigint not null auto_increment,
        email varchar(255),
        telefone varchar(255),
        tipo_telefone varchar(255),
        primary key (id)
) engine=InnoDB;

create table tb_endereco (
       id bigint not null auto_increment,
        bairro varchar(255),
        cep varchar(255),
        cidade varchar(255),
        estado varchar(255),
        numero integer not null,
        rua varchar(255),
        primary key (id)
) engine=InnoDB;

create table tb_perfil (
       id bigint not null auto_increment,
        nome_perfil varchar(255),
        primary key (id)
) engine=InnoDB;

create table tb_usuario (
       id bigint not null auto_increment,
        email varchar(255),
        nome_usuario varchar(255),
        senha varchar(255),
        primary key (id)
) engine=InnoDB;

create table tb_usuario_perfil (
       usuario_id bigint not null,
        perfil_id bigint not null
) engine=InnoDB;

alter table tb_aluno
       add constraint FKoaujc7l53976h81eq8x1dbut9
       foreign key (contato_id)
       references tb_contato (id);

alter table tb_aluno
       add constraint FKpvtf9wys9yxfb4ywleaarewmn
       foreign key (endereco_id)
       references tb_endereco (id);

alter table tb_usuario_perfil
       add constraint FK4j7t1t7n0p4lm8jatjwav1i8l
       foreign key (perfil_id)
       references tb_perfil (id);

alter table tb_usuario_perfil
       add constraint FKjip5y5qe5hsqpapmi8pt29neu
       foreign key (usuario_id)
       references tb_usuario (id);