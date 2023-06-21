package info.fekri.composeboom.ui.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = CutCornerShape(topStart = 4.dp, topEnd = 2.dp, bottomEnd = 2.dp, bottomStart = 2.dp),
    medium = CutCornerShape(topStart = 14.dp, topEnd = 4.dp, bottomEnd = 4.dp, bottomStart = 4.dp),
    large = CutCornerShape(topStart = 14.dp, topEnd = 4.dp, bottomEnd = 4.dp, bottomStart = 4.dp)
)