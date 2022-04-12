create table usuario (
    id bigint not null auto_increment,
    nome varchar(50) not null,
    email varchar(50) not null,
    password varchar(500) not null,
    primary key(id)
);

insert into usuario values(1, 'Rodrigo de Oliveira', 'rodrigo@email.com','$2a$12$QonjujO3.s6Lw2u4R7d6h.VTAk8v36PBb.acmQDF//eLUM.m4vNKG');
