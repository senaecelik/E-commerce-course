package com.senaecelik.upschool_capstoneproject.view.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.senaecelik.upschool_capstoneproject.R
import com.senaecelik.upschool_capstoneproject.databinding.FragmentLoginBinding
import com.senaecelik.upschool_capstoneproject.view.main.MainActivity


class LoginFragment : Fragment(){


    private lateinit var binding: FragmentLoginBinding
    private lateinit var  auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnLogin.setOnClickListener {
            login()

        }

        binding.textBtnRegister.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.buttonForgotPass.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_forgotPassFragment)
        }

        binding.buttonGoogleLogIn.setOnClickListener{
            Toast.makeText(context, "Çok yakında", Toast.LENGTH_SHORT).show()
        }

    }

    private fun login() {
        val  email=binding.emailTextField.text.toString()
        val password=binding.passTextField.text.toString()

        if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
            binding.emailTextField.error = "Lütfen email adresi giriniz"
            binding.passTextField.error = "Lütfen şifre giriniz"
        }
        else  if(TextUtils.isEmpty(email)){
            binding.emailTextField.error = "Lütfen email adresi giriniz"
        }else if(TextUtils.isEmpty(password)){
            binding.passTextField.error = "Lütfen şifre giriniz"
        }

        if (email.isNotEmpty() && password.isNotEmpty()){
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if(!task.isSuccessful) {
                    Toast.makeText(context, "Fail", Toast.LENGTH_LONG).show();
                }else{
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
            }.addOnFailureListener { exception ->

                Toast.makeText(context,exception.localizedMessage, Toast.LENGTH_SHORT).show()
            }}else {
            Toast.makeText(context,"Lütfen tüm alanları doldurunuz", Toast.LENGTH_LONG)
        }
    }

}