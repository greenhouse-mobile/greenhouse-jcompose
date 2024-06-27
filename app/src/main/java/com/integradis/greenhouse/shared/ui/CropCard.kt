package com.integradis.greenhouse.shared.ui


import android.graphics.Bitmap
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Yard
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.request.RequestOptions
import com.integradis.greenhouse.model.data.crops.Crop
import com.integradis.greenhouse.model.data.crops.CropPhase
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.Typography
import com.integradis.greenhouse.ui.theme.errorRed
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import java.security.MessageDigest
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CropCard(
    imageUrl: String,
    crop: Crop,
    navigateTo: () -> Unit,
    selectCrop: () -> Unit,
    deleteCrop: () -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

    val formatter = DateTimeFormatter.ofPattern("EE MMM dd yyyy HH:mm:ss OOOO")

    ElevatedCard(
        modifier = Modifier
            .padding(20.dp)
            .clickable { navigateTo() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
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
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "ID:",
                    modifier = Modifier.weight(1f),
                    style = Typography.labelLarge,
                    color = Color.Black
                )
                Text(
                    text= "View logs",
                    style = Typography.labelLarge,
                    color = PrimaryGreen40,
                    modifier = Modifier.padding(2.dp)
                )
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowForward,
                    contentDescription = "Forward",
                    tint = PrimaryGreen40
                )
            }
            Row{
                Text(
                    text = "",
                    modifier = Modifier.weight(1f)
                )
                if (showDialog) {
                    DeleteItemPopUp(
                        onDismissRequest = { showDialog = false },
                        onClickDismissButton = { showDialog = false },
                        onConfirmButton = { showDialog = false },
                        id = crop.id,
                        type = "crop"
                    )
                }

                Row( modifier = Modifier.clickable { showDialog = true },
                    verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = crop.id,
                        style = Typography.labelSmall,
                        color = Color.Gray,
                        modifier = Modifier.padding(end = 30.dp)
                    )
                    Text(
                        text = "Delete",
                        color = errorRed,
                        style = Typography.labelLarge,
                    )
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Settings Icon",
                        tint = errorRed,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
            Row {
                Icon(
                    Icons.Outlined.Yard,
                    contentDescription = "Crop Icon",
                    tint = PrimaryGreen40,
                )
                Text(
                    text = "Start Date: ${crop.startDate}",
                    style = Typography.labelLarge,
                    modifier = Modifier.padding(2.dp)
                )
            }
            Row {
                Icon(
                    Icons.Outlined.AccessTime,
                    contentDescription = "Phase Icon",
                    tint = PrimaryGreen40
                )
                Text(
                    text = "Current Phase: ${CropPhase.getValueOf(crop.phase).getPhaseName()}",
                    style = Typography.labelLarge,
                    modifier = Modifier.padding(2.dp)
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