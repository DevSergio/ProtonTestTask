package ch.protonmail.android.protonmailtest.persistence.model

import ch.protonmail.android.protonmailtest.domain.model.Task
import ch.protonmail.android.protonmailtest.domain.util.DomainMapper

class TaskEntityMapper : DomainMapper<TaskEntity, Task> {

    override fun mapToDomainModel(model: TaskEntity): Task {
        return Task(
            id = model.id,
            creationDate = model.creationDate,
            dueDate = model.dueDate,
            encryptedDescription = model.encryptedDescription,
            encryptedTitle = model.encryptedTitle,
            image = model.image
        )
    }

    override fun mapFromDomainModel(domainModel: Task): TaskEntity {
        return TaskEntity(
            id = domainModel.id,
            creationDate = domainModel.creationDate,
            dueDate = domainModel.dueDate,
            encryptedDescription = domainModel.encryptedDescription,
            encryptedTitle = domainModel.encryptedTitle,
            image = domainModel.image
        )
    }


    fun fromEntityList(initial: List<TaskEntity>): List<Task>{
        return initial.map { mapToDomainModel(it) }
    }

    fun toEntityList(initial: List<Task>): List<TaskEntity>{
        return initial.map { mapFromDomainModel(it) }
    }

}