package xyz.zohre.domain.usecase

import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import xyz.zohre.domain.model.Restaurants
import xyz.zohre.domain.repository.RestaurantsRepository

@ExperimentalCoroutinesApi
class GetRestaurantsUseCaseTest{

    @RelaxedMockK
    lateinit var restaurantsRepository: RestaurantsRepository

    @InjectMockKs
    lateinit var getRestaurantsUseCase: GetRestaurantsUseCase

    private lateinit var coroutineDispatcher: TestCoroutineDispatcher

    @Before
    fun setUp() {
        coroutineDispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(coroutineDispatcher)
        MockKAnnotations.init(this)
    }

    @Test
    fun `test get restaurants successful response`() = coroutineDispatcher.runBlockingTest {
        val restaurant = mockk<Restaurants>()
        every {
            restaurantsRepository.getRestaurants()
        } returns flowOf(Result.success(listOf(restaurant)))

        val firstResponse = getRestaurantsUseCase.execute().first()
        val lastResponse = getRestaurantsUseCase.execute().last()

        coVerify { restaurantsRepository.getRestaurants() }
        assertTrue(firstResponse.isSuccess)
        assert(firstResponse.getOrNull() != null)
        assert(firstResponse.getOrNull()!!.isNotEmpty())
        assertEquals(lastResponse, firstResponse)
    }
}