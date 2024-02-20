package com.dragonslotos.lotoseveryday.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.dragonslotos.lotoseveryday.R

class Fonts(){
    companion object {
        private val kaushan = FontFamily(
            Font(R.font.kuashan, FontWeight.Normal),
        )
        private val kavivanar = FontFamily(
            Font(R.font.kavivanar_regular, FontWeight.Normal),
        )
        private val karma = FontFamily(
            Font(R.font.karma_light, FontWeight.Light),
            Font(R.font.karma_regular, FontWeight.Normal),
            Font(R.font.karma_medium, FontWeight.Medium),
            Font(R.font.karma_semibold, FontWeight.SemiBold),
            Font(R.font.karma_bold, FontWeight.Bold),
        )

        private val pairfair = FontFamily(
            Font(R.font.playfairfisplay_italic, FontWeight.Normal, FontStyle.Italic),
            Font(R.font.playfair_display, FontWeight.Light, FontStyle.Normal),
            Font(R.font.playfair_display, FontWeight.Bold, FontStyle.Normal)
        )
        fun Karma(): FontFamily{
            return karma
        }
        fun Kaushan(): FontFamily {
            return kaushan;
        }
        fun Kavivanar(): FontFamily {
            return kavivanar;
        }
        fun Pairfair(): FontFamily {
            return pairfair;
        }
    }

}