package com.integradis.greenhouse.shared.ui


import android.graphics.Bitmap
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.request.RequestOptions
import com.integradis.greenhouse.shared.domain.Crop
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import java.security.MessageDigest

@Composable
fun CropCard(
    imageUrl : String,
    crop : Crop
) {
    Card(
        modifier = Modifier.padding(20.dp)
    ) {
        GlideImage(
            imageModel = { imageUrl },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Inside,

            ),
            requestOptions = {
                RequestOptions()
                    .transform(CutOffCardImage())
            }
        )
        Column() {
            Row() {
                Text(
                    text = "Crop ID: ${crop.id}",
                    modifier = Modifier.weight(4f)
                )
                Text(text= "View logs ->")
            }
            Row() {
                Text(
                    text = "",
                    modifier = Modifier.weight(4f)
                )
                Text(
                    text = "Settings"
                )
            }
            Row() {
                Text(
                    text = "Start Date: ${crop.startDate}"
                )
            }
            Row() {
                Text(
                    text = "Current Phase: ${crop.phase}"
                )
            }
        }
    }
}

class CutOffCardImage : BitmapTransformation() {
    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap =
        Bitmap.createBitmap(
            toTransform,
            0,
            0,
            toTransform.width,
            toTransform.height - (toTransform.height/2))

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {}
}