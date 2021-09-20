package ru.konstantin.material.ui.picture


import com.google.gson.annotations.SerializedName

data class MarsList(
    @SerializedName("photos")
    var photos: List<Photo?>? = listOf()
) {
    data class Photo(
        @SerializedName("camera")
        var camera: Camera? = Camera(),
        @SerializedName("earth_date")
        var earthDate: String? = "",
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("img_src")
        var imgSrc: String? = "",
        @SerializedName("rover")
        var rover: Rover? = Rover(),
        @SerializedName("sol")
        var sol: Int? = 0
    ) {
        data class Camera(
            @SerializedName("full_name")
            var fullName: String? = "",
            @SerializedName("id")
            var id: Int? = 0,
            @SerializedName("name")
            var name: String? = "",
            @SerializedName("rover_id")
            var roverId: Int? = 0
        )

        data class Rover(
            @SerializedName("id")
            var id: Int? = 0,
            @SerializedName("landing_date")
            var landingDate: String? = "",
            @SerializedName("launch_date")
            var launchDate: String? = "",
            @SerializedName("name")
            var name: String? = "",
            @SerializedName("status")
            var status: String? = ""
        )
    }
}