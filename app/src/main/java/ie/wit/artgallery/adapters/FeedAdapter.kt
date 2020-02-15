package ie.wit.artgallery.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.artgallery.R
import ie.wit.artgallery.helpers.readImageFromPath
import ie.wit.artgallery.models.ArtModel
import kotlinx.android.synthetic.main.activity_post.view.*
import kotlinx.android.synthetic.main.activity_post.view.editTxtComment
import kotlinx.android.synthetic.main.card_feed_posts.view.*

interface PostListener{
    fun onPostClick(art: ArtModel)
}

class FeedAdapter constructor(private var arts: List<ArtModel>, private val listener: PostListener) : RecyclerView.Adapter<FeedAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_feed_posts, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val art = arts[holder.adapterPosition]
        holder.bind(art,listener)
    }

    override fun getItemCount(): Int = arts.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(art: ArtModel,listener: PostListener) {
            itemView.editTxtComment.text = art.comment
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, art.image))
            itemView.setOnClickListener { listener.onPostClick(art) }
        }

    }

}