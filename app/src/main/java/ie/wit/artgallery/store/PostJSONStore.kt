package ie.wit.artgallery.store

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import ie.wit.artgallery.helpers.exists
import ie.wit.artgallery.helpers.read
import ie.wit.artgallery.helpers.write
import ie.wit.artgallery.models.ArtModel
import org.jetbrains.anko.AnkoLogger
import java.util.*

val JSON_FILE = "posts.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<ArtModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class PostJSONStore : PostStore ,AnkoLogger {

    val context: Context
    var arts = mutableListOf<ArtModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<ArtModel> {
        return arts
    }

    override fun findById(id: Long): ArtModel? {
        val foundPost: ArtModel? = arts.find { it.id == id }
        return foundPost
    }

    override fun create(art: ArtModel) {
        art.id = generateRandomId()
        arts.add(art)
        serialize()
    }


    override fun update(art: ArtModel) {
        val artList = findAll() as ArrayList<ArtModel>
        var foundPost: ArtModel? = artList.find { p -> p.id == art.id }
        if (foundPost != null) {
            foundPost.comment = art.comment
            foundPost.image = art.image
            logAll()
        }
        serialize()
    }
    override fun delete(art: ArtModel) {
        arts.remove(art)
        serialize()
    }

    fun logAll() {
        Log.v("Post","** Posts in the feed**")
        arts.forEach { Log.v("Post","${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(arts, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        arts = Gson().fromJson(jsonString, listType)
    }
}