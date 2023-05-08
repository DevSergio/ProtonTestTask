package ch.protonmail.android.protonmailtest.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ch.protonmail.android.protonmailtest.R
import ch.protonmail.android.protonmailtest.ui.adapter.TabsAdapter
import ch.protonmail.android.protonmailtest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.toolbar.setTitleTextColor(Color.BLACK)
        setSupportActionBar(binding.toolbar)
        initTabs()
    }

    private fun initTabs() {
        val adapter = TabsAdapter(this, supportFragmentManager)
        binding.pager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.pager)
    }
}