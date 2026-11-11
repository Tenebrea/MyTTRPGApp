package com.example.myttrpgapp.domain.model

import androidx.annotation.DrawableRes
import myttrpgapp.shared.generated.resources.Res
import myttrpgapp.shared.generated.resources.profile_1
import myttrpgapp.shared.generated.resources.profile_2
import myttrpgapp.shared.generated.resources.profile_3
import org.jetbrains.compose.resources.DrawableResource

enum class UserAvatars(val avatar: DrawableResource) {
    AVATAR1(Res.drawable.profile_1),
    AVATAR2(Res.drawable.profile_2),
    AVATAR3(Res.drawable.profile_3)
}