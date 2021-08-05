package info.softweb.gauravtatvsofttest.model


import com.google.gson.annotations.SerializedName

data class UserListResponseModel(
    @SerializedName("data")
    var `data`: List<Data>?= listOf(),
    @SerializedName("page")
    var page: Int?=null,
    @SerializedName("per_page")
    var perPage: Int?=null,
    @SerializedName("support")
    var support: Support?= Support(),
    @SerializedName("total")
    var total: Int?=null,
    @SerializedName("total_pages")
    var totalPages: Int?=null
)

data class Support(
    @SerializedName("text")
    var text: String?=null,
    @SerializedName("url")
    var url: String?=null
)

data class Data(
    @SerializedName("avatar")
    var avatar: String?=null,
    @SerializedName("email")
    var email: String?=null,
    @SerializedName("first_name")
    var firstName: String?=null,
    @SerializedName("id")
    var id: Int?=null,
    @SerializedName("last_name")
    var lastName: String?=null
)