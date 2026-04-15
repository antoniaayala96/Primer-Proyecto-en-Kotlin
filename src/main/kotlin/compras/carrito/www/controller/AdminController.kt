package compras.carrito.www.controller

import compras.carrito.www.beans.Carrito
import compras.carrito.www.beans.Producto
import compras.carrito.www.util.Logger

class AdminController(
        private val productos: MutableList<Producto>,
        private val carrito: Carrito
) {
    fun menu() {
        var salir = false
        while (!salir) {
            try {
                println(
                        """
                    |--- ADMINISTRACIÓN DE PRODUCTOS ---
                    |1. Listar productos
                    |2. Crear producto
                    |3. Editar producto
                    |4. Eliminar producto
                    |5. Volver
                    """.trimMargin()
                )
                print("Opción: ")
                when (readln()) {
                    "1" -> listar()
                    "2" -> crear()
                    "3" -> editar()
                    "4" -> eliminar()
                    "5" -> salir = true
                    else -> println("Opción inválida")
                }
            } catch (e: Exception) {
                println("❌ Error en administración: ${e.message}")
                Logger.logError(e)
            }
        }
    }

    private fun listar() {
        if (productos.isEmpty()) {
            println("No hay productos.")
            return
        }
        productos.forEach { p ->
            println("${p.id}. ${p.nombre} - Marca: ${p.marca} - Descripcion: ${p.descripcion} - Precio: ${"%.2f".format(p.precio)} - Stock: ${p.stock}")
        }
    }

    private fun crear() {
        try {
            print("Nombre: "); val nombre = readln().trim()
            print("Marca: "); val marca = readln().trim()
            print("Descripcion: "); val descripcion = readln().trim()
            print("Precio: "); val precio = readln().toDoubleOrNull()
            print("Stock: ");  val stock  = readln().toIntOrNull()

            if (nombre.isBlank() || precio == null || stock == null) {
                println("Datos inválidos")
                return
            }

            val nextId = (productos.maxOfOrNull { it.id } ?: 0) + 1
            productos.add(Producto(nextId, nombre,marca, descripcion, precio, stock))
            println("Producto creado con ID $nextId")
        } catch (e: Exception) {
            println("❌ Error al crear producto: ${e.message}")
            Logger.logError(e)
        }
    }

    private fun editar() {
        try {
            print("ID a editar: "); val id = readln().toIntOrNull() ?: return println("ID inválido")
            val p = productos.find { it.id == id } ?: return println("El producto no existe")

            print("Nombre (${p.nombre}): ")
            readln().takeIf { it.isNotBlank() }?.let { p.nombre = it.trim() }

            print("Marca (${p.marca}): ")
            readln().takeIf { it.isNotBlank() }?.let { p.marca = it.trim() }

            print("Descripcion (${p.descripcion}): ")
            readln().takeIf { it.isNotBlank() }?.let { p.descripcion = it.trim() }

            print("Precio (${p.precio}): ")
            readln().takeIf { it.isNotBlank() }?.toDoubleOrNull()?.let { p.precio = it }

            print("Stock (${p.stock}): ")
            readln().takeIf { it.isNotBlank() }?.toIntOrNull()?.let { p.stock = it }

            println("Producto actualizado")
        } catch (e: Exception) {
            println("❌ Error al editar producto: ${e.message}")
            Logger.logError(e)
        }
    }

    private fun eliminar() {
        try {
            print("ID a eliminar: "); val id = readln().toIntOrNull() ?: return println("ID inválido")

            if (carrito.contieneProducto(id)) {
                println("No se puede eliminar: el producto está en el carrito.")
                return
            }

            val removed = productos.removeIf { it.id == id }
            if (removed) println("🗑️ Producto $id eliminado") else println("No se encontró el producto")
        } catch (e: Exception) {
            println("❌ Error al eliminar producto: ${e.message}")
            Logger.logError(e)
        }
    }
}
