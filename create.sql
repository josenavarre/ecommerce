create table carrito (uuid uuid not null, primary key (uuid))
create table producto (uuid uuid not null, descripcion varchar(255), nombre varchar(255), precio int8, sku varchar(255), primary key (uuid))
create table producto_comprado (uuid uuid not null, cantidad int4 not null, estado bytea, producto_uuid uuid, primary key (uuid))
alter table producto_comprado add constraint FK52eovx52cpet9mg997g62xp3q foreign key (producto_uuid) references producto
alter table producto_comprado add constraint FKggaw4jl45yw0oh9o2qdu52mhq foreign key (uuid) references carrito
