create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensagem varchar(1000) not null,
    data datetime not null,
    estado_topico varchar(50) not null,
    autor varchar(100) not null,
    curso varchar(100) not null,

    primary key(id)
);