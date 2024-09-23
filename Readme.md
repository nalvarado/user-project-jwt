# Desafío JAVA BCI

Evaluación Java API REST

El script para la creación manual de la base de datos, se encuentra en la siguiente ruta.

```
/main/resources/create-schema.sql
```

Las configuraciones del sistema están en el archivo el cual se puede eliminar la propiedad de creacion del modelo, la
configuracion de DDL, se encuentra activa para que se cree el modelo en base a los model creados.:
> **application.properties**

```
#JPA - H2
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto= update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true
logging.level.org.hibernate.SQL=DEBUG
spring.sql.init.mode=always
```

## Pasos

Descargar Fuentes de git

```
git clone https://github.com/nalvarado/user-project-jwt.git
```

Una vez descargada las fuentes se deben ejecutar los siguiente comandos por consola:

```
./gradlew build
./gradlew bootRun
```

La ejecución de la consola para acceder a la base de datos es a través del siguiente link:

```
http://localhost:8080/h2-console
user: admin
pass: admin
```

La ejecución para acceder a swagger y api-docs es a traves de los siguientes links:

```
http://localhost:8080/swagger-ui/index.html
http://localhost:8080/v3/api-docs
```
Antes de la ejecución del servicio, favor considerar que el patron con la regex para validar la contraseña se encuentra de la siguiente propiedad del "application.properties"
```
#PATTERN
#Una Mayuscula, letras minúsculas, y dos numeros
validation.password.pattern=^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$
```
URL Test resultado
```
#ejecutar el siguiente comando.
./gradlew test 
#ruta del reporte
/user-project-jwt/build/reports/tests/test/index.html
```

La ejecución del servicio de registro es a través de la siguiente url:

```
POST localhost:8080/api/v1/auth/register
body:
{
    "name": "Nelson Alvarado Vidal",
    "email": "nalvarado@gmail.com",
    "password": "P4s$worpRUEd",
    "phones": [
        {
            "number": "3245232432",
            "cityCode": "13",
            "countryCode": "134"
        },
        {
            "number": "42343233",
            "cityCode": "13",
            "countryCode": "56"
        }
    ]
}
```

Respuesta:

```
{
    "id": "dcad5ccd-f377-4656-bbd7-6314c99583c5",
    "created": "2024-09-23T02:23:39.878+00:00",
    "modified": "2024-09-23T02:23:39.878+00:00",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiTmVsc29uIEFsdmFyYWRvIFZpZGFsIiwiZW1haWwiOiJuYWx2YXJhZG8zM0BnbWFpbC5jb20iLCJzdWIiOiJOZWxzb24gQWx2YXJhZG8gVmlkYWwiLCJpYXQiOjE3MjcwNTgyMTksImV4cCI6MTcyNzA1ODIyM30.dd4SpNEGCc3OcqkLPr2hNwgqT67AEplLmcPHhEB82Y4",
    "last_login": "2024-09-23T02:23:39.878+00:00",
    "is_active": true
}
```

## Pre-requisitos

- JDK 17
- Gradle

## Desarrollo

Herramientas y lenguajes utilizados

* Java 17 - Lenguaje de programación.
* Spring Boot 3.3.1 - Framework de desarrollo
* JWT 0.11.5 - Generación de tokens
* Swagger 2 - Documentación API
* Gradle - Gestor de dependencias.
* JUnit 5 - Testing
* IntelliJ IDEA 2023.2.5 (Ultimate Edition) - IDE de desarrollo.

## Autor

* **Nelson Alvarado**  
