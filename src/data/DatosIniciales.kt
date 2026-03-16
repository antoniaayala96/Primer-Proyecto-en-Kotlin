package data

import model.Producto

object DatosIniciales {

    fun obtenerProductosIniciales(): MutableList<Producto> {
        return mutableListOf(
            Producto(1, "Galaxy S24", "Samsung", 950.00, 8),
            Producto(2, "iPhone 15", "Apple", 1200.00, 5),
            Producto(3, "Redmi Note 13", "Xiaomi", 350.00, 10),
            Producto(4, "Moto G84", "Motorola", 420.00, 7),
            Producto(5, "Pixel 8", "Google", 899.00, 4)
        )
    }
}