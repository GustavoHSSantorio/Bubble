package gustavo.santorio.mvvmarquithecture.model

class MessageVO {
    var message : String? = null
    var messageType : Int = 0
    var profileType : Int = 0
    var servicesVO : MutableList<ServiceVO>? = null
}