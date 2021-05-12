package com.scallop.marveldex.ui.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scallop.marveldex.R
import com.scallop.marveldex.databinding.FragmentCharacterListBinding
import com.scallop.marveldex.entities.MarvelCharacter
import com.scallop.marveldex.ui.commons.EndlessRecyclerViewScrollListener
import com.scallop.marveldex.ui.commons.OnItemClick
import com.scallop.marveldex.ui.commons.viewBinding
import com.scallop.marveldex.ui.commons.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment : Fragment(), OnItemClick<MarvelCharacter> {

    private val mViewModel: CharacterListViewModel by viewModel()

    private val binding by viewBinding<FragmentCharacterListBinding>()

    private lateinit var mAdapter: CharacterAdapter
    private lateinit var mLayoutManager: GridLayoutManager
    private lateinit var mEndlessRecyclerViewScrollListener: EndlessRecyclerViewScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAdapter = CharacterAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mLayoutManager = GridLayoutManager(context, 2)

        mEndlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(
            mLayoutManager,
            STARTING_PAGE_INDEX
        ) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                mViewModel.getCharacters(page)
            }
        }

        with(binding) {
            characterList.adapter = mAdapter
            characterList.layoutManager = mLayoutManager
            characterList.addOnScrollListener(mEndlessRecyclerViewScrollListener)
        }

        mViewModel.data.observe(viewLifecycleOwner, {
            with(binding) {
                when (it) {
                    is CharacterListState.CharacterListLoading -> {
                        progressBar.visible(it.show)
                    }
                    is CharacterListState.CharacterListItems -> {
                        this.progressBar.visibility = View.GONE
                        updateList(it.items)
                    }
                    is CharacterListState.CharacterListFailure -> {
                        Toast.makeText(context, it.failure, Toast.LENGTH_LONG).show()
                    }
                    else -> throw IllegalStateException(it.toString())
                }
            }
        })
    }

    private fun updateList(items: List<MarvelCharacter>) {
        mAdapter.submitList(items)

        with(binding) {
            if (mAdapter.itemCount == 0) {
                emptyLabel.visible(true)
                characterList.visible(false)
            } else {
                emptyLabel.visible(false)
                characterList.visible(true)
            }
        }
    }

    override fun onItemClicked(item: MarvelCharacter) {
        showMigraineDetail(item)
    }


    private fun showMigraineDetail(item: MarvelCharacter) {
        val action = CharacterListFragmentDirections.showDetail()
        action.characterId = item.id

        val navController = view?.findNavController()
        navController?.navigate(action)
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 0
    }
}
