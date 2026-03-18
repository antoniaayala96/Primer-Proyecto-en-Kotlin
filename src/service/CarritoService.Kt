
import model.ItemCarrito

class CarritoService {





    fun mostrarCarrito(items : List<ItemCarrito>){

        println("=== CARRITO DE COMPRAS ===")

        if(items.isEmpty()){
            println("el carrito esta vacio, intenta agregar algun producto")
            return
        }


        for(item in items) {
            println("===========================")
            val total = (item.producto.precio * item.cantidad);
            println("Nombre: ${item.producto.nombre}")
            println("Marca: ${item.producto.marca}")
            println("Precio: ${item.producto.precio}")
            println("Total unitario: ${total}");
            println("===========================")

        }

        val totalItems = items.sumOf {it.producto.precio * it.cantidad};
        println("Total: ${totalItems}");


    }
}