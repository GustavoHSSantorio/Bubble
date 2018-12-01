package gustavo.santorio.mvvmarquithecture.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIUtils {

    fun getAPIService(): Retrofit {
        return Retrofit.Builder()
                    .baseUrl("http://192.168.92.5:8888")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
    }
}