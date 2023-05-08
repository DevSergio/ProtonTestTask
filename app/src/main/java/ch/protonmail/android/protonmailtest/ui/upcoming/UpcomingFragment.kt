package ch.protonmail.android.protonmailtest.ui.upcoming

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.protonmail.android.protonmailtest.databinding.FragmentUpcomingBinding
import ch.protonmail.android.protonmailtest.ui.MainViewModel
import ch.protonmail.android.protonmailtest.ui.adapter.TasksAdapter
import ch.protonmail.android.protonmailtest.ui.details.DetailsActivity
import ch.protonmail.android.protonmailtest.utils.RecyclerViewEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class UpcomingFragment : Fragment(), RecyclerViewEvent {

    private val binding by lazy { FragmentUpcomingBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapter: TasksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        adapter = TasksAdapter(this)
        val recycler = binding.recyclerView
        recycler.addItemDecoration(dividerItemDecoration)
        recycler.adapter = adapter
        recycler.layoutManager = layoutManager

        lifecycleScope.launch {
            viewModel.tasks.collect {
                it?.let { adapter.submitData(it.sortedBy { it.dueDate }) }
            }
        }

        return binding.root
    }

    override fun onItemClick(position: Int) {
        Timber.d(adapter.getItem(position).toString())
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra(ITEM, adapter.getItem(position))
        startActivity(intent)
    }

    companion object {
        const val ITEM = "item"
    }
}