package screen.country.data

import network.CountriesAPI

class CountryRepository(private val countriesAPI: CountriesAPI) {

    suspend fun countryQuery(code: String) = countriesAPI.countryQuery(code)
}