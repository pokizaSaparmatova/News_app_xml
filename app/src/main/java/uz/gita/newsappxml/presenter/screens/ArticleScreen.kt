package uz.gita.newsappxml.presenter.screens

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.gita.newsappxml.R
import uz.gita.newsappxml.data.local.room.entity.ArticleEntity
import uz.gita.newsappxml.databinding.ScreenArticleBinding
import uz.gita.newsappxml.presenter.viewModel.ArticleViewModel
import uz.gita.newsappxml.presenter.viewModel.impl.ArticleViewModelImpl


@AndroidEntryPoint
class ArticleScreen : Fragment(R.layout.screen_article) {
    private val args: ArticleScreenArgs by navArgs()
    private val viewBinding: ScreenArticleBinding by viewBinding(ScreenArticleBinding::bind)
    private val viewModel: ArticleViewModel by viewModels<ArticleViewModelImpl>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        var isBookmarked = false
//        val article = args.article!!
//        viewBinding.webView.loadUrl(article.url!!)

        var isBookmarked = false

        val article = args.article!!
        article.url?.let {
            viewBinding.webView.loadUrl(it)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            isBookmarked = viewModel.check(title = article.url!!)
            Log.d("BBBB", "isbookmarked - $isBookmarked at top")
            if (isBookmarked) viewBinding.ivBookmark.setImageResource(R.drawable.ic_baseline_bookmark_24)//true bo'sa bo'ya
            else viewBinding.ivBookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24)//false bo'sa bo'yama


        }

        viewBinding.webView.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                viewBinding.progressBar.visibility = View.GONE
            }
        })

        viewBinding.ivBack.setOnClickListener { findNavController().popBackStack() }

//        viewBinding.ivBookmark.setOnClickListener {
//            viewModel.bookmark(ArticleEntity(0, article))
//            if (!isBookmarked) {
//                viewBinding.ivBookmark.setImageResource(R.drawable.ic_baseline_bookmark_24)
//                isBookmarked = true
//            } else {
//                viewBinding.ivBookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
//                isBookmarked = false
//            }
//        }


        viewBinding.ivBookmark.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                Log.d("BBBB", "isbookmarked - $isBookmarked before bookmark/unbookmark")

                viewModel.bookmark(ArticleEntity(0, article))
                isBookmarked = viewModel.check(title = article.url!!)
                Log.d("BBBB", "isbookmarked - $isBookmarked after bookmark/unbookmark")

                if (!isBookmarked) viewBinding.ivBookmark.setImageResource(R.drawable.ic_baseline_bookmark_24)//true bo'sa bo'ya
                else viewBinding.ivBookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24)//false bo'sa bo'yama

            }
        }

        viewBinding.ivShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    "${article.url}"
                )
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding.webView.destroy()
    }
}