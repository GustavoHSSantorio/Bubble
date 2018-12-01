package gustavo.santorio.mvvmarquithecture.repository.di

import dagger.Module
import dagger.Provides
import gustavo.santorio.mvvmarquithecture.remote.APIService
import gustavo.santorio.mvvmarquithecture.remote.APIUtils
import gustavo.santorio.mvvmarquithecture.scope.RepositoryScope
import javax.inject.Named
import kotlin.reflect.KClass


@Module
class RepositoryModule {

    @Provides
    @RepositoryScope
    @Named("apiService")
    fun providesApiService(retrofitClass : KClass<APIService>) : APIService = APIUtils().getAPIService().create(retrofitClass.java)
}
