package dev.nida.blockchainfoodorderapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dev.nida.blockchainfoodorderapp.R
import dev.nida.blockchainfoodorderapp.adapter.FoodsAdapter
import dev.nida.blockchainfoodorderapp.databinding.FragmentHomeBinding
import dev.nida.blockchainfoodorderapp.viewmodel.HomeFragmentViewModel

class HomeFragment : Fragment() {
    private lateinit var tasarim: FragmentHomeBinding
    private lateinit var adapter: FoodsAdapter
    private lateinit var viewModel: HomeFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
                yemeklerListesi -> adapter = FoodsAdapter(requireContext(), yemeklerListesi)
            tasarim.foodsAdapter = adapter
        }

        val gridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        tasarim.rv.layoutManager = gridLayoutManager
        tasarim.rv.setHasFixedSize(true)

        tasarim.imageViewBasket.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.actHomeToBasket)
        }

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomeFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }

}