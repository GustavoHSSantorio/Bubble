package gustavo.santorio.mvvmarquithecture.repository

import android.arch.lifecycle.MutableLiveData
import gustavo.santorio.mvvmarquithecture.remote.APIService
import gustavo.santorio.mvvmarquithecture.repository.di.DaggerRepositoryComponent
import gustavo.santorio.mvvmarquithecture.repository.di.RepositoryComponent
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

open class BaseRepository {

    var logoutMutableLiveData : MutableLiveData<String>? = null

    val component : RepositoryComponent by lazy {
        DaggerRepositoryComponent.builder().retrofitClass(APIService::class).build()
    }

    @field:[Inject Named("apiService")]
    lateinit var apiService : APIService

    var errorLiveData : MutableLiveData<String>? = null

    init {
        component.inject(this)
    }


    protected fun validateResponse(response: Response<*>?) : Boolean{
        if(response == null) {
            errorLiveData!!.value = "Tivemos um probleminha. Verifique sua internet e tente de novo!"
            return false
        }

//        val newResponse = response as Response<BaseSO>
//
//        if (newResponse != null && response.isSuccessful && response.body() != null && response.body()!!.success)
//            return true
//        else if(newResponse.body() != null && newResponse.body()!!.messages != null && newResponse.body()!!.messages != null && !newResponse.body()!!.messages!!.isEmpty())
//            errorLiveData!!.value = newResponse.body()!!.messages!!.get(0)
//        else
//            errorLiveData!!.value = "Tivemos um probleminha. Verifique sua internet e tente de novo!"

        return false
    }
}