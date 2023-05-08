package ch.protonmail.android.protonmailtest.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ch.protonmail.android.protonmailtest.persistence.model.TaskEntity

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskList(tasks: List<TaskEntity>)

    @Query("SELECT * FROM TASKS WHERE id = :id_")
    suspend fun getTasks(id_: Long): List<TaskEntity>?

    @Query("SELECT * FROM TASKS")
    suspend fun getTasks(): List<TaskEntity>
}