package com.example.myfavoritefilms.presentation.screen.details


import android.util.Log
import com.example.myfavoritefilms.R
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import com.example.myfavoritefilms.domain.model.Movie
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.myfavoritefilms.ui.theme.ratingStarsColor
import com.example.myfavoritefilms.ui.theme.textColor
import com.example.myfavoritefilms.util.Constants.BASE_URL


@ExperimentalCoilApi
@Composable
fun DetailsContent(
    movie: Movie?
) {


    movie?.let {
        val painter = rememberImagePainter(data = "$BASE_URL${it.image}") {

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            Surface(
                elevation = 10.dp,
                shape = RoundedCornerShape(
                    bottomEnd = 20.dp,
                    bottomStart = 20.dp,
                    topStart = 20.dp,
                    topEnd = 20.dp
                )
            ) {
                Image(
                    painter = painter,
                    contentDescription = "Movie image",
                    contentScale = ContentScale.Crop
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = it.title,
                    color = MaterialTheme.colors.textColor,
                    fontWeight = Bold,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    textAlign = TextAlign.Start
                )


            }


            Spacer(modifier = Modifier.height(12.dp))

            RatingWidget(
                rating = it.rating,
                modifier = Modifier.align(End)
            )

            Text(
                text = it.plot,
                color = MaterialTheme.colors.textColor,
                fontSize = MaterialTheme.typography.body2.fontSize,
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .align(Start)
            )
        }
    }


}

@Composable
fun RatingWidget(
    modifier: Modifier = Modifier,
    rating: Double,
    scaleFactor: Float = 3f,
    spaceBetween: Dp = 4.dp
) {
    val result = calculateStars(rating = rating)

    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember { starPath.getBounds() }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        result["filledStars"]?.let {
            repeat(it) {
                FilledStar(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor,
                    color = MaterialTheme.colors.ratingStarsColor
                )
            }
        }
        result["halfFilledStars"]?.let {
            repeat(it) {
                HalfFilledStar(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor,
                    color = MaterialTheme.colors.ratingStarsColor
                )
            }
        }
        result["emptyStars"]?.let {
            repeat(it) {
                EmptyStar(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }
    }
}

@Composable
fun FilledStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float,
    color:Color
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = size
        scale(scale = scaleFactor) {
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height
            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left = left, top = top) {
                drawPath(path = starPath, color = color)
            }
        }
    }
}

@Composable
fun HalfFilledStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float,
    color:Color
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = size
        scale(scale = scaleFactor) {
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height
            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left = left, top = top) {
                drawPath(path = starPath, color = Color.LightGray.copy(alpha = 0.5f))
                clipPath(path = starPath) {
                    drawRect(
                        color = color,
                        size = Size(
                            width = starPathBounds.maxDimension / 1.7f,
                            height = starPathBounds.maxDimension * scaleFactor
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = size
        scale(scale = scaleFactor) {
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height
            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left = left, top = top) {
                drawPath(path = starPath, color = Color.LightGray.copy(alpha = 0.5f))
            }
        }
    }
}

@Composable
fun calculateStars(rating: Double): Map<String, Int> {
    val maxStars by remember { mutableStateOf(5) }
    var filledStars by remember { mutableStateOf(0) }
    var halfFilledStars by remember { mutableStateOf(0) }
    var emptyStars by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = rating) {
        val (firstNumber, lastNumber) = rating.toString()
            .split(".")
            .map { it.toInt() }

        if (firstNumber in 0..5 && lastNumber in 0..9) {
            filledStars = firstNumber
            if (lastNumber in 1..5) halfFilledStars++
            if (lastNumber in 6..9) filledStars++
            if (firstNumber == 5 && lastNumber > 0) {
                emptyStars = 5
                filledStars = 0
                halfFilledStars = 0
            }
        } else Log.d("RatingWidget", "Invalid rating number.")
    }
    emptyStars = maxStars - (filledStars + halfFilledStars)
    return mapOf(
        "filledStars" to filledStars,
        "halfFilledStars" to halfFilledStars,
        "emptyStars" to emptyStars
    )
}
