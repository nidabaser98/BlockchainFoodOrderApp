package dev.nida.blockchainfoodorderapp.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import dev.nida.blockchainfoodorderapp.MainActivityWallet
import dev.nida.blockchainfoodorderapp.R
import dev.nida.blockchainfoodorderapp.adapter.BasketFoodsAdapter
import dev.nida.blockchainfoodorderapp.databinding.FragmentBasketBinding
import dev.nida.blockchainfoodorderapp.viewmodel.BasketFragmentViewModel

class BasketFragment : Fragment() {
    private lateinit var tasarim: FragmentBasketBinding
    private lateinit var viewModel: BasketFragmentViewModel
    private lateinit var adapter: BasketFoodsAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)
        tasarim.fragmentBasket = this

        tasarim.imageViewHome.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.actBasketToHome)
        }

        tasarim.textViewEmptyBasket.visibility = View.INVISIBLE

        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner) {
                sepetYemeklerListesi -> var toplamSonuc = 0
                sepetYemeklerListesi.map { toplamSonuc += it.yemek_fiyat * it.yemek_siparis_adet }
                tasarim.tvTopPrice.text = "$toplamSonuc ₺"
                tasarim.tvTopPrice2.text = (toplamSonuc * 0.000045).toString() + " ETH"
                tasarim.toplamEthTextView.text = (toplamSonuc * 0.000045).toString()

            if(viewModel.sepetYemeklerListesi.value.isNullOrEmpty()){
                tasarim.textViewEmptyBasket.visibility = View.VISIBLE
            }

            adapter = BasketFoodsAdapter(requireContext(), sepetYemeklerListesi, viewModel)
            tasarim.basketFoodsAdapter = adapter
        }

        tasarim.btnOkay.setOnClickListener {
            if (viewModel.sepetYemeklerListesi.value.isNullOrEmpty()){
                Snackbar.make(it,"Lütfen sepete en az 1 adet ürün ekleyin",Snackbar.LENGTH_SHORT).show()
            }else{
                Navigation.findNavController(it).navigate(R.id.actBasketToOkay)
            }
        }


        tasarim.btnOkay2.setOnClickListener {
            if (viewModel.sepetYemeklerListesi.value.isNullOrEmpty()){
                Snackbar.make(it,"Lütfen sepete en az 1 adet ürün ekleyin",Snackbar.LENGTH_SHORT).show()
            }else{

                //INTENT
                val payment = tasarim.toplamEthTextView.text
                val intent = Intent(requireContext(), MainActivityWallet::class.java)
                intent.putExtra("key", payment)
                startActivity(intent)

            }
        }

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: BasketFragmentViewModel by viewModels()
        viewModel = tempViewModel


    }
}