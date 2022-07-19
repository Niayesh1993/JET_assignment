package data.zohre.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import data.zohre.data.datasource.RestaurantsRemoteDataSource
import data.zohre.data.datasource.RestaurantsRemoteDataSourceImpl
import data.zohre.data.repository.RestaurantsRepositoryImpl
import xyz.zohre.domain.repository.RestaurantsRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RestaurantDataModule {

    @Binds
    abstract fun bindsRemoteSource(
        restaurantsRemoteDataSourceImpl: RestaurantsRemoteDataSourceImpl
    ): RestaurantsRemoteDataSource

    @Binds
    abstract fun bindsRestaurantsRepository(
        restaurantsRepositoryImpl: RestaurantsRepositoryImpl
    ): RestaurantsRepository

}