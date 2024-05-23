Proyecto de Prueba - Backend
Este proyecto está desarrollado utilizando servicios REST API con Java (versión 17) y tecnologías como Spring Boot 3 y Spring JPA. Se utiliza Hibernate para el manejo de la base de datos, y en este caso, se ha configurado para trabajar con MySQL. Además, la aplicación sigue una arquitectura hexagonal implementada con el patrón de diseño Builder.

Tecnologías Utilizadas:
Java 17
Spring Boot 3
Spring JPA
Hibernate (Base de datos: MySQL)
MapStruct (Para el manejo de DTO y transformaciones de objetos)
Manejo de excepciones personalizado en el backend con Controller Advice
Generar JAR:
Para generar el archivo JAR, puedes utilizar el siguiente comando:

bash
Copy code
./mvnw package
Nota: El archivo mvnw es generado por el IDE (por ejemplo, IntelliJ IDEA), pero también puedes usar el comando Maven directamente.

La salida del comando mostrará el directorio donde se genera el JAR, comúnmente en target/.

Ejecutar la Aplicación:
Suponiendo que el archivo JAR generado se encuentra en target/server.jar, puedes ejecutar la aplicación de las siguientes maneras:

Opción 1:

bash
Copy code
java -jar target/server.jar
Opción 2:

bash
Copy code
java -jar target/server.jar --spring.config.location=config/app.properties
Opción 3:

bash
Copy code
java -jar target/server.jar --spring.config.location=config/app.properties --server.port=8081
El parámetro --spring.config.location indica a Spring Boot dónde tomar las configuraciones .properties, que por defecto se colocan en src/main/resources/application.properties.

Importante:

Es necesario tener configurada la variable de entorno JAVA_HOME con la ruta donde se encuentra instalada la JDK. Por ejemplo, en Linux/macOS:

bash
Copy code
export JAVA_HOME=/directorio/jdk
Recuerda configurar la variable de entorno para que apunte a la versión 8 de la JDK.


Seguir esta pagina
https://youtu.be/ux1uzPAMRzk?si=kWERxbe3jPDJ5OmM