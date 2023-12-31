package di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import network.CountriesAPI
import network.CountriesAPIImpl
import org.koin.core.context.startKoin
import org.koin.dsl.module
import screen.continents.ContinentsViewModel
import screen.continents.data.ContinentsRepository
import screen.countries.CountriesViewModel
import screen.countries.data.CountriesRepository
import screen.country.CountryViewModel
import screen.country.data.CountryRepository

fun initKoin() {
    startKoin {
        modules(
            module {
                single {
                    ApolloClient.Builder()
                        .serverUrl("https://countries.trevorblades.com/graphql")
                        .normalizedCache(MemoryCacheFactory(maxSizeBytes = 10 * 1024 * 1024))
                        .build()
                }
                single<CountriesAPI> { CountriesAPIImpl(get()) }
                single { ContinentsRepository(get()) }
                single { CountriesRepository(get()) }
                single { CountryRepository(get()) }
                factory { ContinentsViewModel(get()) }
                factory { CountriesViewModel(get()) }
                factory { CountryViewModel(get()) }
            }
        )
    }
}