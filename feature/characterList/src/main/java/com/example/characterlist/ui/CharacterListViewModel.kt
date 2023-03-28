package com.example.characterlist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_domain.FetchCharacterListUseCase
import com.example.core_model.CharacterModel
import com.example.core_model.Resource
import com.example.core_model.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val fetchCharacterListUseCase: FetchCharacterListUseCase
) : CharacterListViewModelContract.ViewModel, ViewModel() {

    private val _uiStateData = MediatorLiveData<CharacterListViewModelContract.State>()
    override val uiStateData: LiveData<CharacterListViewModelContract.State> = _uiStateData

    private val _uiEventData = SingleLiveEvent<CharacterListViewModelContract.Event>()
    override val uiEventData: LiveData<CharacterListViewModelContract.Event> = _uiEventData

    override fun invokeAction(action: CharacterListViewModelContract.Action) {
        when (action) {
            is CharacterListViewModelContract.Action.FetchCharactersByPage -> fetchCharacters(action.lastItem)
            CharacterListViewModelContract.Action.InitPage -> initPage()
            is CharacterListViewModelContract.Action.NavigateToCharacterDetails -> _uiEventData.postValue(
                CharacterListViewModelContract.Event.NavigateToCharacterDetailsView(action.character)
            )
        }
    }

    private var currentItems = 0
    private var page = 0
    private var currentList = mutableListOf<CharacterModel>()

    private fun initPage() {
        currentList = mutableListOf()
        currentItems = 0
        page = 0
        fetchCharacters()
    }

    private fun fetchCharacters(lastVisibleItem: Int = 0) {
        viewModelScope.launch(Dispatchers.IO) {
            if (lastVisibleItem >= currentItems.minus(4)) {
                fetchCharacterListUseCase.invoke(page).collect { resource ->
                    when (resource) {
                        is Resource.Error -> resource.message?.let {
                            _uiStateData.postValue(
                                CharacterListViewModelContract.State.ErrorState(
                                    it
                                )
                            )
                        }
                        is Resource.Loading -> _uiStateData.postValue(CharacterListViewModelContract.State.LoadingState)
                        is Resource.Success -> resource.data?.let { handleSuccessState(it) }
                    }
                }
            }
        }
    }

    private fun handleSuccessState(list: List<CharacterModel>) {
        currentList = (currentList + list).toMutableList()
        _uiStateData.postValue(CharacterListViewModelContract.State.SuccessState(currentList))
        page++
        currentItems = currentList.size
    }
}