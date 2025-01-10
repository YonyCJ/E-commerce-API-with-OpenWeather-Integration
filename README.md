# E-commerce API con OpenWeather Integration

Este proyecto es una API REST desarrollada con Spring Boot que proporciona funcionalidades de CRUD para productos y consulta de datos meteorológicos usando la API de OpenWeather. La aplicación utiliza MongoDB como base de datos y está containerizada con Docker.

## Requisitos Previos

- Docker y Docker Compose instalados
- Java 21 (para desarrollo local)
- Maven (para desarrollo local)
- Una API key de OpenWeather (gratuita)

## Configuración Inicial

1. Clona el repositorio:
```bash
git clone https://github.com/YonyCJ/E-commerce-API-with-OpenWeather-Integration.git
cd E-commerce-API-with-OpenWeather-Integration
```

2. Asegúrate de que el archivo `application.properties` contenga tu API key de OpenWeather:
```properties
openweather.api.key=tu_api_key
openweather.api.url=https://api.openweathermap.org/data/2.5/weather
```
Temporalmente esta colocado el API Key que yo genere estará temporalmente

## Iniciar la Aplicación

1. Levanta los contenedores usando Docker Compose:
```bash
docker-compose up -d
```

Este comando iniciará:
- MongoDB en el puerto 27017
- Mongo Express (UI de administración) en el puerto 8081
- La aplicación Spring Boot en el puerto 8084

2. Verifica que los contenedores estén corriendo:
```bash
docker-compose ps
```

## Endpoints de la API
![image](https://github.com/user-attachments/assets/57011d30-0ae2-4614-ad53-f64bb86d3b27)

### Productos (Product API)

#### Crear Producto
- **Método**: POST
- **URL**: `http://localhost:8084/api/product`
- **Body**:
```json
{
    "name": "Teclado Gamer",
    "description": "Marca",
    "price": 0,
    "stock": 0
}
```

#### Listar Productos
- **Método**: GET
- **URL**: `http://localhost:8084/api/product`

#### Obtener Producto por ID
- **Método**: GET
- **URL**: `http://localhost:8084/api/product/{id}`

#### Actualizar Producto
- **Método**: PUT
- **URL**: `http://localhost:8084/product/{id}`
- **Ejemplo**: `http://localhost:8084/product/67807682f92d152174cd40b0`
- **Body**:
```json
{
    "name": "Botella de agua",
    "description": "Marca Cielo",
    "price": 0,
    "stock": 0
}
```

#### Eliminar Producto
- **Método**: DELETE
- **URL**: `http://localhost:8084/api/product/{id}`

### Clima (Weather API)
![image](https://github.com/user-attachments/assets/2abc6d29-294f-4347-8c58-d30977b7816a)

#### Consultar Clima
- **Método**: GET
- **URL**: `http://localhost:8084/api/weather`
- **Parámetros**:
  - `city`: Nombre de la ciudad (opcional)
  - `lat`: Latitud (opcional)
  - `lon`: Longitud (opcional)
- **Ejemplo**: `http://localhost:8084/api/weather?city=Puno&lat&lon`

La API retorna información sobre:
- Ubicación
- Temperatura
- Humedad
- Descripción del clima

## Acceso a MongoDB
![image](https://github.com/user-attachments/assets/5a02bdc9-f19b-43c2-ac5a-b9bbd48a9843)

- **MongoDB**: Accesible en `mongodb://localhost:27017`
- **Mongo Express**: Accesible en `http://localhost:8081`
  - Usuario: sa
  - Contraseña: sa

## Estructura del Proyecto

```
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/prapp/pt_crud_ecommerce/
│   │   │       ├── business/
│   │   │       ├── config/
│   │   │       ├── dao/
│   │   │       └── exception/
|   |   |       └── expose/
|   |   |       └── mapper/
│   │   └── resources/
│   │       └── application.properties
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

--**business** = Donde desarrollo toda la lógica del negocio

--**config** = Archivos de configuración como para revisar la conexión a la DB

--**dao** = Lo necesario para la conexión a la información aquí tenemos los entities y respotorys

--**exeption** = Para controlar los errores

--**expose** = Todo aquello que estará expuesto hacia el cliente

--**mapper** = Herramienta para transferencia de datos


## Detener la Aplicación

Para detener todos los servicios:
```bash
docker-compose down
```

Para detener y eliminar todos los volúmenes (esto eliminará los datos de MongoDB):
```bash
docker-compose down -v
```

Proyecto desarrollado con Java 21, Spring Boot 3.4.1
