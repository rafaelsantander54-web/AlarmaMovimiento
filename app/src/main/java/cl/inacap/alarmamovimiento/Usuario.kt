package cl.inacap.alarmamovimiento

import java.io.Serializable

// data class: clase pensada para almacenar y transportar datos.
// Serializable: permite enviar el objeto de una Activity a otra con intent.putExtra("usu", usu).
// Los valores por defecto ("") permiten que Firebase pueda crear el objeto al leerlo
// desde la base de datos (Firebase necesita un constructor sin parámetros).
data class Usuario(
    val rut: String = "",
    val nombre: String = "",
    val apellido: String = "",
    val correo: String = "",
    val cargo: String = "",
    val contraseña: String = ""
) : Serializable
