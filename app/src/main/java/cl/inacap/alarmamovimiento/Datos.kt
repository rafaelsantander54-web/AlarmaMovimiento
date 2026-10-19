package cl.inacap.alarmamovimiento

// Estado de la alarma y contadores en memoria.
// Los usuarios ya NO se guardan aquí: ahora viven en Firebase Realtime Database
// (colección "Usuario"). Estos contadores pasarán también a Firebase cuando
// se integre el ESP32 y el registro real de acciones.
object Datos {

    // Contadores que se mostrarán en la pantalla "Estadísticas".
    var activaciones = 0
    var desactivaciones = 0
    var alarmaActiva = false
}
