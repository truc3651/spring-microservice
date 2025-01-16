create table if not exists t_reviews (
  id SERIAL PRIMARY KEY,
  version int,
  product_id int,
  author varchar,
  subject varchar,
  content varchar
);