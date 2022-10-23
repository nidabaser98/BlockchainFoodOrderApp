package dev.nida.blockchainfoodorderapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.nida.blockchainfoodorderapp.entity.SepetYemekler
import dev.nida.blockchainfoodorderapp.repo.FoodsDaoRepository

class BasketFragmentViewModel : ViewModel() {
    var sepetYemeklerListesi: MutableLiveData<List<SepetYemekler>>
    private var fdao = FoodsDaoRepository()
    val kullanici_adi:String = "nidabaser"

    init {
        sepetYemekleriYukle(kullanici_adi)
        sepetYemeklerListesi = fdao.sepetYemekleriGetir()
    }

    fun sepetYemekleriYukle(kullanici_adi: String) {
        fdao.tumSepetYemekleriAl("nidabaser")
    }

    fun yemekSil(sepet_yemek_id: Int, kullanici_adi: String) {
        fdao.sepettenYemekSilme(sepet_yemek_id, kullanici_adi)
    }
}