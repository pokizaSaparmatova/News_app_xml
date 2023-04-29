package uz.gita.newsappxml.presenter.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsappxml.R
import uz.gita.newsappxml.databinding.ScreenMainBinding
import uz.gita.newsappxml.presenter.adapters.MainPagerAdapter

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val viewBinding: ScreenMainBinding by viewBinding(ScreenMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.pager.adapter = MainPagerAdapter(requireActivity())
        viewBinding.pager.isUserInputEnabled = false

        viewBinding.bottomNavBarMain.setOnItemSelectedListener {
            val position = when (it.itemId) {
                R.id.homeScreen -> 0
                else -> 1
            }

            viewBinding.pager.setCurrentItem(position,true)

            true
        }

    }
}