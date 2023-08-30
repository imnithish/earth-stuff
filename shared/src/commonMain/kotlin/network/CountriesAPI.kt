package network

import com.apollographql.apollo3.ApolloCall
import dev.imn.earth.ContinentsQuery

interface CountriesAPI {

    suspend fun continentsQuery(): ApolloCall<ContinentsQuery.Data>

}