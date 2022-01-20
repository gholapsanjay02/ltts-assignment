package api

import emp.EmployeeDetails
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface API {
    @GET("users")
    suspend fun getData() : Response<EmployeeDetails>

    companion object
    {
        operator fun invoke() : API
        {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()
                .create(API::class.java)
        }
    }
}



