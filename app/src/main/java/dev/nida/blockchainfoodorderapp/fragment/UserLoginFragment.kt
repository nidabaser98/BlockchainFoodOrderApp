package dev.nida.blockchainfoodorderapp.fragment

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import dev.nida.blockchainfoodorderapp.R
import dev.nida.blockchainfoodorderapp.databinding.FragmentUserLoginBinding

class UserLoginFragment : Fragment() {
    private lateinit var tasarim: FragmentUserLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = FragmentUserLoginBinding.inflate(inflater, container, false)

        emailFocusListener()
        passwordFocusListener()

        tasarim.btnUserLogin.setOnClickListener {

            val validEmail = tasarim.emailContainer.helperText == null
            //val validPassword = tasarim.passwordContainer.helperText == null
            if(validEmail) { // &&validPassword
                Navigation.findNavController(it).navigate(R.id.actLoginToHome)
            }else{
                Snackbar.make(it,"Lütfen geçerli mail ve şifre girin",Snackbar.LENGTH_SHORT).show()
            }
        }

        return tasarim.root
    }

    private fun emailFocusListener(){
        tasarim.emailEditText.setOnFocusChangeListener{_, focused ->
            if(!focused){
                tasarim.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val email = tasarim.emailEditText.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Geçersiz E-mail Adresi"
        }
        return null
    }

    private fun passwordFocusListener(){
        tasarim.passwordEditText.setOnFocusChangeListener{_, focused ->
            if(!focused){
                tasarim.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val password = tasarim.passwordEditText.text.toString()
        //if(password.length < 8) {
        //    return "Minimum 8 karakter olmalı"
        //}
        //if(password.matches(".*[A-Z].*".toRegex())) {
        //    return "En az bir büyük harf içermeli"
        //}
        //if(password.matches(".*[a-z].*".toRegex())) {
        //    return "En az bir küçük harf içermeli"
        //}
        //if(password.matches(".*[@#\$%^&+=].*".toRegex())) {
        //    return "En az bir özel karakter içermeli"
        //}
        return null
    }
}