package com.ok.enjoyer.application.extinsions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.ok.enjoyer.R
import java.io.File

fun ImageView.setImage(url: String?, placeholder: Int? = R.mipmap.ic_launcher) {
    this.context.let {
        val options = RequestOptions()
        placeholder?.let { options.placeholder(it) }
        Glide.with(it).load(url ?: "")
                .apply(options)
                .into(this)
    }
}

fun ImageView?.setCircleImage(url: String?, placeholder: Int = R.mipmap.ic_launcher_round) {
    if (this == null) return // check that image view not null
    this.context.let {
        Glide.with(it).load(url ?: "").apply(RequestOptions
                .circleCropTransform()
                .placeholder(placeholder)).into(this)
    }
}

fun ImageView.setCircleImageFromBitmap(b: Bitmap, placeholder: Int = R.mipmap.ic_launcher_round) {
    this.context.let {
        Glide.with(it).load(b).apply(RequestOptions
                .circleCropTransform()
                .placeholder(placeholder)).into(this)
    }
}

fun ImageView.setCircleImageFromFile(file: File, placeholder: Int = R.mipmap.ic_launcher_round) {
    this.context.let {
        Glide.with(it).load(file).apply(RequestOptions
                .circleCropTransform()
                .placeholder(placeholder)).into(this)
    }
}

fun ImageView.loadImageAsCircle(path: String) {
    this.context.let {
        Glide.with(it).load(path).apply(RequestOptions
                .circleCropTransform()
                .placeholder(R.mipmap.ic_launcher_round)).into(this)
    }
}

fun ImageView.setImageWithFade(url: String, placeholder: Drawable?) {
    this.context?.let {
        val options = RequestOptions()
        placeholder?.let { options.placeholder(it) }
        Glide.with(it).load(url)
                .apply(options)
                .transition(withCrossFade(300))
                .into(this)
    }
}

fun AppCompatImageView.setThumbnailFromVideoUrl(videoUrl: String) {
    this.context.let {
        Glide.with(it)
                .load(videoUrl)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA))
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                        return false
                    }
                    override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        val mainHandler =  Handler(Looper.getMainLooper())
                        val myRunnable =  Runnable { setImageDrawable(resource) }
                        mainHandler.post(myRunnable)
                        return true
                    }
                }).submit()
    }
}

fun ImageView.changeTintColor(res: Int) {
    this.setColorFilter(ContextCompat.getColor(this.context,res),android.graphics.PorterDuff.Mode.MULTIPLY)
}

