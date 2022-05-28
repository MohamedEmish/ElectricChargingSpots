package com.amosh.remote

import androidx.test.filters.SmallTest
import com.amosh.data.model.ChargerSpotDTO
import com.amosh.data.repository.RemoteDataSource
import com.amosh.remote.api.ApiService
import com.amosh.remote.mapper.SpotNetworkMapper
import com.amosh.remote.source.RemoteDataSourceImp
import com.amosh.remote.utils.TestDataGenerator
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class RemoteDataSourceImpTest {

    @MockK
    private lateinit var apiService: ApiService
    private val mapper = SpotNetworkMapper()

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RemoteDataSourceImp before every test
        remoteDataSource = RemoteDataSourceImp(
            apiService = apiService,
            spotMapper = mapper,
        )
    }

    @Test
    fun test_get_charging_spots_success() = runBlockingTest {

        val list = TestDataGenerator.getListOfItems()

        // Given
        coEvery { apiService.getChargingSpots(any()) } returns list

        // When
        val result = remoteDataSource.getChargingSpots(0.1, 0.1)

        // Then
        coVerify { apiService.getChargingSpots(any()) }

        // Assertion
        val expectedList: MutableList<ChargerSpotDTO> = mutableListOf()
        list.forEach {
            expectedList.add(mapper.from(it))
        }
        val expected = expectedList.toList()
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_charging_spots_fail() = runBlockingTest {

        // Given
        coEvery { apiService.getChargingSpots(any()) } throws Exception()

        // When
        remoteDataSource.getChargingSpots(0.1, 0.1)

        // Then
        coVerify { apiService.getChargingSpots(any()) }
    }
}