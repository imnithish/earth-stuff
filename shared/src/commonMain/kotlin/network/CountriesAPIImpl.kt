package network

import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.ApolloClient
import dev.imn.earth.ContinentsQuery

class CountriesAPIImpl(private val apolloClient: ApolloClient) : CountriesAPI {
    override suspend fun continentsQuery(): ApolloCall<ContinentsQuery.Data> {
        return apolloClient.query(ContinentsQuery())
    }
}