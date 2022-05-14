CREATE TABLE IF NOT EXISTS places
(
  id          BIGSERIAL NOT NULL,
  name        VARCHAR(128) NOT NULL,
  image       VARCHAR(128) NOT NULL,
  description VARCHAR(128) NOT NULL,
  rating      DOUBLE PRECISION NULL,
PRIMARY KEY(id)
);