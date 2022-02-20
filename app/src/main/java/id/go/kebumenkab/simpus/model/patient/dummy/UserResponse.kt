package id.go.kebumenkab.simpus.model.patient.dummy

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("token")
	val token: String? = null
)
