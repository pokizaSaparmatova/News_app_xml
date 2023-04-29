package uz.gita.newsappxml.presenter.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import uz.gita.newsappxml.R
import uz.gita.newsappxml.data.remote.model.Article
import uz.gita.newsappxml.databinding.ItemCategorizedBinding

class CategorizedAdapter : ListAdapter<Article, CategorizedAdapter.VH>(NewsDiffUtil) {

    private var articleClickListener: ((Article) -> Unit)? = null

    fun triggerArticleClickListener(block: (Article) -> Unit) {
        articleClickListener = block
    }


    inner class VH(private val itemBinding: ItemCategorizedBinding) : ViewHolder(itemBinding.root) {

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            itemBinding.imageView
            itemBinding.tvTitle.text = item.title
            val ph = itemBinding.tvTitle.context.getDrawable(R.drawable.ph)

            Glide
                .with(itemBinding.root)
                .load(item.urlToImage)
                .placeholder(ph)
                .into(itemBinding.imageView)
        }

        init {
            itemBinding.rootLayout.setOnClickListener {
                articleClickListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }
    }


    object NewsDiffUtil : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemCategorizedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        return holder.bind()
    }

}