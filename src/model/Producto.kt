package model

data class Producto(
    val id: Int,
    val nombre: String,
    val marca: String,
    val precio: Double,
    var cantidadDisponible: Int
) {
    fun mostrarInfo(): String {
        return "ID: $id | Nombre: $nombre | Marca: $marca | Precio: $${"%.2f".format(precio)} | Stock: $cantidadDisponible"
    }
}