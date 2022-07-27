package xyz.zohre.domain.usecase

import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import xyz.zohre.domain.model.database.RestaurantEntity
import xyz.zohre.domain.repository.LocalRestaurantsRepository

@ExperimentalCoroutinesApi
class FetchLocalRestaurantsUseCaseTest{

    @RelaxedMockK
    lateinit var localRestaurantsRepository: LocalRestaurantsRepository

    @InjectMockKs
    lateinit var fetchLocalRestaurantsUseCase: FetchLocalRestaurantsUseCase

    private lateinit var coroutineDispatcher: TestCoroutineDispatcher

    @Before
    fun setUp() {
        coroutineDispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(coroutineDispatcher)
        MockKAnnotations.init(this)
    }

    @Test
    fun `test fetch restaurants successful response`() = coroutineDispatcher.runBlockingTest {
        val restaurant = mockk<RestaurantEntity>()
        coEvery {
            localRestaurantsRepository.fetchRestaurants()
        } returns flowOf(listOf(restaurant))

        val firstResponse = fetchLocalRestaurantsUseCase.fetchRestaurants().first()
        val lastResponse = fetchLocalRestaurantsUseCase.fetchRestaurants().last()

        coVerify { localRestaurantsRepository.fetchRestaurants() }
        assert(firstResponse.isNotEmpty())
        assertEquals(lastResponse, firstResponse)
    }

    @After
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain()
    }

}