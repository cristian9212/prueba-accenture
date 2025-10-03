CREATE TABLE product (
  id        BIGINT NOT NULL AUTO_INCREMENT,
  name      VARCHAR(200) NOT NULL,
  stock     INT NOT NULL,
  version   BIGINT NOT NULL DEFAULT 0,   -- para @Version (optimistic locking)
  branch_id BIGINT NOT NULL,
  CONSTRAINT pk_product PRIMARY KEY (id),
  CONSTRAINT uk_product_branch_name UNIQUE (branch_id, name),
  CONSTRAINT fk_product_branch FOREIGN KEY (branch_id) REFERENCES branch(id)
);