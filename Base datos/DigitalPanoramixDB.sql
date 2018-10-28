Create database DigitalPanoramix;
use DigitalPanoramix;  
  create table Consultorio(
  NombreConsultorio VARCHAR(30) not null,
  IdConsultorio Int not null,Primary Key(IdConsultorio)
);
create table Proveedor(
  NombreProveedor VARCHAR(20) not null,
  IdProveedor int not null,
  Primary key(IdProveedor),
  Telefono int not null, 
  Direccion varchar(20)
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

