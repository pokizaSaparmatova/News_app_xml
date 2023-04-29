package uz.gita.newsappxml.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import uz.gita.newsappxml.R
import uz.gita.newsappxml.data.remote.model.Article
import uz.gita.newsappxml.databinding.ItemEverythingMainBinding

class RemoteDataAdapter : PagingDataAdapter<Article, RemoteDataAdapter.VH>(PassengersComparator) {

    private var articleClickListener: ((Article) -> Unit)? = null

    fun triggerArticleClickListener(block: (Article) -> Unit) {
        articleClickListener = block
    }

    inner class VH(private val itemBinding: ItemEverythingMainBinding) :
        ViewHolder(itemBinding.root) {

        fun bind() {
            val item = getItem(absoluteAdapterPosition)!!

            itemBinding.tvTitle.text = item.title
            itemBinding.tvDate.text = item.publishedAt?.substring(0..9)

            val ph = itemBinding.tvTitle.context.getDrawable(R.drawable.ph)

            Glide
                .with(itemBinding.root)
                .load(item.urlToImage)
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

    object PassengersComparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

}