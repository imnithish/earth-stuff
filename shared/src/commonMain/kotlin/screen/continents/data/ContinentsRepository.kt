package screen.continents.data

import network.CountriesAPI

class ContinentsRepository(private val countriesAPI: CountriesAPI) {
    suspend fun continentsQuery() = countriesAPI.continentsQuery()
}