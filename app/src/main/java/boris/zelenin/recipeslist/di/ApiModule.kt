package boris.zelenin.recipeslist.di

import boris.zelenin.recipeslist.BuildConfig
import boris.zelenin.recipeslist.data.api.RecipeApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object ApiModule {
    @Singleton
    @JvmStatic
    @Provides
    fun provideAuthInterceptor() = Interceptor { chain ->
        val newUrl = chain.request().url()
                .newBuilder()
                .addQueryParameter("key", BuildConfig.FOOD_2_FORK_API_KEY)
                .build()

        val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

        chain.proceed(newRequest)
    }

    @Singleton
    @JvmStatic
    @Provides
    fun provideFood2ForkClient(authInterceptor: Interceptor) =
            OkHttpClient().newBuilder()
                    .addInterceptor(authInterceptor)
                    .build()

    @Singleton
    @JvmStatic
    @Provides
    fun provideRecipeApi(food2forkClient: OkHttpClient): RecipeApi =
            Retrofit.Builder()
                    .client(food2forkClient)
                    .baseUrl("https://www.food2fork.com/api/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                    .create(RecipeApi::class.java)
}