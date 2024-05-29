package com.integradis.greenhouse.shared.ui


import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Yard
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Yard
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.request.RequestOptions
import com.integradis.greenhouse.shared.domain.Crop
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.Subtitle
import com.integradis.greenhouse.ui.theme.Typography
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import java.security.MessageDigest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CropCard(
    imageUrl: String,
    crop: Crop,
    navigateTo: () -> Unit,
    selectCrop: () -> Unit,
    deleteCrop: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = Modifier
            .padding(30.dp)
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
            modifier = Modifier.padding(10.dp)
        ) {
            Row {
                Text(
                    text = "Crop ID: ${crop.id}",
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
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        icon = { Icon(Icons.Filled.Delete, contentDescription = "Delete Icon", tint = Color.Red, modifier = Modifier.size(100.dp)) },
                        text = { Text("Do you want to notify an admin for the deletion of crop ${crop.id}?") },
                        containerColor = Color.White,
                        dismissButton = {
                            Button(
                                onClick = { showDialog = false },
                                colors = ButtonDefaults.buttonColors(
                                    Color.White
                                )
                            ) {
                                Text("Cancel", color = Color.Red)
                            }
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    //onDeleteClicked()
                                    showDialog = false
                                },
                                colors = ButtonDefaults.buttonColors(
                                    Color.Red
                                )
                            ) {
                                Text("Confirm")
                            }
                        }
                    )
                }

                Row( modifier = Modifier.clickable { showDialog = true },
                    verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "Delete",
                        color = Color.Red,
                        modifier = Modifier.padding(top = 1.dp)
                    )
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Settings Icon",
                        tint = Color.Red,
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
                    text = "Current Phase: ${crop.phase.getPhaseName()}",
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