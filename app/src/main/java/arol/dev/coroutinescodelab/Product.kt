package arol.dev.coroutinescodelab

import org.json.JSONObject

data class Product(
    val id: Long,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating,
) {
    companion object {
        fun fromJSONObject(productAsJSONObject: JSONObject): Product = Product(
            id = productAsJSONObject.getLong("id"),
            title = productAsJSONObject.getString("title"),
            price = productAsJSONObject.getDouble("price"),
            description = productAsJSONObject.getString("description"),
            category = productAsJSONObject.getString("category"),
            image = productAsJSONObject.getString("image"),
            rating = Rating.fromJSONObject(productAsJSONObject.getJSONObject("rating")),
        )
    }
}

data class Rating(
    val rate: Double,
    val count: Long,
) {
    companion object {
        fun fromJSONObject(jsonObject: JSONObject): Rating = Rating(
            rate = jsonObject.getDouble("rate"),
            count = jsonObject.getLong("count"),
        )
    }
}