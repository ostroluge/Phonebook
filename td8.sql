drop table entree;
drop table numero;

create table entree (
	id int not null auto_increment,
	nom varchar(20) not null,
	prenom varchar(20) not null,
	constraint pk_entree primary key (id)
);

create table numero (
	code varchar(30) not null,
	value varchar(20) not null,
	id_entree int(5) not null,
	constraint pk_numero primary key (code, value),
	constraint fk_entree foreign key (id_entree) references entree(id)
	on delete cascade
	on update cascade
);

insert into entree (nom, prenom) values ("FLAMEN", "Julien");
insert into entree (nom, prenom) values ("GOUBEL", "Floriane");
insert into entree (nom, prenom) values ("BRISSE", "Teo");

insert into numero values('Pro', '0654632390', 9);
insert into numero values('Perso', '0654616390', 9);

insert into numero values('QlickNumber', '0637632390', 10);
insert into numero values('Perso', '0653632399', 10);

insert into numero values('Pro', '0624642390', 11);
insert into numero values('Perso', '0664632590', 11);