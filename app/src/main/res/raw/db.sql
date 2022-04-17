create table tratamiento(
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR (45) NOT NULL UNIQUE,
    precio FLOAT (10,2) not null
);

create table horario(
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    hora_inicio char(5),
    hora_fin char(5)
);

create table cita(
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    fecha date not null,
    cliente varchar(100) not null,
    horario_id integer,
    tratamiento_id integer,
    foreign key (horario_id) references horario(id),
    foreign key (tratamiento_id) references tratamiento(id)
);

insert into tratamiento(nombre, precio) values
('Manicure', 25), ('Pedicure', 19), ('Alisado', 24), ('Baño acrilico natural', 8),
('Corte de cabello', 5), ('Mechas', 45), ('Planchado bioionic', 6), ('Uñas acrilicas ballerinas', 25),
('Tinte', 19), ('Botox capilar', 25);

insert into horario(hora_inicio, hora_fin) values
('08:15', '09:45'), ('10:15','11:45'), ('13:15', '14:45'), ('15:15', '16:45');
