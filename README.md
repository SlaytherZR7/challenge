# Sistema de Gestión Bancaria

### Descripción
Esta es una aplicación backend monolítica desarrollada con Java y Spring Boot. Permite gestionar clientes, cuentas bancarias y movimientos asociados, implementando operaciones CRUD y validaciones de negocio básicas.

### ⚙ Instalación y uso
```bash
# Requisitos previos
Java 17+ | Spring Boot 3+ | Maven 3.8+ | Docker

# Clonar el repositorio
git clone https://github.com/SlaytherZR7/challenge.git

# Ejecutar la aplicación
docker-compose up -d
```
La aplicación estará disponible en:
````http://localhost:8080````

#### 📁 En la raíz del proyecto encontrarás los siguientes archivos útiles:

- BaseDatos.sql: Script SQL con la estructura de las tablas necesarias para la base de datos.
- Challenge.postman_collection.json: Colección de Postman para probar fácilmente todos los endpoints de la API.

#### Clientes
```(api/clientes)```
| Método | Ruta                 | Descripción                    |
| ------ | -------------------- | ------------------------------ |
| POST   | `/api/clientes`      | Crear un nuevo cliente         |
| GET    | `/api/clientes`      | Listar todos los clientes      |
| GET    | `/api/clientes/{id}` | Obtener un cliente por ID      |
| PUT    | `/api/clientes/{id}` | Actualizar datos de un cliente |
| DELETE | `/api/clientes/{id}` | Eliminar un cliente            |


#### Cuentas
```(api/cuentas)```
| Método | Ruta                | Descripción                    |
| ------ | ------------------- | ------------------------------ |
| POST   | `/api/cuentas`      | Crear una nueva cuenta         |
| GET    | `/api/cuentas`      | Listar todas las cuentas       |
| GET    | `/api/cuentas/{id}` | Obtener una cuenta por ID      |
| PUT    | `/api/cuentas/{id}` | Actualizar datos de una cuenta |
| DELETE | `/api/cuentas/{id}` | Eliminar una cuenta            |

#### Movimientos
```(api/movimientos)```
| Método | Ruta                    | Descripción                   |
| ------ | ----------------------- | ----------------------------- |
| POST   | `/api/movimientos`      | Registrar un nuevo movimiento |
| GET    | `/api/movimientos`      | Listar todos los movimientos  |
| GET    | `/api/movimientos/{id}` | Obtener un movimiento por ID  |
| PUT    | `/api/movimientos/{id}` | Actualizar un movimiento      |
| DELETE | `/api/movimientos/{id}` | Eliminar un movimiento        |
