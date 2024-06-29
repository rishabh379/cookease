package com.pvsrishabh.cookease.di

import android.app.Application
import androidx.room.Room
import com.pvsrishabh.cookease.data.local.RecipesDao
import com.pvsrishabh.cookease.data.local.RecipesDatabase
import com.pvsrishabh.cookease.data.local.RecipesTypeConvertor
import com.pvsrishabh.cookease.data.manager.LocalUserManagerImpl
import com.pvsrishabh.cookease.data.remote.RecipesApi
import com.pvsrishabh.cookease.data.repository.RecipesRepositoryImpl
import com.pvsrishabh.cookease.domain.manager.LocalUserManager
import com.pvsrishabh.cookease.domain.repository.RecipesRepository
import com.pvsrishabh.cookease.domain.usecases.app_entry.AppEntryUseCases
import com.pvsrishabh.cookease.domain.usecases.app_entry.ReadAppEntry
import com.pvsrishabh.cookease.domain.usecases.app_entry.SaveAppEntry
import com.pvsrishabh.cookease.domain.usecases.recipes.DeleteRecipe
import com.pvsrishabh.cookease.domain.usecases.recipes.GetRecipes
import com.pvsrishabh.cookease.domain.usecases.recipes.RecipesUseCases
import com.pvsrishabh.cookease.domain.usecases.recipes.SearchRecipes
import com.pvsrishabh.cookease.domain.usecases.recipes.SelectRecipe
import com.pvsrishabh.cookease.domain.usecases.recipes.SelectRecipes
import com.pvsrishabh.cookease.domain.usecases.recipes.UpsertRecipe
import com.pvsrishabh.cookease.util.Constants.BASE_URL
import com.pvsrishabh.cookease.util.Constants.RECIPES_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideRecipesApi(): RecipesApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRecipesRepository(
        recipesApi: RecipesApi,
        recipesDao: RecipesDao
    ): RecipesRepository = RecipesRepositoryImpl(recipesApi = recipesApi, recipesDao = recipesDao)

    @Provides
    @Singleton
    fun provideRecipesUseCases(
        recipesRepository: RecipesRepository
    ): RecipesUseCases {
        return RecipesUseCases(
            getRecipes = GetRecipes(recipesRepository),
            searchRecipes = SearchRecipes(recipesRepository),
            upsertRecipe = UpsertRecipe(recipesRepository),
            deleteRecipe = DeleteRecipe(recipesRepository),
            selectRecipes = SelectRecipes(recipesRepository),
            selectRecipe = SelectRecipe(recipesRepository)
        )
    }

    @Provides
    @Singleton
    fun provideRecipesDatabase(
        application: Application
    ): RecipesDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = RecipesDatabase::class.java,
            name = RECIPES_DATABASE_NAME,
        ).addTypeConverter(RecipesTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipesDao(
        recipesDatabase: RecipesDatabase
    ): RecipesDao = recipesDatabase.recipesDao
}