package com.scallop.marveldex.data.repository

import com.google.common.truth.Truth
import com.scallop.marveldex.data.BaseTest
import com.scallop.marveldex.data.mappers.DataEntityMapper
import com.scallop.marveldex.domain.entities.ResultWrapperEntity
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
    fun `get character list with succesful results`() {
        runBlocking {
            val results = mRepository.getCharacters(20, 0)
            results.collect {
                Truth.assertThat(it).isNotNull()
                Truth.assertThat(it).isInstanceOf(ResultWrapperEntity.Success::class.java)
                assert(it is ResultWrapperEntity.Success)
                it as ResultWrapperEntity.Success
                Truth.assertThat(it.value).hasSize(20)
            }
        }
    }
}