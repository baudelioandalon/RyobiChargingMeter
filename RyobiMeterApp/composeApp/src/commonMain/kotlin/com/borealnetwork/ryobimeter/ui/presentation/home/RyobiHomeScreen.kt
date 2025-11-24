package com.borealnetwork.ryobimeter.ui.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.borealnetwork.ryobimeter.ui.theme.AccentGreen
import com.borealnetwork.ryobimeter.ui.theme.BgBottom
import com.borealnetwork.ryobimeter.ui.theme.BgTop
import com.borealnetwork.ryobimeter.ui.theme.CardWhite
import com.borealnetwork.ryobimeter.ui.theme.PillGreen
import com.borealnetwork.ryobimeter.ui.theme.PillTextGreen
import com.borealnetwork.ryobimeter.ui.theme.TextBlack
import com.borealnetwork.ryobimeter.ui.theme.TextGray
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ryobimeterapp.composeapp.generated.resources.Res
import ryobimeterapp.composeapp.generated.resources.ic_battery_level
import ryobimeterapp.composeapp.generated.resources.ic_charging
import ryobimeterapp.composeapp.generated.resources.ic_charging_battery
import ryobimeterapp.composeapp.generated.resources.ic_connected
import ryobimeterapp.composeapp.generated.resources.ic_down_left_arrow
import ryobimeterapp.composeapp.generated.resources.ic_fan
import ryobimeterapp.composeapp.generated.resources.ic_locked
import ryobimeterapp.composeapp.generated.resources.ic_use_energy
import ryobimeterapp.composeapp.generated.resources.ryobi_battery_2ah

@Preview
@Composable
fun RyobiHomeScreen() {
    // Fondo con degradado suave vertical
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing),
        topBar = {
            // 1. Header
            HeaderSection()
        }
    ) {
        Column(
            modifier = Modifier.padding(it).fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(BgTop, BgBottom)
                    )
                ).padding(horizontal = 30.dp)
                .verticalScroll(rememberScrollState())
        ) {


            // 2. Stats Row (Capacidad, Velocidad, Modelo)
            StatsRow(
                modifier = Modifier.padding(top = 16.dp)
            )

            // 3. Imagen de la batería (Placeholder)
            // Nota: En un caso real, usa Image(painterResource(id = ...))
            BatteryHeroImage()

            // 4. Grid de Tarjetas (Dashboard)
            DashboardGrid()

            // 5. Botón flotante inferior (Ventilador)
            FanControlCard()
        }
    }

}

// --- Componentes Individuales ---

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                BgTop
            )
            .padding(top = 16.dp, start = 30.dp, end = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Bienvenido",
                color = TextGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Boreal Network",
                color = TextBlack,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
        // Placeholder para la foto de perfil
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.LightGray.copy(alpha = 0.5f))
        )
    }
}

@Composable
fun StatsRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        StatItem(icon = Res.drawable.ic_battery_level, label = "Capacidad", value = "2 Ah")
        VerticalDivider(modifier = Modifier.height(40.dp))
        StatItem(
            icon = Res.drawable.ic_connected,
            label = "Velocidad\nde carga",
            value = "1 Ah",
            alignCenter = true
        )
        VerticalDivider(modifier = Modifier.height(40.dp))
        StatItem(icon = Res.drawable.ic_charging_battery, label = "Modelo", value = "2 Ah")
    }
}

@Composable
fun StatItem(icon: DrawableResource, label: String, value: String, alignCenter: Boolean = false) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = TextBlack
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = TextBlack,
            fontWeight = FontWeight.Bold,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            lineHeight = 14.sp
        )
        Text(
            text = value,
            fontSize = 12.sp,
            color = TextGray
        )
    }
}

@Composable
fun BatteryHeroImage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp).background(color = Color.Transparent),
        contentAlignment = Alignment.CenterEnd
    ) {
        // AQUÍ IRÍA TU IMAGEN REAL: Image(painter = painterResource(R.drawable.tu_bateria), ...)
        // Usamos un placeholder visual para simular la batería Ryobi
        Image(
            modifier = Modifier
                .size(width = 160.dp, height = 140.dp)
                .clip(RoundedCornerShape(16.dp)),
            painter = painterResource(Res.drawable.ryobi_battery_2ah),
            contentDescription = null
        )
    }
}

@Composable
fun DashboardGrid() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Columna Izquierda (Tarjeta Grande de Batería)
        Column(modifier = Modifier.weight(1f)) {
            BatteryStatusCard()
        }

        // Columna Derecha (Bloqueado y Uso de energía)
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            LockedStatusCard()
            EnergyUsageCard()
        }
    }
}

@Composable
fun BatteryStatusCard(percentage: Double = 76.0) {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp) // Altura aproximada para igualar la columna derecha
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header Card
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            Color(0xFFF5F5F5),
                            RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_charging),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text("Bateria 2AH", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            // Porcentaje y Pill de tiempo

            Text(
                modifier = Modifier.wrapContentHeight(),
                text = "$percentage%", fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )

            Surface(
                color = Color(0xFFE0E0E0),
                shape = RoundedCornerShape(25),
                modifier = Modifier.wrapContentHeight()
            ) {
                Text(
                    text = "1h 56 4s",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    fontSize = 10.sp,
                    color = TextGray
                )
            }

            // Barras gráficas de batería
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        PillGreen.copy(alpha = 0.3f),
                        RoundedCornerShape(16.dp)
                    )
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Barras
                    BatteryBar(heightFraction = 1f, isActive = true)
                    BatteryBar(heightFraction = 1f, isActive = true)
                    BatteryBar(heightFraction = 1f, isActive = true)
                    BatteryBar(heightFraction = 1f, isActive = false) // La vacía
                }
            }
        }
    }
}

@Composable
fun RowScope.BatteryBar(heightFraction: Float, isActive: Boolean) {
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight(heightFraction)
            .clip(RoundedCornerShape(4.dp))
            .background(if (isActive) Color(0xFF2BD136) else Color(0xFFA5D6A7).copy(alpha = 0.4f))
    )
}

@Composable
fun LockedStatusCard(enabled: Boolean = true) {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_locked),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Bloqueado", fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
            // Indicador verde
            Box(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .size(width = 6.dp, height = 16.dp)
                    .clip(RoundedCornerShape(50))
                    .background(
                        if (enabled) {
                            AccentGreen
                        } else {
                            Color.LightGray
                        }
                    )
            )
        }
    }
}

@Composable
fun EnergyUsageCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp) // Resto del espacio
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_use_energy),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text("Uso de energia", fontWeight = FontWeight.Bold, fontSize = 13.sp)
            }

            Row(verticalAlignment = Alignment.Bottom) {
                Text("5,343", fontSize = 32.sp, fontWeight = FontWeight.Medium)
                Text(
                    " km",
                    fontSize = 14.sp,
                    color = TextGray,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
            }

            // Pill de tendencia
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = PillGreen,
                    shape = RoundedCornerShape(50),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        PillTextGreen.copy(alpha = 0.3f)
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_down_left_arrow), // Icono flecha o rayo
                            contentDescription = null,
                            tint = PillTextGreen,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            "-32KW",
                            color = PillTextGreen,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text("vs ayer", fontSize = 11.sp, color = TextGray)
            }
        }
    }
}

@Composable
fun FanControlCard(enabled: Boolean = true) {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.size(80.dp).padding(top = 20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_fan), // Simulando ventilador
                contentDescription = "Fan",
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Pequeña barrita/switch abajo
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(50))
                    .background(
                        if (enabled) {
                            AccentGreen
                        } else {
                            Color.LightGray
                        }
                    )
            )
        }
    }
}