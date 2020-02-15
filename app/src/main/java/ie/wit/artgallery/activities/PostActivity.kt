package ie.wit.artgallery.activities


import android.content.Context
import android.content.Intent

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity


import com.google.firebase.auth.FirebaseAuth
import ie.wit.artgallery.R
import ie.wit.artgallery.main.MainApp
import ie.wit.artgallery.models.ArtModel
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.activity_post.sign_out_button
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast


class PostActivity : AppCompatActivity(), AnkoLogger {
    var art = ArtModel(editTxtComment.text)
    val arts = ArrayList<ArtModel>()
    var edit = false
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        app = application as MainApp

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        sign_out_button.setOnClickListener {
            startActivity(LogInActivity.getLaunchIntent(this))
            FirebaseAuth.getInstance().signOut()
        }
        if (intent.hasExtra("post_edit")) {
            edit = true
            art = intent.extras?.getParcelable<ArtModel>("post_edit")!!
            editTxtComment.setText(art.comment)
            post_button.setText("Post")

        }
        post_button.setOnClickListener() {
            val editTxt = editTxtComment.text.toString()
            if (editTxt.isNotEmpty()) {
                info("add Button Pressed: $editTxt")
            } else {
                if (edit) {
                    app.arts.update(art.copy())
                } else {
                    app.arts.create(art.copy())
                }

            }
            setResult(AppCompatActivity.RESULT_OK)
            finish()

        }
        chooseImage.setOnClickListener {
            info ("Select image")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_post, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, PostActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
}
