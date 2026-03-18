import model.ItemCarrito
import service.Inventario
import service.CarritoService
import model.Producto

fun main() {

    val inventario = Inventario();
    val CarritoService = CarritoService();

    val primerProducto = Producto(1, "iPhone 14 Pro", "Apple", 999.99, 10);
    val segundoProducto = Producto(1, "Samsung Galaxy", "Samsung", 800.0, 10);

    val itemsCarrito = listOf(
        ItemCarrito(primerProducto, 2),
        ItemCarrito(segundoProducto, 1)
    )

    CarritoService.mostrarCarrito(itemsCarrito);


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

