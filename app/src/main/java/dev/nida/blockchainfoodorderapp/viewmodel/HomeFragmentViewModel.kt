package dev.nida.blockchainfoodorderapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.nida.blockchainfoodorderapp.entity.Yemekler
import dev.nida.blockchainfoodorderapp.repo.FoodsDaoRepository

class HomeFragmentViewModel : ViewModel() {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    private val fdao = FoodsDaoRepository()

    init {
        yemekleriYukle()
        yemeklerListesi = fdao.yemekleriGetir()
    }

    fun yemekleriYukle() {
        fdao.tumYemekleriAl()
    }
}