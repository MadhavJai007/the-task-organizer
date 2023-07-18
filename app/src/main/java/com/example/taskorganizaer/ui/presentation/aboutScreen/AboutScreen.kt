package com.example.taskorganizaer.ui.presentation.aboutScreen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.taskorganizaer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    navigateBack: () -> Unit,
) {
    val context = LocalContext.current
    val intentGithub = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/MadhavJai007"))
    val intentLinkedIn = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/madhav-jaisankar/"))

    Scaffold(
        topBar = { AboutTopBar(navigateBack) },
        containerColor = MaterialTheme.colorScheme.surface
    ) { padding ->
        Surface(
//            color = colorResource(id = R.color.colorBackground),
            shape = RoundedCornerShape(32.dp, 32.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp, 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "logo",
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.app_title),
//                    fontFamily = FontFamily(Font(R.font.playfair_display_regular)),
                    fontSize = 40.sp
                )
                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = stringResource(R.string.app_desc),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp),
//                    fontFamily = FontFamily(Font(R.font.playfair_display_regular))
                )
                Text(
                    text = stringResource(R.string.built_with_desc),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 10.dp),
//                    fontFamily = FontFamily(Font(R.font.playfair_display_regular))
                )
                Spacer(modifier = Modifier.height(100.dp))
                Button(
                    onClick = { ContextCompat.startActivity(context, intentGithub, null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color.Black,
                        contentColor = Color.White),
                    shape = RoundedCornerShape(20.dp),

                    ) {
                    Text(
                        text = "GitHub",
                        fontSize = 24.sp,
//                        fontFamily = FontFamily(Font(R.font.playfair_display_regular)),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { ContextCompat.startActivity(context, intentLinkedIn, null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
//                        containerColor = colorResource(id = R.color.linked_in_btn),
                        contentColor = Color.White),
                    shape = RoundedCornerShape(20.dp),
                ) {
                    Text(
                        text = "LinkedIn",
                        fontSize = 24.sp,
//                        fontFamily = FontFamily(Font(R.font.playfair_display_regular)),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}