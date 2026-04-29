package com.example.exploraapp.ui.elements
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddTouristicPlaceScreen(
    onBackClick: () -> Unit = {},
    onPublishClick: (String, String, String, String) -> Unit = { _, _, _, _ -> }
) {
    var placeName by remember { mutableStateOf("") }
    var department by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val bgColor = Color(0xFFF5F1F4)
    val cardColor = Color(0xFFF1ECEF)
    val inputColor = Color(0xFFE4DFE3)
    val labelColor = Color(0xFF5E5961)
    val titleColor = Color(0xFF9F2D0D)
    val gradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFB92E00),
            Color(0xFFF36D3D)
        )
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = bgColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 14.dp)
        ) {
            HeaderSection(onBackClick = onBackClick, titleColor = titleColor)

            Spacer(modifier = Modifier.height(28.dp))

            IntroCard(gradient = gradient)

            Spacer(modifier = Modifier.height(26.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(cardColor, RoundedCornerShape(34.dp))
                    .padding(horizontal = 20.dp, vertical = 28.dp)
            ) {
                AddPlaceTextField(
                    label = "NOMBRE DEL LUGAR",
                    value = placeName,
                    onValueChange = { placeName = it },
                    placeholder = "Ej: Cascada del Fin del Mundo",
                    bgColor = inputColor
                )

                Spacer(modifier = Modifier.height(22.dp))

                AddPlaceTextField(
                    label = "DEPARTAMENTO",
                    value = department,
                    onValueChange = { department = it },
                    placeholder = "Ej: Putumayo",
                    bgColor = inputColor
                )

                Spacer(modifier = Modifier.height(22.dp))

                AddPlaceTextField(
                    label = "CIUDAD",
                    value = city,
                    onValueChange = { city = it },
                    placeholder = "Ej: Mocoa",
                    bgColor = inputColor
                )

                Spacer(modifier = Modifier.height(22.dp))

                AddPlaceTextField(
                    label = "DESCRIPCIÓN",
                    value = description,
                    onValueChange = { description = it },
                    placeholder = "Cuéntanos por qué este lugar es especial...",
                    bgColor = inputColor,
                    singleLine = false,
                    minLines = 5
                )

                Spacer(modifier = Modifier.height(38.dp))

                Button(
                    onClick = {
                        onPublishClick(
                            placeName.trim(),
                            department.trim(),
                            city.trim(),
                            description.trim()
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    shape = RoundedCornerShape(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    contentPadding = PaddingValues()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(gradient, RoundedCornerShape(40.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Send,
                                contentDescription = null,
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.height(0.dp))
                            Text(
                                text = "   Publicar",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun HeaderSection(
    onBackClick: () -> Unit,
    titleColor: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                tint = titleColor
            )
        }

        Text(
            text = "AddPlace",
            color = titleColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun IntroCard(
    gradient: Brush
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(246.dp)
            .background(gradient, RoundedCornerShape(38.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Comparte tu\ndescubrimiento",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 28.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Ayuda a otros viajeros a encontrar los tesoros\nescondidos de nuestra tierra.",
                color = Color.White.copy(alpha = 0.92f),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun AddPlaceTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    bgColor: Color,
    singleLine: Boolean = true,
    minLines: Int = 1
) {
    Column {
        Text(
            text = label,
            color = Color(0xFF5D5860),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color(0xFFB0A9AF),
                    fontSize = 14.sp
                )
            },
            singleLine = singleLine,
            minLines = minLines,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = bgColor,
                unfocusedContainerColor = bgColor,
                disabledContainerColor = bgColor,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            )
        )
    }
}