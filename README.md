# Ticket API

Este proyecto es una API RESTful para la gestión de tickets. Permite crear, eliminar, editar y recuperar tickets con paginación y filtrado.

# Requisitos previos

- Docker instalado en tu máquina
- Java 17 o superior
- Gradle 8.8 o superior

# Configuración del entorn

# Clonar el repositorio

```bash
git clone https://github.com/stivendk/ticketapi.git
cd ticketapi
```

# Construir el proyecto
```bash
./gradlew build
```

# Construir y ejecutar el contenedor Docker
```bash
docker-compose up --build
```

# Probar la API
Una vez que el contenedor esté en funcionamiento, puedes acceder a la API en http://localhost:8080.

En la carpeta raiz del proyecto se encuentra adjunta la colección de postman junto con todas las acciones solicitadas para el API.
