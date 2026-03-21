package model 

import model.Producto

data class ItemCarrito (
    val producto: Producto,
    var cantidad : Int
)

