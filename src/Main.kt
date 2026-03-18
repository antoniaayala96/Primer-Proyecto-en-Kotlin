import service.Inventario


fun main() {

    println("SISTEMA DE INVENTARIO - TIENDA DE TELÉFONOS")
    inventario.mostrarProductos()

    val idProducto = 2
    val cantidadSolicitada = 1

    println("\nIntentando comprar $cantidadSolicitada unidad(es) del producto con ID $idProducto...")

    if (inventario.hayStockSuficiente(idProducto, cantidadSolicitada)) {
        inventario.reducirStock(idProducto, cantidadSolicitada)
        println("Producto agregado al carrito. Inventario actualizado.")
    } else {
        println("No hay suficiente stock o el producto no existe.")
    }

    inventario.mostrarProductos()

    println("\nSimulando eliminación del carrito: devolviendo 1 unidad al inventario...")
    inventario.aumentarStock(idProducto, 1)

    inventario.mostrarProductos()
}

