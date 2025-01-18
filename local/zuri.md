<!--
    *** ETAPAS DE DESARROLLO DE LA PRACTICA ***
-->
# Etapa 1: Backend (Spring Boot)
- Configuración del proyecto:
## Crea un nuevo proyecto Spring Boot utilizando Spring Initializr (https://start.spring.io/).
- Incluye las siguientes dependencias:
- Spring Web
- Spring Data JPA
- PostgreSQL Driver (o Oracle Driver si eliges esa base de datos)
- Spring Security (si vas a implementar la autenticación)
- Springdoc-openapi-ui (para Swagger UI)
- Lombok (opcional, para simplificar el código)

## Diseño de la base de datos:
- Define las entidades Cliente y Poliza con los atributos especificados en las historias de usuario.
- Utiliza las anotaciones de JPA (@Entity, @Table, @Id, @GeneratedValue, @Column, etc.) para mapear las entidades a las tablas de la base de datos.
- Define la relación entre las entidades (un cliente puede tener varias pólizas).

## Creación de los repositorios:
- Crea interfaces que extiendan JpaRepository para las entidades Cliente y Poliza. 
- Esto te proporcionará métodos básicos para interactuar con la base de datos.

## Implementación de los servicios:
- Crea clases de servicio para Cliente y Poliza.
- Implementa la lógica de negocio para las operaciones CRUD y las validaciones especificadas en las historias de usuario.

## Creación del controlador REST:
- Crea un controlador REST que exponga las funcionalidades a través de una API.
- Utiliza las anotaciones de Spring MVC (@RestController, @RequestMapping, @GetMapping, @PostMapping, etc.) para definir los endpoints.
- Documenta la API con Swagger/OpenAPI.

## Implementación de la autenticación y autorización (opcional):
- Si decides implementar la autenticación y autorización, configura Spring Security para proteger los endpoints y definir los roles de usuario.

## Pruebas unitarias y de integración:
- Escribe pruebas unitarias para los servicios y el controlador REST.
- Escribe pruebas de integración para verificar la interacción entre las diferentes capas de la aplicación.

# Etapa 2: Frontend (Angular)
- Configuración del proyecto:
## Crea un nuevo proyecto Angular utilizando Angular CLI.
- Instala las dependencias necesarias, incluyendo Redux o NGXS.

## Creación de los componentes:
- Crea componentes para las diferentes vistas de la aplicación (lista de clientes, formulario de cliente, lista de pólizas, formulario de póliza, etc.).

## Implementación de la gestión del estado:
- Utiliza Redux o NGXS para gestionar el estado de la aplicación.
- Define las acciones, los reducers y los selectors para manejar la información de clientes y pólizas.

## Implementación de la autenticación:
- Crea un sistema de autenticación básico para diferenciar entre administrador y cliente.
- Puedes utilizar un servicio para almacenar el rol del usuario y controlar el acceso a las diferentes vistas.

## Consumo de la API REST:
- Crea un servicio para consumir la API REST del backend.
- Utiliza el módulo HttpClient de Angular para realizar las peticiones HTTP.

## Diseño de la interfaz de usuario:
- Diseña las vistas de la aplicación de forma intuitiva y fácil de usar.
- Utiliza los componentes de Angular Material u otro framework de UI para crear una interfaz atractiva.

## Pruebas unitarias:
- Escribe pruebas unitarias para los componentes y los servicios del frontend.

# Etapa 3: Integración y Despliegue
- .
## Integración del frontend y el backend:
- Conecta el frontend al backend a través de la API REST.
- Verifica que la comunicación entre el frontend y el backend funciona correctamente.

## Despliegue en AWS:
- Crea una cuenta en AWS (si no tienes una).
- Despliega la aplicación en un servicio de AWS como Elastic Beanstalk o EC2.

## Documentación:
- Crea un archivo README.md en cada proyecto (frontend y backend) con las instrucciones para construir, ejecutar y desplegar la aplicación.