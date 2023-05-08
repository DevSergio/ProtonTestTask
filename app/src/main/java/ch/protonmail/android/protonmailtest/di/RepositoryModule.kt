package ch.protonmail.android.protonmailtest.di

import ch.protonmail.android.protonmailtest.network.ApiService
import ch.protonmail.android.protonmailtest.network.model.TaskDtoMapper
import ch.protonmail.android.protonmailtest.persistence.TaskDao
import ch.protonmail.android.protonmailtest.persistence.model.TaskEntityMapper
import ch.protonmail.android.protonmailtest.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideTasksRepository(
        apiService: ApiService,
        taskDao: TaskDao,
        taskDtoMapper: TaskDtoMapper,
        taskEntityMapper: TaskEntityMapper
    ): TaskRepository {
        return TaskRepository(apiService, taskDao, taskDtoMapper, taskEntityMapper)
    }
}