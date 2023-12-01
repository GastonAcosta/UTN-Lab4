Biblioteca App

Este proyecto es una aplicación de biblioteca desarrollada con Java Spring Boot en el backend y Vue.js en el frontend. Proporciona funcionalidades básicas de administración de libros, usuarios y registros de préstamos.

CONTENIDO:

1 - Instalación
2 - Configuración del Entorno
3 - Uso
4 - Funcionalidades
5 - Tecnologías Utilizadas

1 -Instalación:

Es necesario tener Java y Node.js instalados en el sistema.

bash
Copy code
# Clona el repositorio
git clone https://github.com/TuUsuario/biblioteca-app.git

# Accede al directorio del backend
cd biblioteca-app/backend

# Ejecuta la aplicación Spring Boot
./mvnw spring-boot:run
bash
Copy code
# Accede al directorio del frontend
cd biblioteca-app/frontend

# Instala las dependencias
npm install

# Inicia la aplicación Vue.js
npm run dev

2 - Configuración del Entorno:

La configuración del backend se encuentra en backend/src/main/resources/application.properties, donde puedes ajustar la conexión a la base de datos PostgreSQL.
El frontend se inicia en http://localhost:5173/inicio.

3 - Uso:

Una vez que la aplicación esté en funcionamiento, puedes acceder a la interfaz de usuario en tu navegador y comenzar a utilizar las funcionalidades de la biblioteca.

4 - Funcionalidades:

Registro y gestión de libros (Agregar, Listar, Modificar, Borrar, Buscar).
Registro y gestión de usuarios (Agregar, Listar, Modificar, Borrar).
Registro y gestión de préstamos (Agregar, Listar, Modificar, Borrar).

5 - Tecnologías Utilizadas:

Java Spring Boot
Vue.js
PostgreSQL
Intellij IDEA
Visual Studio Code
