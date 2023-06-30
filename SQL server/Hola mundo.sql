create database holamundo;
show databases;
use holamundo;
CREATE TABLE animales (
id int ,
tipo varchar(255),
estado varchar (255),
PRIMARY KEY (id)
);

-- INSERT INTO animales (tipo, estado) VALUES ('Chanchito','Feliz');

ALTER TABLE animales MODIFY COLUMN id int auto_increment;

show create table animales;

CREATE TABLE `animales` (
   `id` int NOT NULL AUTO_INCREMENT,
   `tipo` varchar(255) DEFAULT NULL,
   `estado` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ;
 
INSERT INTO animales (tipo, estado) VALUES ('Chanchito','Feliz');
INSERT INTO animales (tipo, estado) VALUES ('dragon','Feliz');
INSERT INTO animales (tipo, estado) VALUES ('felipe','triste'); 

select * from animales;
select * from animales where id = 1;
select * from animales where estado = 'feliz' and tipo = 'felipe';

update animales set estado = "feliz" where id = 4;

select * from animales;

delete from animales where id = 2;

select * from animales;

update animales set estado = 'triste' where tipo = 'dragon';

