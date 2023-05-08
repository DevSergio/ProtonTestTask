package ch.protonmail.android.protonmailtest.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import ch.protonmail.android.protonmailtest.persistence.model.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        const val DATABASE_NAME: String = "task_db"
    }
}