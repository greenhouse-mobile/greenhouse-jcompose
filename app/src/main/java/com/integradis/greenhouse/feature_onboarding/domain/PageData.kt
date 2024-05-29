package com.integradis.greenhouse.feature_onboarding.domain

import androidx.annotation.DrawableRes
import com.integradis.greenhouse.R

data class PageData(
    val title: String,
    val description : String,
    @DrawableRes val image : Int
)

val pages = listOf(
    PageData(
        title = "Bienvenido a Greenhouse",
        description = "Documenta todo proceso de tu compañia en una sola plataforma",
        image = R.drawable.primary_logo_greenhouse
    ),
    PageData(
        title = "a",
        description = "a",
        image = R.drawable.primary_logo_greenhouse
    ),
    PageData(
        title = "a",
        description = "a",
        image = R.drawable.primary_logo_greenhouse
    )
)