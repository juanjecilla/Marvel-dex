package com.scallop.marveldex.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import com.scallop.marveldex.R
import com.scallop.marveldex.databinding.FragmentDetailBinding
import com.scallop.marveldex.entities.MarvelCharacter
import com.scallop.marveldex.ui.commons.viewBinding
import com.scallop.marveldex.ui.commons.visible
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DetailFragment : Fragment() {

    private val mViewModel: DetailViewModel by viewModel {
        parametersOf(arguments?.let {
            val passedArguments = DetailFragmentArgs.fromBundle(it)
            passedArguments.characterId
        })
    }

    private val binding by viewBinding<FragmentDetailBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(false)

        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.data.observe(viewLifecycleOwner, {
            with(binding) {
                when (it) {
                    is DetailState.DetailLoading -> {
                        progressBar.visible(it.show)
                    }
                    is DetailState.DetailSuccess -> {
                        updateUI(it.item)
                    }
                    is DetailState.DetailFailure -> {
                        Toast.makeText(context, it.failure, Toast.LENGTH_LONG).show()
                    }
                    else -> throw IllegalStateException(it.toString())
                }
            }
        })
    }

    private fun updateUI(character: MarvelCharacter) {
        with(binding) {
            characterName.text = character.name
            characterImage.load("${character.thumbnail.path}/portrait_incredible.${character.thumbnail.extension}")

            if (character.description.isNotEmpty()) {
                characterDescription.text = character.description
            } else {
                characterDescription.text = getString(R.string.empty_description)
            }
        }
    }
}
