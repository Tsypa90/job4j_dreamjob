CREATE TABLE post (
   id SERIAL PRIMARY KEY,
   name TEXT,
   description TEXT,
   city_id int,
   created DATE,
   visible boolean
);

create table candidates (
    id serial primary key,
    name text,
    description text,
    created date,
    photo bytea[]
);