package com.example.myclothingstore.data

data class Cloth(
    val name: String = "",
    val price: Int = 0,
    val description: String = "",
    val image: String = "",
    val category: Category = Category.T_SHIRT,
    val size: Size = Size.S,
) {
    enum class Size(val size: String) {
        S("S"),
        M("M"),
        L("L"),
        XL("XL"),
        XXL("XXL"),
    }
    enum class Category(val category: String) {
        T_SHIRT("T-SHIRT"),
        JACKET("JACKET"),
        PANTS("PANTS"),
        SHOES("SHOES"),
        ACCESSORIES("ACCESSORIES"),
    }
}
