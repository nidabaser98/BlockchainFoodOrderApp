package dev.nida.blockchainfoodorderapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import dev.nida.blockchainfoodorderapp.R
import dev.nida.blockchainfoodorderapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var tasarim:FragmentLoginBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = FragmentLoginBinding.inflate(inflater, container, false)

        tasarim.btnLogin.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.actLoginToHome)
        }

        return tasarim.root
    }
}