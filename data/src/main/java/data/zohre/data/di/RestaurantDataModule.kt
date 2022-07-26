package data.zohre.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import data.zohre.data.datasource.local.RestaurantLocalDataSource
import data.zohre.data.datasource.local.RestaurantLocalDataSourceImpl
import data.zohre.data.datasource.remote.RestaurantsRemoteDataSource
import data.zohre.data.datasource.remote.RestaurantsRemoteDataSourceImpl
import data.zohre.data.datasource.sort.RestaurantSortDataSource
import data.zohre.data.datasource.sort.RestaurantSortDataSourceImpl
import data.zohre.data.repository.LocalRestaurantsRepositoryImpl
import data.zohre.data.repository.RemoteRestaurantsRepositoryImpl
import xyz.zohre.domain.repository.LocalRestaurantsRepository
import xyz.zohre.domain.repository.RemoteRestaurantsRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RestaurantDataModule {

    @Binds
    abstract fun bindsRemoteSource(
        restaurantsRemoteDataSourceImpl: RestaurantsRemoteDataSourceImpl
    ): RestaurantsRemoteDataSource

    @Binds
    abstract fun bindsLocalSource(
        restaurantLocalDataSource: RestaurantLocalDataSourceImpl
    ): RestaurantLocalDataSource

    @Binds
    abstract fun bindsSortingSource(
        restaurantSortDataSourceImpl: RestaurantSortDataSourceImpl
    ): RestaurantSortDataSource

    @Binds
    abstract fun bindsRemoteRestaurantsRepository(
        restaurantsRepositoryImpl: RemoteRestaurantsRepositoryImpl
    ): RemoteRestaurantsRepository

    @Binds
    abstract fun bindsLocalRestaurantsRepository(
        localRestaurantsRepositoryImpl: LocalRestaurantsRepositoryImpl
    ): LocalRestaurantsRepository

}