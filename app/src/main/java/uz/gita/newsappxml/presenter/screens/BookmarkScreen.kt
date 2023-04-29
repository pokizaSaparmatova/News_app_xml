package uz.gita.newsappxml.presenter.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.newsappxml.R
import uz.gita.newsappxml.databinding.ScreenBookmarkBinding
import uz.gita.newsappxml.presenter.adapters.LocalDataAdapter
import uz.gita.newsappxml.presenter.viewModel.BookmarkViewModel
import uz.gita.newsappxml.presenter.viewModel.impl.BookmarkViewModelImpl

@AndroidEntryPoint
class BookmarkScreen:Fragment(R.layout.screen_bookmark) {
    private val viewBinding: ScreenBookmarkBinding by viewBinding(ScreenBookmarkBinding::bind)
    private val viewModel:BookmarkViewModel by viewModels<BookmarkViewModelImpl>()
    private val adapter:LocalDataAdapter by lazy { LocalDataAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.searchContainer.adapter = adapter

        viewModel.searchFlow.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.searchBookmark(search = query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewLifecycleOwner.lifecycleScope.launch {
                    delay(250)
                    if (newText != null) {
                        Log.d("zzzz","$newText")
                        viewModel.searchBookmark(search = newText)
                    }
                }
                return true
            }
        })

        adapter.triggerArticleClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToArticleScreen(it.article))
        }

    }
}