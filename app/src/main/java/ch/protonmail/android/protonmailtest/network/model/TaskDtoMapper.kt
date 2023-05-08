package ch.protonmail.android.protonmailtest.network.model

import ch.protonmail.android.protonmailtest.utils.CryptoHelper
import ch.protonmail.android.protonmailtest.domain.model.Task
import ch.protonmail.android.protonmailtest.domain.util.DomainMapper

class TaskDtoMapper(var cryptoHelper: CryptoHelper) : DomainMapper<TaskDto, Task> {

    override fun mapToDomainModel(model: TaskDto): Task {
        return Task(
            id = model.id,
            creationDate = model.creationDate,
            dueDate = model.dueDate,
            encryptedDescription = model.encryptedDescription?.let {
                cryptoHelper.instance.decrypt(it).toString().removeRange(0,8).dropLast(1)
            },
            encryptedTitle = model.encryptedTitle?.let {
                cryptoHelper.instance.decrypt(it).toString().removeRange(0,8).dropLast(1)
            },
            image = model.image
        )
    }

    override fun mapFromDomainModel(domainModel: Task): TaskDto {
        return TaskDto(
            id = domainModel.id,
            creationDate = domainModel.creationDate,
            dueDate = domainModel.dueDate,
            encryptedDescription = domainModel.encryptedDescription,
            encryptedTitle = domainModel.encryptedTitle,
            image = domainModel.image
        )
    }

    fun toDomainList(initial: List<TaskDto>): List<Task> {
        return initial.map { mapToDomainModel(it) }
    }
}