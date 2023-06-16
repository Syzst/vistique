package com.bangkitcapstone.vistique.model

import com.google.gson.annotations.SerializedName

data class BatikResponse(

	@field:SerializedName("Response")
	val response: List<ResponseItem>
)

data class ResponseItem(

	@field:SerializedName("pbatik_photo")
	val pbatikPhoto: String,

	@field:SerializedName("pbatik_history")
	val pbatikHistory: String,

	@field:SerializedName("pbatik_name")
	val pbatikName: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("pbatik_description")
	val pbatikDescription: String,

	@field:SerializedName("pbatik_price")
	val pbatikPrice: String,
)
