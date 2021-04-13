package com.scallop.marveldex.data.repository

import com.google.common.truth.Truth
import com.scallop.marveldex.data.BaseTest
import com.scallop.marveldex.data.mappers.DataEntityMapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


internal class RepositoryImplTest : BaseTest() {

    private lateinit var mRepository: RepositoryImpl

    @Before
    override fun setup() {
        super.setup()
        mRepository = RepositoryImpl(RemoteDataSourceImpl(mApi), DataEntityMapper())
    }

    @Test
    fun `get weather by location wasith successful results`() {
        runBlocking {
            val results = mRepository.getCharacters(20, 0)
            results.collect {
                Truth.assertThat(it).isNotNull()
                Truth.assertThat(it).hasSize(20)
            }
        }
    }
}