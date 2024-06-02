package com.integradis.greenhouse.feature_onboarding.ui
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.integradis.greenhouse.feature_onboarding.domain.PageData

@Composable
fun OnBoardingPage(
    page : PageData
) {
    Column() {
        Image(painter = painterResource(id = page.image),
            contentDescription = null)
    }
}