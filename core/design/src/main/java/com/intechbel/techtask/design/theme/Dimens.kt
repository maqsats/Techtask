package com.intechbel.techtask.design.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight as ComposeFontWeight

data class Dimens(
    val padding: Padding = Padding(),
    val borderRadius: BorderRadius = BorderRadius(),
    val iconSize: IconSize = IconSize(),
    val logoSize: LogoSize = LogoSize(),
    val toolbarSize: ToolbarSize = ToolbarSize(),
    val elevation: Elevation = Elevation(),
    val fontSize: FontSize = FontSize(),
    val fontWeight: FontWeight = FontWeight(),
    val lineHeight: LineHeight = LineHeight(),
    val letterSpacing: LetterSpacing = LetterSpacing(),
    val animationDuration: AnimationDuration = AnimationDuration(),
    val borderWidth: BorderWidth = BorderWidth(),
    val otpSize: OtpSize = OtpSize(),
    val componentSize: ComponentSize = ComponentSize(),
) {
    data class Padding(
        val zero: Dp = 0.dp,
        val quadrupleExtraSmall: Dp = 2.dp,
        val tripleExtraSmall: Dp = 4.dp,
        val hintTop: Dp = 6.dp,
        val doubleExtraSmall: Dp = 8.dp,
        val extraSmall: Dp = 10.dp,
        val small: Dp = 12.dp,
        val medium: Dp = 16.dp,
        val large: Dp = 20.dp,
        val extraLarge: Dp = 24.dp,
        val doubleExtraLarge: Dp = 32.dp,
        val tripleExtraLarge: Dp = 40.dp,
        val quadrupleExtraLarge: Dp = 48.dp,
        val quintupleExtraLarge: Dp = 56.dp,
        val exception: Dp = 18.dp,
    )

    data class BorderRadius(
        val radius0: Dp = 0.dp,
        val radius4: Dp = 4.dp,
        val radius6: Dp = 6.dp,
        val radius8: Dp = 8.dp,
        val radius12: Dp = 12.dp,
        val radius16: Dp = 16.dp,
        val radius24: Dp = 24.dp,
    )

    data class FontSize(
        val fontSize8: TextUnit = 8.sp,
        val fontSize10: TextUnit = 10.sp,
        val fontSize12: TextUnit = 12.sp,
        val fontSize14: TextUnit = 14.sp,
        val fontSize16: TextUnit = 16.sp,
        val fontSize18: TextUnit = 18.sp,
        val fontSize20: TextUnit = 20.sp,
        val fontSize24: TextUnit = 24.sp,
        val fontSize28: TextUnit = 28.sp,
        val fontSize40: TextUnit = 40.sp,
    )

    data class LineHeight(
        val lineHeight10: TextUnit = 10.sp,
        val lineHeight12: TextUnit = 12.sp,
        val lineHeight16: TextUnit = 16.sp,
        val lineHeight18: TextUnit = 18.sp,
        val lineHeight20: TextUnit = 20.sp,
        val lineHeight22: TextUnit = 22.sp,
        val lineHeight24: TextUnit = 24.sp,
        val lineHeight28: TextUnit = 28.sp,
        val lineHeight32: TextUnit = 32.sp,
        val lineHeight36: TextUnit = 36.sp,
        val lineHeight40: TextUnit = 40.sp,
        val lineHeight56: TextUnit = 56.sp,
    )

    data class FontWeight(
        val regular: ComposeFontWeight = ComposeFontWeight.Normal,
        val medium: ComposeFontWeight = ComposeFontWeight.Medium,
        val semiBold: ComposeFontWeight = ComposeFontWeight.SemiBold,
        val bold: ComposeFontWeight = ComposeFontWeight.Bold,
    )

    data class LetterSpacing(
        val letterSpacing0: TextUnit = 0.sp,
        val letterSpacing0_1: TextUnit = 0.1.sp,
        val letterSpacing0_2: TextUnit = 0.2.sp,
        val letterSpacing0_15: TextUnit = 0.15.sp,
        val letterSpacing0_25: TextUnit = 0.25.sp,
    )

    data class IconSize(
        val small: Dp = 16.dp,
        val standard: Dp = 20.dp,
        val medium: Dp = 24.dp,
        val large: Dp = 32.dp,
        val extraLarge: Dp = 36.dp,
        val tripleExtraLarge: Dp = 40.dp,
    )

    data class LogoSize(
        val width: Dp = 165.dp,
        val height: Dp = 36.dp,
    )

    data class ToolbarSize(
        val height: Dp = 58.dp,
    )

    data class Elevation(
        val toolbar: Dp = 12.dp,
    )

    data class AnimationDuration(
        val fast: Int = 250,
    )

    data class BorderWidth(
        val thin: Dp = 1.dp,
    )

    data class OtpSize(
        val maxCellWidth: Dp = 53.dp,
        val cellHeightForNonDefaultLength: Dp = 72.dp,
    )

    data class ComponentSize(
        val surveyMinHeight: Dp = 156.dp,
        val profileImageHeight: Dp = 120.dp,
        val mapHeight: Dp = 280.dp,
    )
}

