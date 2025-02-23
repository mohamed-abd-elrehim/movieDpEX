package banquemisr.presentation.screen.details_screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banquemisr.components.shared_components.AppAlertDialog
import banquemisr.components.shared_components.AppHorizontalDivider
import banquemisr.components.shared_components.AppIconButton
import banquemisr.components.shared_components.AppText
import banquemisr.components.shared_components.CircularIndeterminateProgressBar
import banquemisr.components.shared_components.Gap
import banquemisr.components.shared_components.LoadAsyncImage
import banquemisr.components.shared_components.PullToRefreshBox
import banquemisr.core.domain.ProgressBarState
import banquemisr.core.domain.UIComponent
import banquemisr.presentation.R
import banquemisr.presentation.screen.details_screen.components.MovieChipList
import banquemisr.presentation.screen.details_screen.components.MovieDetailsRow
import banquemisr.presentation.screen.details_screen.components.MovieDetailsSecondRow
import banquemisr.presentation.screen.details_screen.components.MovieHeader
import banquemisr.presentation.screen.details_screen.components.MovieProductionCompaniesChipList
import banquemisr.presentation.screen.details_screen.components.SecondTitle
import banquemisr.presentation.screen.list_screen.ui.ListScreenIntent
import banquemisr.presentation.ui.theme.PrimaryColor
import banquemisr.presentation.ui.theme.SecondaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(viewModel: DetailsScreenViewModel) {
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current
    PullToRefreshBox(
        isRefreshing = state.value.isRefreshing,
        onRefresh = { viewModel.onIntent(DetailsScreenIntent.RefreshMovieDetails) },
        content = {
            state.value.let { movie ->
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Movie Image
                    movie.movieDetails.let { movieDetails ->
                        movie.imageLoader?.let {
                            LoadAsyncImage(
                                imageUrl = movieDetails?.backdropPath ?: "",
                                imageTitle = movieDetails?.title ?: "",
                                modifier = Modifier.fillMaxSize(),
                                imageLoader = it,
                                context = context,
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, PrimaryColor, PrimaryColor),
                                    startY = 0f,
                                    endY = 3000f
                                )
                            )

                    ) {

                        //Back Button
                        AppIconButton(
                            icon = Icons.AutoMirrored.Filled.ArrowBack,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(5.dp),
                            iconTint = PrimaryColor,
                            iconModifier = Modifier
                                .background(
                                    SecondaryColor.copy(alpha = 0.7f),
                                    RoundedCornerShape(15.dp)
                                )
                                .size(50.dp)
                                .padding(5.dp),
                            onClick = { viewModel.onIntent(DetailsScreenIntent.BackButtonClicked) }
                        )

                        Box(
                            modifier = Modifier
                                .padding(top = 400.dp, bottom = 10.dp)
                        ) {


                            //page Content
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(rememberScrollState())
                                    .align(Alignment.Center) // Start from the center of the screen
                                    .padding(horizontal = 16.dp),
                                verticalArrangement = Arrangement.Center, // Content expands downward from the center
                                horizontalAlignment = Alignment.Start
                            ) {

                                movie.movieDetails.let { movieDetails ->


                                    if (movieDetails != null) {


                                        //Header title  - tagline
                                        MovieHeader(movieDetails)

                                        Gap(height = 8)
                                        //List of genres
                                        MovieChipList(movieDetails.genres)

                                        AppHorizontalDivider()

                                        Gap(height = 8)
                                        // voteCount  - popularity  -runtime
                                        MovieDetailsRow(movieDetails)

                                        AppHorizontalDivider()

                                        Gap(height = 8)
                                        //voteAverage - budget -releaseDate
                                        MovieDetailsSecondRow(movieDetails)

                                        AppHorizontalDivider()

                                        Gap(height = 8)
                                        //Overview
                                        SecondTitle(R.string.overview)
                                        AppText(
                                            text = (movieDetails.overview ?: ""),
                                            color = Color.White.copy(alpha = 0.8f),
                                            fontSize = 15.sp,
                                            textAlign = TextAlign.Start,
                                            modifier = Modifier.padding(bottom = 8.dp)

                                        )

                                        AppHorizontalDivider()
                                        Gap(height = 8)
                                        //production countries
                                        SecondTitle(R.string.production_countries)
                                        MovieChipList(movieDetails.productionCountries)

                                        AppHorizontalDivider()

                                        Gap(height = 8)
                                        //production companies
                                        SecondTitle(R.string.production_companies)
                                        state.value.imageLoader?.let {
                                            MovieProductionCompaniesChipList(
                                                movieDetails.productionCompanies,
                                                it,
                                                context
                                            )
                                        }


                                    }
                                }
                            }

                        }


                    }

                }
                if (state.value.errorQueue.isNotEmpty()) {

                    state.value.errorQueue.peek()?.let { uiComponent ->
                        if (uiComponent is UIComponent.Dialog) {
                            AppAlertDialog (
                                showDialog = true,
                                title = uiComponent.title,
                                description = uiComponent.description,
                                onRemoveHeadFromQueue = {
                                    viewModel.onIntent(DetailsScreenIntent.RemoveHeadMessageFromQueue)
                                },
                            )

                        }

                    }


                }
                if (state.value.progressBarState is ProgressBarState.Loading) {
                    CircularIndeterminateProgressBar()
                }
            }
        }
    )

}


