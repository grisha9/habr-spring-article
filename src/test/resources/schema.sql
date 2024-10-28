create table if not exists books (
  id bigserial not null,
  name varchar not null,
  isbn varchar not null,
  counter int not null default 0,
  primary key (id),
  UNIQUE (isbn)
);