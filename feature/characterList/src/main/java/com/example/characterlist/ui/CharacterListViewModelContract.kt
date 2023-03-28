package com.example.characterlist.ui

import androidx.lifecycle.LiveData
import com.example.core_model.CharacterModel

class CharacterListViewModelContract {

    interface ViewModel {
        val uiStateData: LiveData<State>
        fun invokeAction(action: Action)
        val uiEventData: LiveData<Event>
    }

    sealed class Action {
        data class NavigateToCharacterDetails(val character: CharacterModel) : Action()
        data class FetchCharactersByPage(val lastItem: Int) : Action()

        object InitPage : Action()
    }

    sealed class State {
        object LoadingState : State()
        data class SuccessState(val characterList: List<CharacterModel>) : State()
        data class ErrorState(val message: String) : State()
    }

    sealed class Event {
        data class NavigateToCharacterDetailsView(val character: CharacterModel) : Event()
    }
}