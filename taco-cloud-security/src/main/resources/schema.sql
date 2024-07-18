-- Crear tabla Taco_Order
create table if not exists Taco_Order (
    id identity primary key,
    delivery_Name varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City varchar(50) not null,
    delivery_State varchar(2) not null,
    delivery_Zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
);

-- Crear tabla Taco
create table if not exists Taco (
    id identity primary key,
    name varchar(50) not null,
    createdAt timestamp not null,
    taco_order bigint not null,
    foreign key (taco_order) references Taco_Order(id)
);

-- Crear tabla Ingredient
create table if not exists Ingredient (
    id varchar(4) not null primary key,
    name varchar(25) not null,
    type varchar(10) not null
);

-- Crear tabla intermedia para la relación muchos a muchos entre Taco y Ingredient
create table if not exists Taco_Ingredients (
    taco_id bigint not null,
    ingredient_id varchar(4) not null,
    foreign key (taco_id) references Taco(id),
    foreign key (ingredient_id) references Ingredient(id),
    primary key (taco_id, ingredient_id)
);

-- Crear tabla User
create table if not exists Users (
    id identity primary key,
    username varchar(50) not null,
    password varchar(50) not null,
    fullname varchar(50),
    street varchar(50),
    city varchar(50),
    state varchar(2),
    zip varchar(10),
    phone varchar(10)
);
