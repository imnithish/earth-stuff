package screen.continents.data

import dev.imn.earth.ContinentsQuery
import util.UIErrorType

data class ContinentsUIState(
    val isLoading: Boolean = true,
    val error: UIErrorType = UIErrorType.Nothing,
    val continents: List<ContinentsQuery.Continent> = listOf()
)
