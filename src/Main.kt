import service.Inventario
import service.CarritoService

fun pausar() {
    println("\nPresione Enter para continuar...")
    readLine()
}

fun mostrarMenu() {
    println("\n======================================")
    println("   SISTEMA DE CARRITO DE COMPRAS")
    println("======================================")
    println("1. Ver productos disponibles")
    println("2. Agregar producto al carrito")
    println("3. Eliminar producto del carrito")
    println("4. Ver carrito")
    println("5. Salir")
    print("Seleccione una opción: ")
}

fun main() {
    val inventario = Inventario()
    val carritoService = CarritoService()

    var opcion: Int

    do {
        mostrarMenu()
        opcion = readLine()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> {
                println("\n--- PRODUCTOS DISPONIBLES ---")
                inventario.mostrarProductos()
                pausar()
            }

            2 -> {
                println("\n--- AGREGAR PRODUCTO AL CARRITO ---")
                inventario.mostrarProductos()

                print("\nIngrese el ID del producto que desea agregar: ")
                val idProducto = readLine()?.toIntOrNull()

                print("Ingrese la cantidad que desea agregar: ")
                val cantidad = readLine()?.toIntOrNull()

                if (idProducto == null || cantidad == null) {
                    println("\nEntrada inválida. Debe ingresar valores numéricos.")
                } else {
                    val producto = inventario.buscarProductoPorId(idProducto)

                    if (producto != null) {
                        carritoService.agregarProducto(producto, cantidad, inventario)
                    } else {
                        println("\nNo se encontró un producto con ese ID.")
                    }
                }

                pausar()
            }

            3 -> {
                println("\n--- ELIMINAR PRODUCTO DEL CARRITO ---")
                carritoService.mostrarCarrito()

                print("\nIngrese el ID del producto que desea eliminar: ")
                val idProducto = readLine()?.toIntOrNull()

                print("Ingrese la cantidad que desea eliminar: ")
                val cantidad = readLine()?.toIntOrNull()

                if (idProducto == null || cantidad == null) {
                    println("\nEntrada inválida. Debe ingresar valores numéricos.")
                } else {
                    carritoService.eliminarProducto(idProducto, cantidad, inventario)
                }

                pausar()
            }

            4 -> {
                println("\n--- CARRITO ACTUAL ---")
                carritoService.mostrarCarrito()
                pausar()
            }

            5 -> {
                println("\nGracias por utilizar el sistema. ¡Hasta luego!")
            }

            else -> {
                println("\nOpción inválida. Intente nuevamente.")
                pausar()
            }
        }

    } while (opcion != 5)
}