package uz.gita.newsappxml.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.newsappxml.R
import uz.gita.newsappxml.data.local.room.entity.ArticleEntity
import uz.gita.newsappxml.databinding.ItemEverythingMainBinding

class LocalDataAdapter : ListAdapter<ArticleEntity, LocalDataAdapter.VH>(PassengersComparator) {

    private var articleClickListener: ((ArticleEntity) -> Unit)? = null

    fun triggerArticleClickListener(block: (ArticleEntity) -> Unit) {
        articleClickListener = block
    }

    inner class VH(private val itemBinding: ItemEverythingMainBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind() {
            val item = getItem(absoluteAdapterPosition)!!

            itemBinding.tvTitle.text = item.article.title
            itemBinding.tvDate.text = item.article.publishedAt?.substring(0..9)

            val ph = itemBinding.tvTitle.context.getDrawable(R.drawable.ph)

            Glide
                .with(itemBinding.root)
                .load(item.article.urlToImage)
                .placeholder(ph)
                .into(itemBinding.ivItem)
        }

        init {
            itemBinding.rootContainer.setOnClickListener {
                articleClickListener?.invoke(getItem(absoluteAdapterPosition)!!)
            }
        }
    }


    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemEverythingMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    object PassengersComparator : DiffUtil.ItemCallback<ArticleEntity>() {
        override fun areItemsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
            return oldItem.article.url == newItem.article.url
        }

        override fun areContentsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
            return oldItem == newItem
        }
    }
}