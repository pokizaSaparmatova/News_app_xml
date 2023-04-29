package uz.gita.newsappxml.presenter.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.newsappxml.presenter.screens.BookmarkScreen
import uz.gita.newsappxml.presenter.screens.HomeScreen

class MainPagerAdapter(f: FragmentActivity) : FragmentStateAdapter(f) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeScreen()
            }
            else -> {
                BookmarkScreen()
            }
        }
    }
}