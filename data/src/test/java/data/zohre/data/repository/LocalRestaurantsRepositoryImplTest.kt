package data.zohre.data.repository

import data.zohre.data.datasource.local.RestaurantLocalDataSourceImpl
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import xyz.zohre.domain.model.database.RestaurantEntity


@ExperimentalCoroutinesApi
class LocalRestaurantsRepositoryImplTest {

    @RelaxedMockK
    lateinit var restaurantLocalDataSourceImpl: RestaurantLocalDataSourceImpl

    @RelaxedMockK
    lateinit var restaurant: RestaurantEntity

    @InjectMockKs
    lateinit var localRestaurantsRepositoryImpl: LocalRestaurantsRepositoryImpl

    private lateinit var coroutineDispatcher: TestCoroutineDispatcher

    @Before
    fun setup() {
        coroutineDispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(coroutineDispatcher)
        MockKAnnotations.init(this)
    }

    @Test
    fun `test fetch restaurants success - method call`() = coroutineDispatcher.runBlockingTest {

        coEvery {
            restaurantLocalDataSourceImpl.fetchRestaurants()
        } returns flowOf(listOf(restaurant))

        localRestaurantsRepositoryImpl.fetchRestaurants().last()

        coVerify { restaurantLocalDataSourceImpl.fetchRestaurants() }
    }

    @After
    fun finalize() {
        unmockkAll()
        Dispatchers.resetMain()
    }
}