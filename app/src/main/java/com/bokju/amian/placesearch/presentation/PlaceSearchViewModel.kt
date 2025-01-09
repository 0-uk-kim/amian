package com.bokju.amian.placesearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bokju.amian.placesearch.domain.PlaceSearchRepository
import com.bokju.amian.utils.model.onFailure
import com.bokju.amian.utils.model.onSuccess
import com.bokju.amian.utils.model.toUiText
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlaceSearchViewModel(
    private val placeSearchRepository: PlaceSearchRepository
) : ViewModel() {

    private var searchJob: Job? = null

    private val _state = MutableStateFlow(PlaceSearchState())
    val state = _state
        .onStart {
            observeSearchQuery()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = _state.value
        )

    fun onAction(action: PlaceSearchAction) {
        when (action) {
            is PlaceSearchAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> showRecentQueries()
                    else -> searchPlaces(query)
                }
            }
            .launchIn(viewModelScope)
    }

    private fun showRecentQueries() {
        _state.update {
            it.copy(
                isLoading = false,
                searchResults = emptyList(),
                errorMessage = null,
            )
        }
    }

    private fun searchPlaces(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            placeSearchRepository
                .searchPlaces(query)
                .onSuccess { searchResults ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            searchResults = searchResults,
                            errorMessage = null,
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            searchResults = emptyList(),
                            errorMessage = error.toUiText()
                        )
                    }
                }
        }
    }
}