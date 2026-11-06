package com.example.myttrpgapp.navigation

import androidx.annotation.DrawableRes
import myttrpgapp.shared.generated.resources.Res
import myttrpgapp.shared.generated.resources.campaigns
import myttrpgapp.shared.generated.resources.character_sheet
import myttrpgapp.shared.generated.resources.profile
import org.jetbrains.compose.resources.DrawableResource

enum class NavRoutes(val icon: DrawableResource) {
    CAMPAIGNS(Res.drawable.campaigns),
    CHARACTERS(Res.drawable.character_sheet),
    PROFILE(Res.drawable.profile)
}