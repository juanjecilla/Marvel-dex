package com.scallop.marveldex.ui.characterlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.scallop.marveldex.CoroutineTestRule
import com.scallop.marveldex.fakes.FakeGetCharactersUseCase
import com.scallop.marveldex.mappers.CharacterMapper
import com.scallop.marveldex.utils.Status
import com.scallop.randomweather.utils.TestUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class CharacterListViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var mViewModel: CharacterListViewModel
    private lateinit var mMapper: CharacterMapper

    @Mock
    lateinit var mObserver: Observer<CharacterListState>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mMapper = CharacterMapper()
        mViewModel = CharacterListViewModel(
            FakeGetCharactersUseCase(Status.SUCCESSFUL),
            mMapper,
            coroutineTestRule.dispatcher
        )
        mViewModel.data.observeForever(mObserver)
    }

    @Test
    fun `getting items on viewModel init with successful result`() {
        Mockito.verify(mObserver).onChanged(
            CharacterListState.CharacterListItems(
                mMapper.mapCharacters(
                    TestUtils.getCharacters(20)
                )
            )
        )
    }
}