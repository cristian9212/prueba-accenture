CREATE TABLE branch (
  id            BIGINT NOT NULL AUTO_INCREMENT,
  name          VARCHAR(200) NOT NULL,
  franchise_id  BIGINT NOT NULL,
  CONSTRAINT pk_branch PRIMARY KEY (id),
  CONSTRAINT uk_branch_franchise_name UNIQUE (franchise_id, name),
  CONSTRAINT fk_branch_franchise FOREIGN KEY (franchise_id) REFERENCES franchise(id)
);