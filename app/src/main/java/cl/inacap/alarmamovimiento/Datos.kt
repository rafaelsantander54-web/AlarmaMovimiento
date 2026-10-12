package cl.inacap.alarmamovimiento

// Almacenamiento temporal en memoria (simula la base de datos).
// Cuando se integre Firebase (Realtime Database), este objeto será reemplazado
// por consultas a la nube. Por ahora los datos viven mientras la app está abierta.
object Datos {

    // Usuario de prueba que existe "por defecto" para poder iniciar sesión.
    val usuarios = mutableListOf(
        Usuario("11111111-1", "Rafael", "Santander", "rafael@inacap.cl", "Administrador", "1234")
    )

    // Contadores que se mostrarán en la pantalla "Estadísticas".
    var activaciones = 0
    var desactivaciones = 0
    var alarmaActiva = false

    // Busca un usuario por rut y contraseña. Retorna null si no existe.
    fun buscarUsuario(rut: String, contraseña: String): Usuario? {
        for (u in usuarios) {
            if (u.rut == rut && u.contraseña == contraseña) {
                return u
            }
        }
        return null
    }

    // Verifica si el rut ya está registrado.
    fun existeRut(rut: String): Boolean {
        for (u in usuarios) {
            if (u.rut == rut) {
                return true
            }
        }
        return false
    }
}
