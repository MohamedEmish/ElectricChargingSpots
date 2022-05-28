package com.amosh.remote.utils

import com.amosh.remote.model.*


/**
 * Dummy data generator for tests
 */
class TestDataGenerator {

    companion object {
        fun getListOfItems():  List<SpotsNetworkResponse> {
            return listOf(
                SpotsNetworkResponse(
                    ID = 1
                )
            )
        }
    }

}