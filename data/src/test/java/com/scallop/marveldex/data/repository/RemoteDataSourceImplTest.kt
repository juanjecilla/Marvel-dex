package com.scallop.marveldex.data.repository

import com.google.common.truth.Truth
import com.scallop.marveldex.data.BaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RemoteDataSourceImplTest : BaseTest() {

    private lateinit var mRemote: RemoteDataSourceImpl

    @Before
    override fun setup() {
        super.setup()
        mRemote = RemoteDataSourceImpl(mApi)
    }

    @Test
    fun `get weather by location with successful results`() {
        runBlocking {
            val results = mRemote.getCharacters(20, 0)
            results.collect {
                Truth.assertThat(it).isNotNull()
                Truth.assertThat(it).hasSize(20)
            }
        }
    }
}