package com.nedashkivskyi.numbers_fact.ui.screen.main_screen.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nedashkivskyi.numbers_fact.data.model.local.NumbersDataEntity
import com.nedashkivskyi.numbers_fact.data.repository.MainDataSourceRepository
import com.nedashkivskyi.numbers_fact.utils.DispatcherProvider
import com.nedashkivskyi.numbers_fact.utils.EventState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: MainDataSourceRepository,
    private val dispatchers: DispatcherProvider
): ViewModel() {

    val state: MutableState<EventState<List<NumbersDataEntity>>> = mutableStateOf(EventState.Empty())

    fun getNumberData(number: String) {
        viewModelScope.launch(dispatchers.io()){
            state.value = EventState.Loading(isLoading = true)
            when(val event = repository.fetchNumberData(number = number)){
                is EventState.Success ->
                    state.value = event
                is EventState.Error ->
                    state.value = event
            }
        }
    }

    fun getRandomNumberData() {
        viewModelScope.launch(dispatchers.io()){
            state.value = EventState.Loading(isLoading = true)
            when(val event = repository.fetchRandomNumberData()){
                is EventState.Success ->
                    state.value = event
                is EventState.Error ->
                    state.value = event
            }
        }
    }

    fun getAllNumbers(){
        viewModelScope.launch(dispatchers.io()) {
            state.value = EventState.Loading(isLoading = true)

            when(val event = repository.fetchAllNumberHistory()){
                is EventState.Success ->
                    state.value = event
                is EventState.Empty ->
                    state.value = event
            }
        }
    }
}