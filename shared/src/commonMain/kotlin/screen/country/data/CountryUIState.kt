package screen.country.data

import dev.imn.earth.CountryQuery
import util.UIErrorType


enum class CurrentBottomSheetContent {
    STATES, LANGUAGES
}

data class CountryUIState(
    val isLoading: Boolean = true,
    val error: UIErrorType = UIErrorType.Nothing,

    val countryCode: String? = null,
    val data: CountryQuery.Country? = null, // Cuz, i'm a lazy *****
    val currentBottomSheetContent: CurrentBottomSheetContent = CurrentBottomSheetContent.STATES
)
