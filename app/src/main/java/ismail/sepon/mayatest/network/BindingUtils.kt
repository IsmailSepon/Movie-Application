package ismail.sepon.mayatest.network

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String?) {

    val imageBaseUrl : String = "https://image.tmdb.org/t/p/w500"+url


    Glide.with(view)
        .load(imageBaseUrl)
        .into(view)
}