package ch.protonmail.android.protonmailtest.repository

import androidx.annotation.WorkerThread
import ch.protonmail.android.protonmailtest.domain.data.DataState
import ch.protonmail.android.protonmailtest.domain.model.Task
import ch.protonmail.android.protonmailtest.network.ApiService
import ch.protonmail.android.protonmailtest.network.model.TaskDtoMapper
import ch.protonmail.android.protonmailtest.persistence.TaskDao
import ch.protonmail.android.protonmailtest.persistence.model.TaskEntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val apiService: ApiService,
    private val taskDao: TaskDao,
    private val taskDtoMapper: TaskDtoMapper,
    private val taskEntityMapper: TaskEntityMapper
) {
    @WorkerThread
    fun loadTasks(
    ): Flow<DataState<List<Task>>> = flow {
        try {
            emit(DataState.loading())

            val tasks = getTaskFromCache()
            if (tasks.isNotEmpty()) {
                emit(DataState.success(tasks))
            } else {
                val networkPosts = getTasksFromNetwork()
                taskDao.insertTaskList(taskEntityMapper.toEntityList(networkPosts))
                emit(DataState.success(networkPosts))
            }

        } catch (e: Exception) {
            emit(DataState.error(e.message ?: "Unknown Error"))
        }
    }

    private suspend fun getTaskFromCache(): List<Task> {
        return taskDao.getTasks().let { taskEntity ->
            taskEntityMapper.fromEntityList(taskEntity)
        }
    }

    private suspend fun getTasksFromNetwork(): List<Task> {
        return taskDtoMapper.toDomainList(apiService.getTasks())
    }
}