create table if not exists t_products (
  id SERIAL PRIMARY KEY,
  version int,
  name varchar,
  weight float
);