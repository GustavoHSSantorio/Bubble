package gustavo.santorio.mvvmarquithecture.remote

import gustavo.santorio.mvvmarquithecture.model.ChatVO
import gustavo.santorio.mvvmarquithecture.model.MessageVO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("/bot")
    fun getMessage(@Query("mensagem") mensage : String) : Call<ChatVO>
}