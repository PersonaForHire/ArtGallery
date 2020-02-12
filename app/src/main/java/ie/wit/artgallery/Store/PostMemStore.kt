package ie.wit.artgallery.Store

import android.util.Log
import ie.wit.artgallery.models.ArtModel

var lastId :Long = 0
internal fun getId():Long{
    return lastId++
}

class PostMemStore : PostStore{
    val posts = ArrayList<ArtModel>()

    override fun findAll(): List<ArtModel> {
        return posts
    }

    override fun findById(id:Long) : ArtModel? {
        val foundDonation: ArtModel? = posts.find { it.id == id }
        return foundDonation
    }

    override fun create(post: ArtModel) {
        post.id = getId()
        posts.add(post)
        logAll()
    }

    fun logAll() {
        Log.v("Post","** Posts in the feed**")
        posts.forEach { Log.v("Post","${it}") }
    }
}