package data.zohre.data.repository

import data.zohre.data.datasource.remote.RestaurantsRemoteDataSource
import data.zohre.data.datasource.sort.RestaurantSortDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import xyz.zohre.domain.model.Restaurants

@ExperimentalCoroutinesApi
class RemoteRestaurantsRepositoryImplTest {

    @RelaxedMockK
    lateinit var remoteDataSource: RestaurantsRemoteDataSource

    @RelaxedMockK
    lateinit var sortDataSource: RestaurantSortDataSource

    @RelaxedMockK
    lateinit var restaurants: List<Restaurants>

    @RelaxedMockK
    lateinit var restaurant: Restaurants

    @InjectMockKs
    lateinit var remoteRestaurantsRepositoryImpl: RemoteRestaurantsRepositoryImpl

    private lateinit var coroutineDispatcher: TestCoroutineDispatcher

    @Before
    fun setup() {
        coroutineDispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(coroutineDispatcher)
        MockKAnnotations.init(this)
    }

    @After
    fun finalize() {
        unmockkAll()
        Dispatchers.resetMain()
    }
}