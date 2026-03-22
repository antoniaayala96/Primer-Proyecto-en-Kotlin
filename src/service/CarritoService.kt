package service
import model.ItemCarrito

class CarritoService {

    fun mostrarCarrito(items: List<ItemCarrito>) {

        println("\n╔══════════════════════════════════════════╗")
        println("║         🛒  CARRITO DE COMPRAS           ║")
        println("╚══════════════════════════════════════════╝")

        if (items.isEmpty()) {
            println("\n  ⚠️  El carrito está vacío.")
            println("  Intenta agregar algún producto.\n")
            return
        }

        items.forEachIndexed { index, item ->
            val total = item.producto.precio * item.cantidad
            println("\n  📦 Producto #${index + 1}")
            println("  ┌─────────────────────────────────────┐")
            println("  │ Nombre  : ${item.producto.nombre.padEnd(length = 26)}│")
            println("  │ Marca   : ${item.producto.marca.padEnd(26)}│")
            println("  │ Precio  : ${"$%.2f".format(item.producto.precio).padEnd(26)}│")
            println("  │ Cantidad: ${item.cantidad.toString().padEnd(26)}│")
            println("  │ Total   : ${"$%.2f".format(total).padEnd(26)}│")
            println("  └─────────────────────────────────────┘")
        }

        val totalGeneral = items.sumOf { it.producto.precio * it.cantidad }
        println("\n╔══════════════════════════════════════════╗")
        println("║  💰 TOTAL GENERAL:${"$%.2f".format(totalGeneral).padEnd(24)}║")
        println("╚══════════════════════════════════════════╝\n")
    }
}