Sistema de Seguimiento de Paquetes (BF-API-ACT2)
Este proyecto es una aplicación Spring Boot diseñada para gestionar el seguimiento de paquetes. Se conecta a una base de datos PostgreSQL y expone su funcionalidad principal a través de un servicio web SOAP.

Tecnologías Utilizadas
Java 17
Spring Boot 3.x
Spring Data JPA
Hibernate
PostgreSQL
Apache CXF (para servicios SOAP)
Maven (gestor de dependencias)
Lombok
Prerequisitos
Para que este proyecto funcione correctamente, asegúrate de tener instalados los siguientes componentes:

Java Development Kit (JDK) 17: Puedes descargarlo desde el sitio oficial de Oracle o a través de OpenJDK.
Maven: Usualmente viene integrado en IDEs como IntelliJ IDEA o Eclipse. Si no, descárgalo e instálalo manualmente.
PostgreSQL: Un servidor de base de datos PostgreSQL debe estar funcionando.
Un cliente PostgreSQL: Herramientas como pgAdmin, DBeaver, o la línea de comandos psql son muy útiles para administrar tu base de datos.
Un IDE: Recomendamos IntelliJ IDEA (Community o Ultimate), Eclipse o VS Code con las extensiones de Java.
SoapUI: Necesario para probar el servicio SOAP expuesto por la aplicación.
Configuración de la Base de Datos
El proyecto está configurado para usar una base de datos PostgreSQL. Sigue estos pasos para prepararla:

Conéctate a tu servidor PostgreSQL como un usuario con permisos para crear bases de datos (por ejemplo, el usuario postgres por defecto).

Crea la base de datos bf-api-act2:

SQL

CREATE DATABASE "bf-api-act2";
Crea un usuario y asígnale permisos. Este usuario será el que tu aplicación use para conectarse. Por ejemplo, para un usuario llamado pyro con una contraseña:

SQL

CREATE USER pyro WITH PASSWORD 'tu_contraseña_segura';
GRANT ALL PRIVILEGES ON DATABASE "bf-api-act2" TO pyro;
¡Importante! Cambia 'tu_contraseña_segura' por una contraseña robusta de tu elección.

Puesta en Marcha del Proyecto
Una vez que tengas los prerequisitos y la base de datos configurados, puedes seguir estos pasos para ejecutar la aplicación:

Clona el repositorio:

Bash

git clone https://github.com/tu-usuario/tu-repositorio.git
cd tu-repositorio # Navega a la raíz del proyecto clonado (e.g., exenvios/)
(Asegúrate de reemplazar tu-usuario/tu-repositorio con la URL real de tu repositorio en GitHub).

Configura las propiedades de la base de datos:
Abre el archivo src/main/resources/application.properties y verifica o ajusta las propiedades de conexión a tu base de datos:

Properties

spring.datasource.url=jdbc:postgresql://localhost:5432/bf-api-act2
spring.datasource.username=pyro
spring.datasource.password=tu_contraseña_segura # ¡Reemplaza con la contraseña real!
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Opcional: Carga datos de prueba al inicio. Útil en desarrollo.
spring.sql.init.mode=always
Construye el proyecto con Maven:
Desde la raíz del proyecto en tu terminal, ejecuta el siguiente comando:

Bash

mvn clean install
Esto descargará todas las dependencias necesarias y compilará la aplicación.

Ejecuta la aplicación Spring Boot:

Puedes iniciar la aplicación directamente desde tu IDE (buscando y ejecutando la clase principal ExenviosApplication) o desde la terminal con Maven:

Bash

mvn spring-boot:run
Al iniciar, la aplicación se conectará a la base de datos y, gracias a ddl-auto=update, creará automáticamente las tablas packages y tracking_event si aún no existen.

Carga de Datos Iniciales (Opcional)
Si spring.sql.init.mode=always está configurado en tu application.properties, el proyecto intentará cargar datos de prueba desde el archivo src/main/resources/data.sql cada vez que se inicie. Este archivo contiene paquetes de ejemplo y sus eventos de seguimiento asociados, lo que es ideal para pruebas rápidas.

Puedes modificar data.sql para adaptar o añadir nuevos datos de prueba según tus necesidades.

Acceso al Servicio SOAP
Una vez que la aplicación esté ejecutándose, el servicio SOAP estará disponible en la siguiente URL:

WSDL URL: http://localhost:8080/services/tracking?wsdl
Para probar el servicio, puedes usar SoapUI:

Abre SoapUI.
Crea un nuevo proyecto SOAP.
Importa el WSDL pegando la URL proporcionada.
Utiliza las operaciones disponibles (como getTrackingStatus) y envía solicitudes con números de seguimiento de prueba (por ejemplo, los definidos en data.sql como PACK_001_IN_TRANSIT o PACK_002_DELIVERED) para verificar las respuestas.