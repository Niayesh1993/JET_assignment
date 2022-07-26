package data.zohre.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import data.zohre.data.database.RestaurantDao
import data.zohre.data.database.RestaurantDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RestaurantDatabaseModule {

    @Provides
    @Singleton
    fun provideRestaurantDatabase(@ApplicationContext appContext: Context): RestaurantDatabase {
        return Room.databaseBuilder(
            appContext,
            RestaurantDatabase::class.java,
            "JETdb"
        ).build()
    }

    @Provides
    fun provideRestaurantDao(appDatabase: RestaurantDatabase): RestaurantDao {
        return appDatabase.getRestaurantDao()
    }
}