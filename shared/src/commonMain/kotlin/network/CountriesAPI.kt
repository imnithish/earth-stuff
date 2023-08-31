package network

import com.apollographql.apollo3.ApolloCall
import dev.imn.earth.ContinentQuery
import dev.imn.earth.ContinentsQuery

interface CountriesAPI {

    suspend fun continentsQuery(): ApolloCall<ContinentsQuery.Data>

    suspend fun continentQuery(id: String): ApolloCall<ContinentQuery.Data>

}