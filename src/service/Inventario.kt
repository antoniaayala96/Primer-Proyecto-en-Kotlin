package service

import data.DatosIniciales
import model.Producto

class Inventario {

    private val productos: MutableList<Producto> = DatosIniciales.obtenerProductosIniciales()

    fun mostrarProductos() {
        println("\n========== CATÁLOGO DE TELÉFONOS ==========")

        if (productos.isEmpty()) {
            println("No hay productos disponibles.")
            return
        }

        productos.forEach { producto ->
            println(producto.mostrarInfo())
        }
    }

    fun obtenerProductos(): List<Producto> {
        return productos
    }

    fun buscarProductoPorId(id: Int): Producto? {
        return productos.find { it.id == id }
    }

    fun existeProducto(id: Int): Boolean {
        return productos.any { it.id == id }
    }

    fun hayStockSuficiente(id: Int, cantidadSolicitada: Int): Boolean {
        val producto = buscarProductoPorId(id)
        return producto != null && cantidadSolicitada > 0 && producto.cantidadDisponible >= cantidadSolicitada
    }

    fun reducirStock(id: Int, cantidad: Int): Boolean {
        val producto = buscarProductoPorId(id)

        return if (producto != null && cantidad > 0 && producto.cantidadDisponible >= cantidad) {
            producto.cantidadDisponible -= cantidad
            true
        } else {
            false
        }
    }

    fun aumentarStock(id: Int, cantidad: Int): Boolean {
        val producto = buscarProductoPorId(id)

        return if (producto != null && cantidad > 0) {
            producto.cantidadDisponible += cantidad
            true
        } else {
            false
        }
    }
}