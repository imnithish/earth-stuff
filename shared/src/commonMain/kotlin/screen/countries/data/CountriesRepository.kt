package screen.countries.data

import network.CountriesAPI

class CountriesRepository(private val countriesAPI: CountriesAPI) {

    suspend fun continentQuery(id: String) = countriesAPI.continentQuery(id)
}