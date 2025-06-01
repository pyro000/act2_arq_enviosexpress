# Sistema de Seguimiento de Paquetes (BF-API-ACT2)

## Complementos

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Hibernate
- PostgreSQL
- Apache CXF (para servicios SOAP)
- Maven (gestor de dependencias)
- Lombok

## Requerimientos
Asegurarse de tener instalados los siguientes componentes:

- Java Development Kit (JDK) 17
- PostgreSQL
- Un cliente PostgreSQL - Herramientas como pgAdmin, DBeaver, o la línea de comandos psql.
- IDE: IntelliJ IDEA (Community o Ultimate), Eclipse o VS Code con las extensiones de Java.
- SoapUI: Necesario para probar el servicio SOAP expuesto por la aplicación.

## Configuración de la Base de Datos
El proyecto está configurado para usar una base de datos PostgreSQL.

- Crea la base de datos bf-api-act2:

`CREATE DATABASE "bf-api-act2";`

Crea un usuario y asígnale permisos.

`CREATE USER pyro WITH PASSWORD 'qwerty123';`

`GRANT ALL PRIVILEGES ON DATABASE "bf-api-act2" TO pyro;`

## Configura las credenciales del server
Abre el archivo `src/main/resources/application.properties` y verifica o ajusta las propiedades de conexión a tu base de datos, el resto dejar intacto preferencialmente:

`spring.datasource.username=pyro
spring.datasource.password=qwerty123`

# Ejecución

En tu IDE busca la forma de instalar los complementos, pero generalmente se hace al ir a la raíz del proyecto y ejecutando en terminal (Si tienes maven instalado en tu PC):

`mvn clean install`

Puedes iniciar la aplicación directamente desde tu IDE (buscando y ejecutando la clase principal ExenviosApplication) o desde la terminal con Maven:

`mvn spring-boot:run`

# Carga de Datos de Prueba (Opcional)

En PostgreSQL se puede hacer este insert:

### Pendiente TRACK123456789

`INSERT INTO packages (tracking_number, sender_name, receiver_name, origin, destination, weight, dimensions, status, current_location, estimated_delivery_date)
VALUES ('TRACK123456789', 'Juan Perez', 'Maria Garcia', 'Lima', 'Quito', 2.5, '20x15x10 cm', 'EN_TRANSITO', 'Centro de Distribucion Quito', '2025-06-07');`

`INSERT INTO tracking_event (date, description, location, package_id)
VALUES ('2025-06-01 10:00:00', 'Recibido en Origen', 'Lima', 1);`

`INSERT INTO tracking_event (date, description, location, package_id)
VALUES ('2025-06-02 08:30:00', 'Salió de Almacén Lima', 'Lima', 1);`

`INSERT INTO tracking_event (date, description, location, package_id)
VALUES ('2025-06-03 14:00:00', 'Llegó a Centro de Distribucion Quito', 'Quito', 1);`

### Entregado DELIVERED987654321

`INSERT INTO packages (tracking_number, sender_name, receiver_name, origin, destination, weight, dimensions, status, current_location, estimated_delivery_date)
VALUES ('DELIVERED987654321', 'Ana Lopez', 'Carlos Ruiz', 'Bogota', 'Guayaquil', 1.0, '10x10x5 cm', 'ENTREGADO', 'Casa del Cliente', '2025-05-30');`

`INSERT INTO tracking_event (date, description, location, package_id)
VALUES ('2025-05-28 09:00:00', 'En ruta a Guayaquil', 'Bogota', 2);`

`INSERT INTO tracking_event (date, description, location, package_id)
VALUES ('2025-05-30 11:45:00', 'Entregado al cliente', 'Guayaquil', 2);`


# Usando SOAP
Una vez que la aplicación esté ejecutándose, el servicio SOAP estará disponible en la siguiente URL:

http://localhost:8080/services/tracking?wsdl

Para probar el servicio, puedes usar SoapUI:

En un proyecto nuevo importa el WSDL pegando la URL proporcionada.
Explora las operaciones disponibles (como getTrackingStatus) y envía solicitudes con números de seguimiento de prueba (VER CODIGOS EN Carga de Datos de Prueba).