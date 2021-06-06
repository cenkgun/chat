package com.cenkgun.presentation.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SessionModel(
    var user: UserModel
) : Parcelable
