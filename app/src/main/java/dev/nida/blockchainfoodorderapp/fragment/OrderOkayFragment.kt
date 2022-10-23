package dev.nida.blockchainfoodorderapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import dev.nida.blockchainfoodorderapp.R
import dev.nida.blockchainfoodorderapp.databinding.FragmentOrderOkayBinding

class OrderOkayFragment : Fragment() {
    private lateinit var tasarim:FragmentOrderOkayBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = FragmentOrderOkayBinding.inflate(inflater,container,false)


        tasarim.btnReturn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.actReturnHome)
        }


        return tasarim.root
    }
}