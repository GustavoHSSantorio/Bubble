package gustavo.santorio.mvvmarquithecture.repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import gustavo.santorio.mvvmarquithecture.model.ChatVO
import gustavo.santorio.mvvmarquithecture.model.MessageVO
import gustavo.santorio.mvvmarquithecture.model.ServiceVO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BubbleRepository : BaseRepository() {

    fun getMessage(message : String, liveData: MutableLiveData<ChatVO>){
        apiService.getMessage(message).enqueue(object : Callback<ChatVO>{
            override fun onFailure(call: Call<ChatVO>, t: Throwable) {
                Log.e("", "")
            }

            override fun onResponse(call: Call<ChatVO>, response: Response<ChatVO>) {
                liveData.value = response.body()
            }

        })
    }

    fun getMessageEstimate(profileType : Int, profileSuggestedType : Int) : MutableList<MessageVO>{
        val messagesVO = arrayListOf<MessageVO>()
        when(profileType){
            1 -> {
                val messageVO = MessageVO()
                messageVO.messageType = 1
                messageVO.profileType = 1
                messageVO.servicesVO = arrayListOf()
                val serviceVO = ServiceVO()
                serviceVO.name = "Yellow"
                serviceVO.time = 10.0
                serviceVO.value = 30.0
                serviceVO.tipe = 1
                messageVO.servicesVO!!.add(serviceVO)
                val serviceVO2 = ServiceVO()
                serviceVO2.name = "Grin"
                serviceVO2.time = 6.0
                serviceVO2.value = 40.0
                serviceVO2.tipe = 2
                messageVO.servicesVO!!.add(serviceVO2)
                messagesVO.add(messageVO)
            }
            2 -> {
                val messageVO2 = MessageVO()
                messageVO2.messageType = 1
                messageVO2.profileType = 2
                messageVO2.servicesVO = arrayListOf()
                val serviceVO3 = ServiceVO()
                serviceVO3.name = "Uber"
                serviceVO3.time = 15.0
                serviceVO3.value = 50.0
                serviceVO3.tipe = 3
                messageVO2.servicesVO!!.add(serviceVO3)
                val serviceVO4 = ServiceVO()
                serviceVO4.name = "99"
                serviceVO4.time = 15.0
                serviceVO4.value = 60.0
                serviceVO4.tipe = 4
                messageVO2.servicesVO!!.add(serviceVO4)
                val serviceVO5 = ServiceVO()
                serviceVO5.name = "4move"
                serviceVO5.time = 15.0
                serviceVO5.value = 60.0
                serviceVO5.tipe = 5
                messageVO2.servicesVO!!.add(serviceVO5)
                messagesVO.add(messageVO2)
            }
            3 -> {
                val messageVO3 = MessageVO()
                messageVO3.messageType = 1
                messageVO3.profileType = 3
                messageVO3.servicesVO = arrayListOf()
                val serviceVO6 = ServiceVO()
                serviceVO6.name = "Moovit"
                serviceVO6.time = 20.0
                serviceVO6.value = 4.80
                serviceVO6.tipe = 9
                messageVO3.servicesVO!!.add(serviceVO6)
                val serviceVO4 = ServiceVO()
                serviceVO4.name = "Cade o Onibus"
                serviceVO4.time = 20.0
                serviceVO4.value = 4.80
                serviceVO4.tipe = 8
                messageVO3.servicesVO!!.add(serviceVO4)
                val serviceVO5 = ServiceVO()
                serviceVO5.name = "Coletivo"
                serviceVO5.time = 15.0
                serviceVO5.value = 4.50
                serviceVO5.tipe = 10
                messageVO3.servicesVO!!.add(serviceVO4)
                messagesVO.add(messageVO3)
            }
        }


        when(profileSuggestedType){
            1 -> {
                val messageVO = MessageVO()
                messageVO.messageType = 1
                messageVO.profileType = 1
                messageVO.servicesVO = arrayListOf()
                val serviceVO = ServiceVO()
                serviceVO.name = "Yellow"
                serviceVO.time = 10.0
                serviceVO.value = 30.0
                serviceVO.tipe = 1
                messageVO.servicesVO!!.add(serviceVO)
                val serviceVO2 = ServiceVO()
                serviceVO2.name = "Grin"
                serviceVO2.time = 6.0
                serviceVO2.value = 40.0
                serviceVO2.tipe = 2
                messageVO.servicesVO!!.add(serviceVO2)
                messagesVO.add(messageVO)
            }
            2 -> {
                val messageVO2 = MessageVO()
                messageVO2.messageType = 1
                messageVO2.profileType = 2
                messageVO2.servicesVO = arrayListOf()
                val serviceVO3 = ServiceVO()
                serviceVO3.name = "Uber"
                serviceVO3.time = 15.0
                serviceVO3.value = 50.0
                serviceVO3.tipe = 3
                messageVO2.servicesVO!!.add(serviceVO3)
                val serviceVO4 = ServiceVO()
                serviceVO4.name = "99"
                serviceVO4.time = 15.0
                serviceVO4.value = 60.0
                serviceVO4.tipe = 4
                messageVO2.servicesVO!!.add(serviceVO4)
                val serviceVO5 = ServiceVO()
                serviceVO5.name = "4move"
                serviceVO5.time = 15.0
                serviceVO5.value = 60.0
                serviceVO5.tipe = 5
                messageVO2.servicesVO!!.add(serviceVO5)
                messagesVO.add(messageVO2)
            }
            3 -> {
                val messageVO3 = MessageVO()
                messageVO3.messageType = 1
                messageVO3.profileType = 3
                messageVO3.servicesVO = arrayListOf()
                val serviceVO6 = ServiceVO()
                serviceVO6.name = "Moovit"
                serviceVO6.time = 20.0
                serviceVO6.value = 4.80
                serviceVO6.tipe = 9
                messageVO3.servicesVO!!.add(serviceVO6)
                val serviceVO4 = ServiceVO()
                serviceVO4.name = "Cade o Onibus"
                serviceVO4.time = 20.0
                serviceVO4.value = 4.80
                serviceVO4.tipe = 8
                messageVO3.servicesVO!!.add(serviceVO4)
                val serviceVO5 = ServiceVO()
                serviceVO5.name = "Coletivo"
                serviceVO5.time = 15.0
                serviceVO5.value = 4.50
                serviceVO5.tipe = 10
                messageVO3.servicesVO!!.add(serviceVO4)
                messagesVO.add(messageVO3)
            }
        }

        when(profileType + profileSuggestedType){
            5 -> {
                val messageVO = MessageVO()
                messageVO.messageType = 1
                messageVO.profileType = 1
                messageVO.servicesVO = arrayListOf()
                val serviceVO = ServiceVO()
                serviceVO.name = "Yellow"
                serviceVO.time = 10.0
                serviceVO.value = 30.0
                serviceVO.tipe = 1
                messageVO.servicesVO!!.add(serviceVO)
                val serviceVO2 = ServiceVO()
                serviceVO2.name = "Grin"
                serviceVO2.time = 6.0
                serviceVO2.value = 40.0
                serviceVO2.tipe = 2
                messageVO.servicesVO!!.add(serviceVO2)
                messagesVO.add(messageVO)
            }
            4 -> {
                val messageVO2 = MessageVO()
                messageVO2.messageType = 1
                messageVO2.profileType = 2
                messageVO2.servicesVO = arrayListOf()
                val serviceVO3 = ServiceVO()
                serviceVO3.name = "Uber"
                serviceVO3.time = 15.0
                serviceVO3.value = 50.0
                serviceVO3.tipe = 3
                messageVO2.servicesVO!!.add(serviceVO3)
                val serviceVO4 = ServiceVO()
                serviceVO4.name = "99"
                serviceVO4.time = 15.0
                serviceVO4.value = 60.0
                serviceVO4.tipe = 4
                messageVO2.servicesVO!!.add(serviceVO4)
                val serviceVO5 = ServiceVO()
                serviceVO5.name = "4move"
                serviceVO5.time = 15.0
                serviceVO5.value = 60.0
                serviceVO5.tipe = 5
                messageVO2.servicesVO!!.add(serviceVO5)
                messagesVO.add(messageVO2)
            }
            3 -> {
                val messageVO3 = MessageVO()
                messageVO3.messageType = 1
                messageVO3.profileType = 3
                messageVO3.servicesVO = arrayListOf()
                val serviceVO6 = ServiceVO()
                serviceVO6.name = "Moovit"
                serviceVO6.time = 20.0
                serviceVO6.value = 4.80
                serviceVO6.tipe = 9
                messageVO3.servicesVO!!.add(serviceVO6)
                val serviceVO4 = ServiceVO()
                serviceVO4.name = "Cade o Onibus"
                serviceVO4.time = 20.0
                serviceVO4.value = 4.80
                serviceVO4.tipe = 8
                messageVO3.servicesVO!!.add(serviceVO4)
                val serviceVO5 = ServiceVO()
                serviceVO5.name = "Coletivo"
                serviceVO5.time = 15.0
                serviceVO5.value = 4.50
                serviceVO5.tipe = 10
                messageVO3.servicesVO!!.add(serviceVO4)
                messagesVO.add(messageVO3)
            }
        }

        return messagesVO
    }
}