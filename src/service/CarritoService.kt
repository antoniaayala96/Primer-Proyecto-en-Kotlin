package service

import model.ItemCarrito
import model.Producto

class CarritoService {

    private val items = mutableListOf<ItemCarrito>()

    fun obtenerItems(): List<ItemCarrito> {
        return items
    }

    fun agregarProducto(producto: Producto, cantidad: Int, inventario: Inventario): Boolean {
        if (cantidad <= 0) {
            println("La cantidad debe ser mayor que 0.")
            return false
        }

        if (!inventario.hayStockSuficiente(producto.id, cantidad)) {
            println("No hay stock suficiente para agregar este producto.")
            return false
        }

        val itemExistente = items.find { it.producto.id == producto.id }

        return if (itemExistente != null) {
            itemExistente.cantidad += cantidad
            inventario.reducirStock(producto.id, cantidad)
            println("Cantidad actualizada en el carrito.")
            true
        } else {
            items.add(ItemCarrito(producto, cantidad))
            inventario.reducirStock(producto.id, cantidad)
            println("Producto agregado al carrito.")
            true
        }
    }

    fun eliminarProducto(idProducto: Int, cantidad: Int, inventario: Inventario): Boolean {
        val itemExistente = items.find { it.producto.id == idProducto }

        if (itemExistente == null) {
            println("El producto no está en el carrito.")
            return false
        }

        if (cantidad <= 0) {
            println("La cantidad a eliminar debe ser mayor que 0.")
            return false
        }

        return if (cantidad >= itemExistente.cantidad) {
            inventario.aumentarStock(idProducto, itemExistente.cantidad)
            items.remove(itemExistente)
            println("Producto eliminado completamente del carrito.")
            true
        } else {
            itemExistente.cantidad -= cantidad
            inventario.aumentarStock(idProducto, cantidad)
            println("Cantidad reducida en el carrito.")
            true
        }
    }

    fun mostrarCarrito() {
        println("=== CARRITO DE COMPRAS ===")

        if (items.isEmpty()) {
            println("El carrito está vacío, intenta agregar algún producto.")
            return
        }

        for (item in items) {
            val total = item.producto.precio * item.cantidad
            println("===========================")
            println("ID: ${item.producto.id}")
            println("Nombre: ${item.producto.nombre}")
            println("Marca: ${item.producto.marca}")
            println("Cantidad: ${item.cantidad}")
            println("Precio unitario: ${"%.2f".format(item.producto.precio)}")
            println("Total por producto: ${"%.2f".format(total)}")
            println("===========================")
        }

        val totalItems = items.sumOf { it.producto.precio * it.cantidad }
        println("Total general: ${"%.2f".format(totalItems)}")
    }
}