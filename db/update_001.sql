CREATE TABLE post (
   id SERIAL PRIMARY KEY,
   name TEXT,
   description TEXT,
   city_id int,
   created DATE,
   visible boolean
);