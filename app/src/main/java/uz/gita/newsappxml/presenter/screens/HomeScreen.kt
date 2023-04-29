package uz.gita.newsappxml.presenter.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.newsappxml.R
import uz.gita.newsappxml.databinding.ScreenHomeBinding
import uz.gita.newsappxml.presenter.adapters.CategorizedAdapter
import uz.gita.newsappxml.presenter.adapters.RemoteDataAdapter
import uz.gita.newsappxml.presenter.viewModel.HomeViewModel
import uz.gita.newsappxml.presenter.viewModel.impl.HomeViewModelImpl

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {
    private val viewBinding: ScreenHomeBinding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private val adapterLatest: RemoteDataAdapter by lazy { RemoteDataAdapter() }
    private val adapterCategorized: CategorizedAdapter by lazy { CategorizedAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.containerCategorized.adapter = adapterCategorized
        viewBinding.containerLatest.adapter = adapterLatest

        viewModel.latest.onEach {
            adapterLatest.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.topHeadlines.onEach {
            if (it.isEmpty()) viewBinding.placeHolder.visibility = View.VISIBLE
            if (it.isNotEmpty()) viewBinding.placeHolder.visibility = View.INVISIBLE
            adapterCategorized.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.categorizedHeadlines("technology")

        adapterCategorized.triggerArticleClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToArticleScreen(it))
        }

        viewBinding.mySearch.setOnClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToSearchScreen2())
        }

        adapterLatest.triggerArticleClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToArticleScreen(it))
        }

        viewBinding.myShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    "https://play.google.com/store/apps/details?id=${activity?.packageName}"
                )
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        viewBinding.priorityChipGroup.setOnCheckedStateChangeListener { chipGroup, i ->
            val id = i[0]
            val chip = chipGroup.findViewById(id) as Chip
            when (chip.text) {
                "technology" -> viewModel.categorizedHeadlines("technology")
                "entertainment" -> viewModel.categorizedHeadlines("entertainment")
                "health" -> viewModel.categorizedHeadlines("health")
                "science" -> viewModel.categorizedHeadlines("science")
                "sport" -> viewModel.categorizedHeadlines("sport")
                else -> viewModel.categorizedHeadlines("business")
            }
        }
    }

}

