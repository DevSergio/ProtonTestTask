package ch.protonmail.android.protonmailtest.network

import ch.protonmail.android.protonmailtest.network.model.TaskDto
import retrofit2.http.GET

interface ApiService {

    @GET("tasks.json")
    suspend fun getTasks(): List<TaskDto>
}