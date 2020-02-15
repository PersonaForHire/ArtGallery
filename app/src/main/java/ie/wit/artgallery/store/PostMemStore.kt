package ie.wit.artgallery.store

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
        val foundPost: ArtModel? = posts.find { it.id == id }
        return foundPost
    }

    override fun create(post: ArtModel) {
        post.id = getId()
        posts.add(post)
        logAll()
    }

    override fun update(art: ArtModel) {
        var foundPost: ArtModel? = posts.find { p -> p.id == art.id }
        if (foundPost != null) {
            foundPost.comment = art.comment
            logAll()
        }
    }


    fun logAll() {
        Log.v("Post","** Posts in the feed**")
        posts.forEach { Log.v("Post","${it}") }
    }
}