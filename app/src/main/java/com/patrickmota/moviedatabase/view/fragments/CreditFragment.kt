package com.patrickmota.moviedatabase.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.patrickmota.moviedatabase.databinding.FragmentCreditBinding
import com.patrickmota.moviedatabase.view.adapters.CreditAdapter
import com.patrickmota.moviedatabase.viewmodel.fragments.CreditViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CreditFragment() : Fragment() {

    private lateinit var creditAdapter: CreditAdapter
    private lateinit var binding: FragmentCreditBinding

    private val creditViewModel: CreditViewModel by viewModel()

    private var id: String = ""
    private var viewAll: Boolean = false

    constructor(id: String, viewAll: Boolean) : this() {
        this.id = id
        this.viewAll = viewAll
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreditBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        creditViewModel.getCreditResponse(id)
        creditViewModel.creditResponse().observe(viewLifecycleOwner) {
            if (!viewAll) {
                if (it.cast.size > 4) {
                    it.cast = it.cast.subList(0, 4)
                }
            }

            val recyclerView: RecyclerView = binding.recyclerviewCast

            creditAdapter = CreditAdapter()

            creditAdapter.items = it

            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = creditAdapter
            }
        }

        creditViewModel.error().observe(viewLifecycleOwner){
            showConnectionError()
        }
    }

    private fun showConnectionError() {
        binding.recyclerviewCast.visibility = View.GONE
        binding.ivError.visibility = View.VISIBLE
        binding.tvError.visibility = View.VISIBLE
        binding.tvErrorTip.visibility = View.VISIBLE
    }


}