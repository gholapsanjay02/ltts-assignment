package api


class RetrofitAPI(val api:API) {
    suspend fun getRepositoryResult() = api.getData();
}

