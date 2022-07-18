CREATE TABLE if not exists  post (
   id SERIAL PRIMARY KEY,
   name TEXT,
   description TEXT,
   city_id int,
   created DATE,
   visible boolean
);

create table if not exists candidates (
    id serial PRIMARY KEY,
    name text,
    description text,
    created DATE,
    photo bytea
);