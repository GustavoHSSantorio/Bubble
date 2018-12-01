package gustavo.santorio.mvvmarquithecture.exception

class ViewModelNameNotProvidedException(var viewName : String) : Exception("The $viewName doesn't provide a ViewModel name. To use 4Move BaseView is necessary to provide it.")