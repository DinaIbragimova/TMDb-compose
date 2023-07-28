package com.ibragimova.corecommon.container

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.ibragimova.corecommon.model.FullUrl

sealed class ImageValue(val value: Any?) {

    class Url(value: String) : ImageValue(value)

    class Image(value: Bitmap) : ImageValue(value)

    class ResImage(@DrawableRes value: Int) : ImageValue(value)

    class ResVector(@DrawableRes value: Int) : ImageValue(value)

    class Vector(value: ImageVector) : ImageValue(value)
}

fun FullUrl.imageValue(): ImageValue = ImageValue.Url(this)

fun Bitmap.imageValue(): ImageValue = ImageValue.Image(this)

fun @receiver:DrawableRes Int.imageValue(): ImageValue = ImageValue.ResImage(this)

fun ImageVector.imageValue(): ImageValue = ImageValue.Vector(this)