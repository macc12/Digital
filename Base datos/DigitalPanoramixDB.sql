Create database DigitalPanoramix;
use DigitalPanoramix;  
  create table Consultorio(
  NombreConsultorio VARCHAR(30) not null,
  IdConsultorio Int not null,Primary Key(IdConsultorio)
);

insert into Consultorio values ('martin', 10001);
insert into Consultorio values ('carlos', 10001);
insert into Consultorio values ('pepe', 10001);

create table Proveedor(
  NombreProveedor VARCHAR(20) not null,
  IdProveedor int not null,
  Primary key(IdProveedor),
  Telefono int not null, 
  Direccion varchar(20),
  IdConsultorio Int not null,
  FOREIGN key (IdConsultorio) REFERENCES Consultorio (IdConsultorio)
);

create table Producto(
  NombreProducto VARCHAR(30) not null,
  IdProducto int not null,
  Primary key(IdProducto),
  Precio double not null,
  Estado VARCHAR(40) not null,
  Cantidad int not null
);

create table Cliente(
  NombreCliente VARCHAR(20) not null,
  ApellidoCliente VARCHAR(20) not null,
  IdCliente int not null,
  Primary key(IdCliente),
  HistoriaClinica VARCHAR(100) not null  
);

create table Trabajador(
  NombreTrabajador VARCHAR(20) not null,
  ApellidoTrabajador VARCHAR(20) not null,
  IdTrabajador int(10) not null,
  Primary key(IdTrabajador),
  Cargo VARCHAR(30) not null,
  Sueldo double not null,
  DiasTrabajados int not null,
  Deuda double not null,
  Pagado double not null
);

create table usuario(
	nombreUser     varchar(20) not null primary key,
	contrase       varchar(20) not null,
	tipouser       varchar(20) not null
);

insert into usuario values('maria','maria','Administrador');

create table citas(
	IdCita  varchar(20) not null primary key,
	IdCliente int not null,
	IdTrabajador int not null,
	Estado varchar(20) not null,
	Dia  varchar(20) not null,
	hora varchar(20) not null,
	Descripcion varchar(20) not null,
	INDEX (IdCliente),
	INDEX (IdTrabajador),
   FOREIGN KEY (IdCliente) REFERENCES 	Cliente (IdCliente),
   FOREIGN KEY (IdTrabajador) REFERENCES Trabajador (IdTrabajador)
);

create table quejas(
	correo           varchar(50),
	texto            varchar(100)
);

