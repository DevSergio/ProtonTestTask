package ch.protonmail.android.protonmailtest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.protonmail.android.protonmailtest.domain.model.Task
import ch.protonmail.android.protonmailtest.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val taskRepository: TaskRepository) :
    ViewModel() {

    private val _tasks = MutableSharedFlow<List<Task>?>()
    val tasks = _tasks.asSharedFlow()

    init {
        getTasks()
    }

    private fun getTasks() {
        taskRepository.loadTasks().onEach { dataState ->
            Timber.d(dataState.toString())
            _tasks.emit(dataState.data)
        }.launchIn(viewModelScope)
    }

}