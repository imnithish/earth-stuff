package screen.country

import com.apollographql.apollo3.exception.ApolloException
import com.apollographql.apollo3.exception.ApolloNetworkException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import screen.country.data.CountryRepository
import screen.country.data.CountryUIState
import screen.country.data.CurrentBottomSheetContent
import util.UIErrorType

class CountryViewModel(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CountryUIState())
    val uiState = _uiState.asStateFlow()

    fun setup(code: String?) = viewModelScope.launch {
        _uiState.update { state ->
            state.copy(
                countryCode = code
            )
        }
        attemptCountryQuery()
    }

    fun setCurrentBottomSheet(currentBottomSheetContent: CurrentBottomSheetContent) =
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    currentBottomSheetContent = currentBottomSheetContent
                )
            }
        }

    fun attemptCountryQuery() = viewModelScope.launch {
        _uiState.value.countryCode?.let { countryCode ->
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = UIErrorType.Nothing
                )
            }
            countryRepository.countryQuery(countryCode).toFlow()
                .catch { exceptions ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            error = if ((exceptions as ApolloException).suppressedExceptions.map { it as ApolloException }
                                    .any { it is ApolloNetworkException })
                                UIErrorType.Network else UIErrorType.Other()
                        )
                    }
                }.collectLatest { res ->
                    if (!res.hasErrors()) {
                        val data = res.data?.country
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                error = if (data == null) UIErrorType.Other("API returned empty stuff") else UIErrorType.Nothing,
                                data = data
                            )
                        }
                    } else {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                error = UIErrorType.Other()
                            )
                        }
                    }
                }
        } ?: kotlin.run {
            _uiState.update { state ->
                state.copy(
                    isLoading = false,
                    error = UIErrorType.Other("Country code missing")
                )
            }
        }
    }

}