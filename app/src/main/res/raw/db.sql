CREATE TABLE marcas (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR (45) NOT NULL UNIQUE
);

INSERT INTO marcas (nombre) VALUES ("Audi"),("BMW"),("Toyota"),("Nissan"),("Hyundai");

CREATE TABLE colores (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    descripcion VARCHAR (45) NOT NULL UNIQUE
);

INSERT INTO colores (descripcion) VALUES ("Rojo"),("Azul"),("Silver"),("Cafe");

CREATE TABLE tipo_automovil (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    descripcion VARCHAR (45)
);

INSERT INTO tipo_automovil (descripcion) VALUES ("Sedan"),("Camion"),("Pick up"),("CAMIONETA");

CREATE TABLE role (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    role VARCHAR (45) NOT NULL UNIQUE
);

INSERT INTO ROLE (role) VALUES ("ADMIN"),("CLIENT");

CREATE TABLE usuario (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    nombres VARCHAR (45) NOT NULL,
    apellidos VARCHAR (45) NOT NULL,
    email VARCHAR (45) NOT NULL,
    user VARCHAR (45) UNIQUE NOT NULL,
    password VARCHAR (45) NOT NULL,
    fk_usuario_role_idx INT,
    FOREIGN KEY (fk_usuario_role_idx) REFERENCES role (id)
);

INSERT INTO usuario (nombres,apellidos,email,user,password,fk_usuario_role_idx) VALUES ("Alejandro","Alejo","alejandro714@gmail.com","alejo","12345",2) , ("Matias","Gaitan","matthew25@gmail.com","Mat25","123456",1);

CREATE TABLE automovil (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    modelo VARCHAR (45),
    numero_vin VARCHAR (45),
    numero_chasis VARCHAR (45),
    numero_motor VARCHAR (45),
    numero_asientos INTEGER,
    anio INTEGER,
    capacidad_asientos INT,
    precio FLOAT (10,2),
    uri_img VARCHAR (45) NULL,
    descripcion VARCHAR (45) NULL,
    fk_automovil_marcas_idx INT,
    fk_automovil_tipo_automovil INT,
    fk_automovil_colores INT,
    FOREIGN KEY (fk_automovil_marcas_idx) REFERENCES marcas (id),
    FOREIGN KEY (fk_automovil_tipo_automovil) REFERENCES tipo_automovil (id),
    FOREIGN KEY (fk_automovil_colores) REFERENCES colores (id)
);

INSERT INTO automovil (modelo,numero_vin,numero_chasis,numero_motor,numero_asientos,anio,capacidad_asientos,precio,uri_img,descripcion,fk_automovil_marcas_idx,fk_automovil_tipo_automovil,fk_automovil_colores)
VALUES ("Elantra","iso20123","22daa","sdwed",5,2010,5,7500,"","Hyundai Elantra perfect status",5,1,1), ("Corolla","s2sda20123","221sd2daa","92kisd",4,2015,4,9000,"","Toyta Corolla perfect status",3,2,4);

CREATE TABLE favoritos_automovil (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    fk_favoritos_automovil_usuario_idx INT,
    fk_favoritos_automovil_automovil_idx INT,
    fecha_agregado DATE,
    FOREIGN KEY (fk_favoritos_automovil_usuario_idx) REFERENCES usuario (id),
    FOREIGN KEY (fk_favoritos_automovil_automovil_idx) REFERENCES automovil (id)
);
