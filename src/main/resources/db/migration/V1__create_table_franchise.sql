CREATE TABLE franchise (
  id   BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(200) NOT NULL,
  CONSTRAINT pk_franchise PRIMARY KEY (id),
  CONSTRAINT uk_franchise_name UNIQUE (name)
);
