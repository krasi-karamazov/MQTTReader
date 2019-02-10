package kpk.dev.model.poko

import com.squareup.moshi.Json

data class UserAddress(
    @Json(name = "ID")
    val ID: String,
    @Json(name = "address")
    val address: String
)