package ch.protonmail.android.protonmailtest.di

import androidx.room.Room
import ch.protonmail.android.protonmailtest.TasksApplication
import ch.protonmail.android.protonmailtest.persistence.AppDatabase
import ch.protonmail.android.protonmailtest.persistence.TaskDao
import ch.protonmail.android.protonmailtest.persistence.model.TaskEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDb(app: TasksApplication): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTaskDao(db: AppDatabase): TaskDao {
        return db.taskDao()
    }

    @Singleton
    @Provides
    fun provideCacheTaskMapper(): TaskEntityMapper {
        return TaskEntityMapper()
    }
}