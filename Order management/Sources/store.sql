-- drop database store;
create database if not exists store;
create table clientul (id int, nume varchar(45), oras varchar(45), primary key(id));
create table produs (id int, nume varchar(45), cantitate int, pret float, primary key(id));
create table cumpara (id int, idClient int, idProdus int, cantitate int, primary key(id));

alter table cumpara add foreign key(idClient) references clientul(id);
alter table cumpara add foreign key(idProdus) references produs(id);
