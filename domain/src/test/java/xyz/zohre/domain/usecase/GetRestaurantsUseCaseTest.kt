package xyz.zohre.domain.usecase

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import xyz.zohre.domain.repository.RemoteRestaurantsRepository

@ExperimentalCoroutinesApi
class GetRestaurantsUseCaseTest{

    @RelaxedMockK
    lateinit var remoteRestaurantsRepository: RemoteRestaurantsRepository

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

    }
}