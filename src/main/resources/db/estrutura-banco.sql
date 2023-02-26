create table usuarios(

    id bigint not null auto_increment,
    nome varchar(50) not null,
    login varchar(20) not null unique,
    email varchar(255) not null,
    senha varchar(16) not null,
    data_nascimento date,

    primary key(id)
);
