# Read Me First
Ejercicio: alta de usuario para empresa Smartjob
Fecha: 8 de Noviembre de 2025

    # ___________________________________________________________________________________________
    # NOTAS:
    #   * LA LOGICA UTILIZADA PARA LOS PAQUETES SIGUE UN MODELO DE ARQUITECTURA HEXAGONAL Y DDD
    #   * El ejericio no indica que hacer con el JWT generado, tampoco solicita otro endpoint
    #       donde utilizar el JWT en header Authorization, o validacion de JWT
    #   * No se especifica como configurar la expresion regular para validar formato de password
    # ___________________________________________________________________________________________

# Caracteristicas

* Existen dos diagramas UML de clase perteneciente a las dos clases con mas funcionalida en el /diagramas
* Base de datos H2
* Creacion de esquemas de datos automatico con Flyway
* Los script de creacion de esquema de db se encuentran en directorio estandar de flyway (/resources/deb/migration)
* Implementacion anemica de DDD en paquete CORE
* Implementacion de arquitectura haxagonal
* Generacion automatica de interfaces restcontroller con OpenAPI generator
  * Las interfaces de los restcontroller estan en adapter en lugar de core.port para evitar tener referencia al framework en core 
* Swagger
* Mapeos automaticos con MapStruct
* Password no almacenado, se almacena salt&pepper con bcrypt (OWASP)


# Diagramas

* Existen dos diagramas UML de clase perteneciente a las dos clases con mas funcionalida en el /diagramas

# Instrucciones

* Compilacion mas test: ./gradlew clean build
* * Tests: ./gradlew clean test
* Ejecucion: ./gradlew clean bootRun
* H2 Manager: http://localhost:8080/h2-console/
* Swagger: http://localhost:8080/swagger-ui/index.html
* Endpoint alta usuario: POST <host>t:8080/v1/users
  * Codigos de retorno: 201 (created), 400 (bad request), 405 (method not allowed), 500 (internal error)

* Ejemplo de request:
    ~~~
      curl --location 'localhost:8080/v1/users' --header 'Content-Type: application/json' --data-raw 
            '{
            "name": "Juan Rodriguez",
            "email": "juan@rodriguez.org",
            "password": "Hunter25",
            "phones": [
                            {
                                "number": "1234567",
                                "cityCode": "1",
                                "countryCode": "57"
                            },
                            {
                                "number": "8465334",
                                "cityCode": "9",
                                "countryCode": "54"
                            }
                    ]
            }'
    ~~~
