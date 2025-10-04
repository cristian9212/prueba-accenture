# Proyecto Franquicias

Este proyecto implementa un sistema de gestión de **franquicias, sucursales y productos** utilizando **Spring Boot**, **MySQL** y despliegue con **Docker**.

---

## Funcionalidades
- **Gestión de Franquicias**: Permite registrar, actualizar y eliminar información sobre diferentes franquicias.
- **Análisis de Datos**: Proporciona herramientas para analizar el rendimiento de las franquicias, incluyendo métricas clave como ventas, crecimiento y satisfacción del cliente. .
- **Integración con APIs**: Permite la integración con diversas APIs para obtener datos adicionales y enriquecer el análisis.
- **Pruebas de codigo**: Incluye un conjunto de pruebas unitarias y de integración para asegurar la calidad del código.

## 📚 Contenido
- [Estructura](#-estructura)
- [Base de datos](#-base-de-datos)
- [Servicios](#-servicios)
- [Endpoints](#-endpoints)
- [Pruebas](#-pruebas)
- [Docker local](#-docker-local)
- [Despliegue en AWS](#-despliegue-en-aws)
- [Swagger](#-swagger)
- [Tecnologías](#-tecnologías)
---

## 🗂️ Estructura
- **Entities** → Tablas de la BD (`Franchise`, `Branch`, `Product`)
- **Repositories** → Interfaces JPA para consultas
- **Services** → Lógica de negocio (`FranchiseService`, `BranchService`, `ProductService`)
- **Controllers** → Endpoints REST

---

## 🗄️ Base de datos

### Tablas
```sql
CREATE TABLE franchise (
  id   BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(200) NOT NULL,
  CONSTRAINT pk_franchise PRIMARY KEY (id)
);

CREATE TABLE product (
  id   BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(200) NOT NULL,
  stock INT NOT NULL,
  franchise_id BIGINT,
  CONSTRAINT pk_product PRIMARY KEY (id),
  CONSTRAINT fk_product_franchise FOREIGN KEY (franchise_id) REFERENCES franchise(id)
);

-- Franquicias
INSERT INTO franchise (name) VALUES
('Crepes & Waffles'),
('Juan Valdez Café'),
('El Corral');

-- Productos
INSERT INTO product (name, stock, franchise_id) VALUES
('Helado Artesanal', 50, 1),
('Café Premium', 100, 2),
('Hamburguesa Doble', 80, 3);

```

### ⚙ Servicios ProductService

- create(ProductDto productDto) → Crea un producto

- delete(Long productId) → Elimina un producto por id

- updateStock(Long id, int stock) → Actualiza el stock de un producto

- findMaxStockByFranchise(Long franchiseId) → Devuelve productos con mayor stock por franquicia

### ⚙ BranchService

- createBranch(BranchDto branchDto) → Crea una sucursal asociada a una franquicia

- Validación: No permite duplicar nombres de sucursales en la misma franquicia

### 🚀 Endpoints

- Método	Endpoint	Descripción

- POST	/products	Crear producto

- DELETE	/products/{id}	Eliminar producto

- PUT	/products/{id}/stock	Actualizar stock de producto

- GET	/products/franchise/{id}/max-stock	Consultar top productos de una franquicia

- POST	/branches	Crear sucursal

## ✅ Pruebas

- Tests unitarios configurados para BranchService, ProductService y FranchiseService utilizando JUnit 5 y Mockito.

- Validaciones de negocio:

- Lanzar excepción si la franquicia no existe

### 🐳 Docker local
- Construcción de la imagen
- docker build -t franquicias.

### Ejecución del contenedor
- docker run --rm -p 8080:8080 --name franquicias franquicias

### Despliegue en AWS

1. Subida de la imagen a Docker Hub
   docker tag franquicias crist92/img_franquicia
   docker push crist92/img_franquicia

2. Conexión a EC2
   ssh -i "mi-llave.pem" ec2-user@<IP_PUBLICA>

3. Descargar e iniciar el contenedor desde Docker Hub
   docker run -d --name franquicias -p 8080:8080 crist92/img_franquicia

4. Acceso desde el navegador
   http://<IP_PUBLICA>:8080/swagger-ui/index.html

5. La documentación interactiva de la API está disponible en el siguiente enlace:

👉 [Swagger UI en AWS](http://3.144.190.217:8080/swagger-ui/index.html)-ui/index.html

### 📖 Swagger

- La documentación de la API está disponible en:

- 👉 Swagger UI http://3.144.190.217:8080/swagger-ui/index.html



### 🛠️ Tecnologías

- Java 17 / 18
- Spring Boot 3
- Spring Data JPA
- MySQL
- JUnit 5 / Mockito
- Docker
- AWS EC2
-RDS MySQL
- Swagger
- Lombok
- Flyway
- Services REST




