package xyz.zohre.domain.usecase

import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import xyz.zohre.domain.TestData
import xyz.zohre.domain.model.Restaurants
import xyz.zohre.domain.repository.RemoteRestaurantsRepository

@ExperimentalCoroutinesApi
class SortRestaurantUseCaseTest {

    @RelaxedMockK
    lateinit var remoteRestaurantsRepository: RemoteRestaurantsRepository

    @InjectMockKs
    lateinit var sortRestaurantUseCase: SortRestaurantUseCase

    private lateinit var coroutineDispatcher: TestCoroutineDispatcher

    @Before
    fun setUp() {
        coroutineDispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(coroutineDispatcher)
        MockKAnnotations.init(this)
    }

    @Test
    fun `test sort restaurants successful response`() = coroutineDispatcher.runBlockingTest {
        val restaurant = mockk<Restaurants>()
        val sampleList = TestData.list

        every {
            remoteRestaurantsRepository.sortRestaurant(any(), any())
        } returns listOf(restaurant)

        val firstResponse = sortRestaurantUseCase.sortRestaurant("newest", sampleList)
        val lastResponse = sortRestaurantUseCase.sortRestaurant("newest", sampleList)

        coVerify { sortRestaurantUseCase.sortRestaurant("newest", sampleList) }
        assert(firstResponse.isNotEmpty())
        assertEquals(lastResponse, firstResponse)
    }

    @After
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain()
    }

}