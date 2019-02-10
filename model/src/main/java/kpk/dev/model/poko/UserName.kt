package kpk.dev.model.poko

import com.squareup.moshi.Json

data class UserName(
    @Json(name = "ID")
    val ID: String,
    @Json(name = "name")
    val name: String
)