package ch.protonmail.android.protonmailtest.di

import android.content.Context
import ch.protonmail.android.protonmailtest.TasksApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): TasksApplication {
        return app as TasksApplication
    }
}