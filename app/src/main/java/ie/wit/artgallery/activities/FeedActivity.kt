package ie.wit.artgallery.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.wit.artgallery.R
import ie.wit.artgallery.adapters.FeedAdapter
import ie.wit.artgallery.adapters.PostListener
import ie.wit.artgallery.main.MainApp
import ie.wit.artgallery.models.ArtModel
import kotlinx.android.synthetic.main.activity_feed.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult


class FeedActivity : AppCompatActivity(),PostListener {

    lateinit var app:MainApp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager as RecyclerView.LayoutManager?
        loadPosts()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadPosts()
        super.onActivityResult(requestCode, resultCode, data)
    }


    override fun onPostClick(art: ArtModel) {
        startActivityForResult(intentFor<PostActivity>().putExtra("post_edit", art), 0)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<PostActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadPosts(){
        showPosts(app.arts.findAll())
    }

    private fun showPosts(arts: List<ArtModel>){
        recyclerView.adapter = FeedAdapter(arts, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }





}





