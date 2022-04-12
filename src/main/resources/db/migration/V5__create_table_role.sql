create table role(
    id bigint not null auto_increment,
    nome varchar(50) not null,
    primary key(id)
);

INSERT INTO ROLE (id, nome) VALUES (1,'LEITURA_ESCRITA');
INSERT INTO ROLE (id, nome) VALUES (2,'SOMENTE_LEITURA');

