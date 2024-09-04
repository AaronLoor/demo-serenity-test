# language: es
Característica: Funcionalidad demo puntonet

  Esquema del escenario: Validar el correcto ingreso de Clientes
    Dado que el usuario accede a la página ingresa usuario "<usuario>" y contraseña "<contrasena>" y realiza clic en el botón iniciar sesión
    Cuando el usuario ingresa nombre, apellido, cédula, fecha_nacimiento, teléfono, estado_civil, email, género y dirección en el formulario de registro
      | nombre   | apellido   | cédula   | fecha_nacimiento   | teléfono   | estado_civil   | email   | género   | dirección   |
      | <nombre> | <apellido> | <cedula> | <fecha_nacimiento> | <telefono> | <estado_civil> | <email> | <genero> | <direccion> |
    Y realiza clic en el botón registrar
    Entonces debería ver notificación de ingreso exitoso

    Ejemplos:
      | usuario       | contrasena | nombre            | apellido         | cedula     | fecha_nacimiento | telefono   | estado_civil   | email                       | genero    | direccion                        |
      | puntonet.user | password   | Aaron Wladimir    | Loor Moran       | 1316843231 | 18/06/1998       | 0991234567 | Union de Hecho | aaron.loor@puntonet.ec      | Masculino | Cdla. Santa Clara, Guayaquil     |
      | puntonet.user | password   | Mauricio Roberto  | Chilan Macias    | 1304362061 | 18/06/1970       | 0991234567 | Casado         | mauricio.chilan@puntonet.ec | Masculino | Av. 6 de Diciembre N34-45, Quito |
      | puntonet.user | password   | Cristhian Alberto | Miranda Zambrano | 1305324329 | 18/06/2002       | 0991234567 | Viudo          | alberto.miranda@puntonet.ec | Masculino | Av. 5 de Octubre , Quito         |
