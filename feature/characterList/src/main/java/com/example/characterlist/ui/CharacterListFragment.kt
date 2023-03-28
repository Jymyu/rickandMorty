package com.example.characterlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.characterlist.adapter.CharactersListAdapter
import com.example.characterlist.databinding.FragmentCharactersListBinding
import com.example.core_model.CharacterModel
import com.android.navigation.CharacterHandler
import com.android.navigation.NavigateToFlow
import com.android.navigation.NavigationFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment(), CharacterHandler {

    private val viewModel: CharacterListViewModel by viewModels()
    private var scrollListener: RecyclerView.OnScrollListener? = null

    private lateinit var binding: FragmentCharactersListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiStateData.observe(viewLifecycleOwner, Observer(::performViewState))
        viewModel.uiEventData.observe(viewLifecycleOwner, Observer(::performViewEvent))
        viewModel.invokeAction(CharacterListViewModelContract.Action.InitPage)

        configureRecyclerView()
    }

    private fun performViewState(state: CharacterListViewModelContract.State) {

        when (state) {
            is CharacterListViewModelContract.State.SuccessState -> {
                binding.pbCharacterList.isVisible = false
                (binding.rvCharacterList.adapter as? CharactersListAdapter?)?.submitList(emptyList())
                (binding.rvCharacterList.adapter as? CharactersListAdapter?)?.submitList(state.characterList)
            }
            is CharacterListViewModelContract.State.ErrorState -> {
                binding.pbCharacterList.isVisible = false
                Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
            }

            CharacterListViewModelContract.State.LoadingState -> binding.pbCharacterList.isVisible =
                true
        }
    }

    private fun performViewEvent(event: CharacterListViewModelContract.Event) {
        when (event) {
            is CharacterListViewModelContract.Event.NavigateToCharacterDetailsView -> (activity as NavigateToFlow).navigateToFlow(
                NavigationFlow.RickAndMortyListToCharacterDetailsFlow(event.character)
            )
        }
    }


    override fun onCharacterClick(characterModel: CharacterModel) {
        viewModel.invokeAction(
            CharacterListViewModelContract.Action.NavigateToCharacterDetails(
                characterModel
            )
        )
    }

    private fun configureRecyclerView() {
        if (binding.rvCharacterList.adapter == null)
            binding.rvCharacterList.adapter = CharactersListAdapter(this)

        if (binding.rvCharacterList.layoutManager == null)
            binding.rvCharacterList.layoutManager = GridLayoutManager(requireContext(), 2)

        configureScrollListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        scrollListener = null
    }

    private fun configureScrollListener() {
        if (scrollListener == null) {
            scrollListener = object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1))
                        viewModel.invokeAction(
                            CharacterListViewModelContract.Action.FetchCharactersByPage(
                                (binding.rvCharacterList.layoutManager as GridLayoutManager).findLastCompletelyVisibleItemPosition()
                            )
                        )
                }
            }
            binding.rvCharacterList.addOnScrollListener(scrollListener as RecyclerView.OnScrollListener)
        }
    }
}