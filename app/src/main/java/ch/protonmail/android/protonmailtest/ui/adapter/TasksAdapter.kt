package ch.protonmail.android.protonmailtest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ch.protonmail.android.protonmailtest.R
import ch.protonmail.android.protonmailtest.databinding.ItemTaskBinding
import ch.protonmail.android.protonmailtest.domain.model.Task
import ch.protonmail.android.protonmailtest.utils.DateUtils.INPUT_FORMAT
import ch.protonmail.android.protonmailtest.utils.DateUtils.OUTPUT_FORMAT
import ch.protonmail.android.protonmailtest.utils.DateUtils.getDateInFormat
import ch.protonmail.android.protonmailtest.utils.RecyclerViewEvent
import coil.load
import coil.transform.RoundedCornersTransformation

class TasksAdapter(private val listener: RecyclerViewEvent) :
    RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    private var data: List<Task> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) = with(holder.binding) {
        data[position].let {
            title.text = it.encryptedTitle
            image.load(it.image) {
                size(250)
                transformations(RoundedCornersTransformation(15f))
                placeholder(R.drawable.ic_launcher_background)
            }
            description.text =
                it.encryptedDescription
            creationDate.text = it.creationDate?.getDateInFormat(INPUT_FORMAT, OUTPUT_FORMAT)
            dueDate.text = it.dueDate?.getDateInFormat(INPUT_FORMAT, OUTPUT_FORMAT)
        }
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    fun getItem(position: Int): Task? {
        return data[position]
    }

    fun submitData(list: List<Task>) {
        data = list
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        init {
            binding.item.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

}