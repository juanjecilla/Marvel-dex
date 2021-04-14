package com.scallop.marveldex.data.repository

import com.google.common.truth.Truth
import com.scallop.marveldex.data.BaseTest
import com.scallop.marveldex.data.entitites.MarvelCharacterData
import com.scallop.marveldex.data.entitites.ResultWrapperData
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
    fun `get characters with successful results`() {
        runBlocking {
            val results = mRemote.getCharacters(20, 0)
            results.collect {
                Truth.assertThat(it).isNotNull()
                Truth.assertThat(it).isInstanceOf(ResultWrapperData.Success::class.java)
                assert(it is ResultWrapperData.Success)
                it as ResultWrapperData.Success
                Truth.assertThat(it.value).hasSize(20)
            }
        }
    }
}