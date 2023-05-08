package ch.protonmail.android.protonmailtest.network.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class TaskDto(
    @SerializedName("id")
    var id: String,
    @SerializedName("creation_date")
    var creationDate: String?,
    @SerializedName("due_date")
    var dueDate: String?,
    @SerializedName("encrypted_description")
    var encryptedDescription: String?,
    @SerializedName("encrypted_title")
    var encryptedTitle: String?,
    @SerializedName("image")
    var image: String?
)
