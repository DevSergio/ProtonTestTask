package ch.protonmail.android.protonmailtest.domain.model

import java.io.Serializable

data class Task(
    val id: String,
    val creationDate: String? = null,
    val dueDate: String? = null,
    val encryptedDescription: String? = null,
    val encryptedTitle: String? = null,
    val image: String? = null
) : Serializable