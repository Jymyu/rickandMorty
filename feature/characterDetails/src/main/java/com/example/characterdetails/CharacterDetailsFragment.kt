package com.example.characterdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.DialogFragment
import com.android.navigation.NavigationArgs.RICK_AND_MORTY_CHARACTER
import com.example.characterdetails.databinding.FragmentCharacterDetailsBinding
import com.example.characterdetails.theme.DetailsTheme
import com.example.core_model.CharacterModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class CharacterDetailsFragment : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val character: CharacterModel? =
            arguments?.get(RICK_AND_MORTY_CHARACTER) as? CharacterModel?


        return ComposeView(requireContext()).apply {
            setContent {
                DetailsTheme {
                    Surface(color = Color.Transparent) {
                        DetailsScreen(character = character)
                    }
                }

            }
        }


    }

    @Composable
    fun DetailsScreen(
        modifier: Modifier = Modifier,
        character: CharacterModel? = CharacterModel()
    ) {
        Card(
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(id = R.string.character_details).uppercase(Locale.getDefault()),
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                )
                character?.name?.let {
                    Text(
                        modifier = Modifier
                            .padding(start = 16.dp, bottom = 8.dp),
                        text = stringResource(id = R.string.character_name).plus(it)
                    )
                }
                character?.species?.let {
                    Text(
                        modifier = Modifier
                            .padding(start = 16.dp, bottom = 8.dp),

                        text = stringResource(id = R.string.species).plus(it)
                    )
                }

                character?.type?.let {
                    Text(
                        modifier = Modifier
                            .padding(start = 16.dp, bottom = 8.dp),

                        text = stringResource(id = R.string.type).plus(it)
                    )
                }

                character?.status?.let {
                    Text(
                        modifier = Modifier
                            .padding(start = 16.dp, bottom = 8.dp),

                        text = stringResource(id = R.string.status).plus(it)
                    )
                }
            }
        }


    }

    @Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
    @Composable
    fun SearchBarPreview() {
        DetailsScreen(character = CharacterModel(1, "ssss", "ssss", "ssss", "ssss"))
    }
}


