package ie.wit.artgallery.activities


import android.content.Context
import android.content.Intent

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity


import com.google.firebase.auth.FirebaseAuth
import ie.wit.artgallery.R
import ie.wit.artgallery.models.ArtModel
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.activity_post.sign_out_button


class PostActivity : AppCompatActivity(){
    var art=ArtModel()
    val arts =ArrayList<ArtModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        setupUI()
        post_button.setOnClickListener {
            val editTxt = editTxtComment.text.toString()
            arts.add(art.copy())
            arts.forEach{}
        }
    }

    private fun setupUI() {
        sign_out_button.setOnClickListener {
            signOut()
        }
    }
    private fun signOut() {
        startActivity(LogInActivity.getLaunchIntent(this))
        FirebaseAuth.getInstance().signOut()
    }
    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, PostActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
}
