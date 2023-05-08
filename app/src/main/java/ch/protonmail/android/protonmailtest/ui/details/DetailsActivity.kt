package ch.protonmail.android.protonmailtest.ui.details

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import ch.protonmail.android.protonmailtest.R
import ch.protonmail.android.protonmailtest.databinding.ActivityDetailsBinding
import ch.protonmail.android.protonmailtest.domain.model.Task
import ch.protonmail.android.protonmailtest.ui.MainViewModel
import ch.protonmail.android.protonmailtest.ui.tasks_all.AllTasksFragment.Companion.ITEM
import ch.protonmail.android.protonmailtest.utils.DateUtils
import ch.protonmail.android.protonmailtest.utils.DateUtils.getDateInFormat
import coil.load
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.transform.RoundedCornersTransformation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailsBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.toolbar.setTitleTextColor(Color.BLACK)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        val task = intent.getSerializableExtra(ITEM) as? Task

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.tasks.collect {
                Timber.d(it.toString())
                binding.title.text = task?.encryptedTitle
                binding.description.text = task?.encryptedDescription
                binding.creationDate.text = task?.creationDate?.getDateInFormat(
                    DateUtils.INPUT_FORMAT,
                    DateUtils.OUTPUT_FORMAT
                )
                binding.dueDate.text = task?.dueDate?.getDateInFormat(
                    DateUtils.INPUT_FORMAT,
                    DateUtils.OUTPUT_FORMAT
                )
                binding.download.setOnClickListener {
                    binding.image.load(task?.image) {
                        size(250)
                        transformations(RoundedCornersTransformation(15f))
                        placeholder(R.drawable.ic_launcher_background)
                        listener(object : ImageRequest.Listener {
                            override fun onSuccess(request: ImageRequest, result: SuccessResult) {
                                super.onSuccess(request, result)
                                binding.download.visibility = View.GONE
                            }
                        })
                    }
                }
            }
        }
    }
}

