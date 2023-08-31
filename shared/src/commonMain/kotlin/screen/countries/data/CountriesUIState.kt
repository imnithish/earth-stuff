package screen.countries.data

import dev.imn.earth.ContinentQuery
import util.UIErrorType

data class CountriesUIState(
    val isLoading: Boolean = true,
    val error: UIErrorType = UIErrorType.Nothing,

    val continentId: String? = null,
    val countries: List<ContinentQuery.Country> = listOf(),
    val continentCodeAndName: Pair<String, String>? = null
)
