create table if not exists t_recommendations (
  id SERIAL PRIMARY KEY,
  version int,
  product_id int,
  author varchar,
  rating int,
  content varchar
);