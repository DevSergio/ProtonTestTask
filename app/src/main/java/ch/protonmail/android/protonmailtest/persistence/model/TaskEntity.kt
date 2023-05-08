package ch.protonmail.android.protonmailtest.persistence.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey @NonNull val id: String,
    val creationDate: String? = null,
    val dueDate: String? = null,
    val encryptedDescription: String? = null,
    val encryptedTitle: String? = null,
    val image: String? = null
)