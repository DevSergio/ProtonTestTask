package ch.protonmail.android.protonmailtest.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ch.protonmail.android.protonmailtest.R
import ch.protonmail.android.protonmailtest.ui.tasks_all.AllTasksFragment
import ch.protonmail.android.protonmailtest.ui.upcoming.UpcomingFragment

class TabsAdapter(val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return AllTasksFragment()
        }
        return UpcomingFragment()
    }

    override fun getPageTitle(position: Int): CharSequence {
        return if (position == 0) context.getString(R.string.all_tasks) else context.getString(R.string.upcoming_tasks)
    }

    override fun getCount(): Int {
        return 2
    }
}