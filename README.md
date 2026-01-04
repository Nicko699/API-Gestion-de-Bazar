# 🛍️ API Gestión Bazar

API REST para la **gestión de un sistema de bazar o tienda**, desarrollada en Java con **Spring Boot** y **Spring Security**.
Permite administrar productos, usuarios, ventas y autenticación mediante **JWT**.

---

## 🚀 Características Principales

* 🔐 **Autenticación y Autorización**: Sistema basado en JWT con refresh tokens.
* 👤 **Gestión de Usuarios**: Registro, login, edición de perfil y recuperación de contraseña por correo.
* 📦 **Gestión de Productos**: CRUD completo con control de inventario.
* 💰 **Gestión de Ventas**: Registro, consulta y reportes de ventas.
* 🧑‍💼 **Roles de Usuario**: Roles `USER` y `ADMIN` con permisos diferenciados.
* 📧 **Notificaciones por Email**: Envío de correos para recuperación de contraseña.
* 🛡️ **Seguridad Avanzada**: Encriptación de contraseñas y validación de tokens.

---

## 📋 Requisitos Previos

* ☕ **Java 17** o superior
* 🐬 **MySQL 8.0** o superior
* 🔧 **Maven 3.6** o superior
* 📩 **Cuenta de Gmail** con contraseña de aplicación (para el módulo de correo)

---

## ⚙️ Instalación y Configuración

### 1️⃣ Clonar el repositorio

```bash
git clone <url-del-repositorio>
cd API-Gestion-de-Bazar
```

### 2️⃣ Crear la base de datos

```sql
CREATE DATABASE api_bazar;
```

### 3️⃣ Configurar variables de entorno

Crea un archivo `.env` o define las variables en tu entorno del sistema en properties:

```properties
BD_URL=jdbc:mysql://localhost:3306/api_bazar
BD_USER_NAME=root
BD_PASSWORD=123
FIRMA=7Kj9mP2qR5tW8xZ1aC4dF6g... de 32 caracteres
MAIL_EMAIL=tu correo
MAIL_PASSWORD=<tu-password-de-aplicacion>
```

> 🔑 **Nota sobre `FIRMA`:** genera una cadena larga y aleatoria para firmar los tokens JWT- minimo de 32 caracteres.

### 4️⃣ Compilar y ejecutar

```bash
mvn clean install
mvn spring-boot:run
```

La API se ejecutará en:
👉 `http://localhost:8080`

---

## 📧 Configuración del Módulo de Correo

Para habilitar el envío de correos electrónicos:

1. Habilita la **verificación en dos pasos** en tu cuenta de Gmail.
2. Genera una **contraseña de aplicación** desde:

   * https://myaccount.google.com/apppasswords
   * “Verificación en dos pasos” → “Contraseñas de aplicaciones”
   * Crea una contraseña para “Correo”.
3. Usa esa contraseña en `MAIL_PASSWORD`.

---

## 📚 Documentación de la API

### ✅ Importar la colección de Postman

La forma más fácil de probar la API es importar el archivo:

`api_Gestion_Bazar.postman_collection.json`

1. Abre **Postman**.
2. Haz clic en **Import**.
3. Selecciona el archivo `.json` de la colección.
4. ¡Listo! Tendrás todos los endpoints configurados.

---

### 🧩 Qué incluye la colección `api_Gestion_Bazar.postman_collection.json`

Este archivo exportado desde Postman contiene:

#### 🔹 Endpoints organizados por módulos

* **Autenticación** → Registro, login, refresh token, recuperación de contraseña.
* **Usuarios** → CRUD de usuarios, activar/desactivar cuentas.
* **Productos** → CRUD de productos, búsqueda, control de stock bajo.
* **Ventas** → Registro de ventas, top 5, búsqueda por fecha.

#### 🔹 Variables preconfiguradas

* `{{baseUrl}}`: URL base de la API (por defecto `http://localhost:8080`)
* `{{accessToken}}`: Token JWT que se actualiza automáticamente tras iniciar sesión o a veces se debe de actualizar manualmente copiando y pegando.
* `{{refreshTokenId}}`: Id del Token 
* `{{refreshToken}}`: Token para renovar el `accessToken`

## 🔑 Endpoints Principales

### 🔐 Autenticación

| Método | Endpoint                           | Descripción                   |
| ------ | ---------------------------------- | ----------------------------- |
| POST   | `/usuario/crearCuenta`             | Crear nueva cuenta            |
| POST   | `/usuario/iniciarSesion`           | Iniciar sesión                |
| POST   | `/refreshToken/renovarAccessToken` | Renovar token                 |
| POST   | `/resetToken/recuperarPassword`    | Enviar correo de recuperación |
| POST   | `/resetToken/cambiarPassword`      | Cambiar contraseña            |

### 👤 Usuarios

| Método | Endpoint                              | Descripción             |
| ------ | ------------------------------------- | ----------------------- |
| GET    | `/usuario/admin/obtenerUsuarios`      | Listar usuarios (Admin) |
| PATCH  | `/usuario/admin/editarUsuario`        | Editar perfil           |
| PATCH  | `/usuario/admin/reactivarCuenta/{id}` | Reactivar cuenta        |
| PATCH  | `/usuario/admin/eliminarUsuario/{id}` | Desactivar cuenta       |

### 📦 Productos

| Método | Endpoint                                     | Descripción              |
| ------ | -------------------------------------------- | ------------------------ |
| GET    | `/producto/user/obtenerProductos`            | Listar productos         |
| POST   | `/producto/crearProducto`                    | Crear producto           |
| PUT    | `/producto/admin/editarProducto/{id}`        | Editar producto          |
| DELETE | `/producto/admin/eliminarProducto/{id}`      | Eliminar producto        |
| GET    | `/producto/admin/obtenerProductosCantMenor5` | Productos con stock bajo |

### 🛒 Ventas

| Método | Endpoint                                     | Descripción             |
| ------ | -------------------------------------------- | ----------------------- |
| POST   | `/venta/crearVenta`                          | Crear venta             |
| GET    | `/venta/admin/obtenerVentas`                 | Listar todas las ventas |
| GET    | `/venta/admin/top5Ventas`                    | Consultar top 5 ventas  |
| GET    | `/venta/admin/obtenerVentasPorFecha/{fecha}` | Filtrar por fecha       |

---

## 🧑‍💻 Roles y Permisos

| Rol   | Permisos                                                               |
| ----- | ---------------------------------------------------------------------- |
| USER  | Ver productos, ver sus ventas, crear ventas, editar perfil             |
| ADMIN | Todos los permisos de USER + gestión de usuarios, productos y reportes |

---

## 📝 Licencia

Este proyecto está bajo la licencia **MIT**.
Eres libre de usarlo y modificarlo mencionando la autoría correspondiente.
---

## 👨‍💻 Autor

Desarrollado por **Nicolás Muñoz Díaz** 

---
