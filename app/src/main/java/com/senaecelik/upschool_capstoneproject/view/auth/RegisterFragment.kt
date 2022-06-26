package com.senaecelik.upschool_capstoneproject.view.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.senaecelik.upschool_capstoneproject.view.main.MainActivity
import com.senaecelik.upschool_capstoneproject.R
import com.senaecelik.upschool_capstoneproject.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    private lateinit var  auth: FirebaseAuth
    private lateinit var dataBinding: FragmentRegisterBinding
    private lateinit var buttonRegister: Button
    private lateinit var buttonLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= FirebaseAuth.getInstance()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        dataBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        buttonRegister = dataBinding.btnRegister
        buttonLogin = dataBinding.textBtnLogin
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       buttonRegister.setOnClickListener {
            register()
        }

        buttonLogin.setOnClickListener{
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

    }

    private fun register() {
       val  email=dataBinding.emailTextField.text.toString()
        val password=dataBinding.passTextField.text.toString()

        if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
                dataBinding.emailTextField.error = "Lütfen email adresi giriniz"
                dataBinding.passTextField.error = "Lütfen şifre giriniz"
            }
        else  if(TextUtils.isEmpty(email)){
            dataBinding.emailTextField.error = "Lütfen email adresi giriniz"
        }else if(TextUtils.isEmpty(password)){
            dataBinding.passTextField.error = "Lütfen şifre giriniz"
        }


        if (email.isNotEmpty() && password.isNotEmpty()){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->

            if(!task.isSuccessful) {
                if(password.length<6){
                    dataBinding.passTextField.error = "Şifre minimum 6 hanel"
                }else{
                    Toast.makeText(context, "Fail", Toast.LENGTH_LONG).show();
                }
            }else{

                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }.addOnFailureListener { exception ->

            Toast.makeText(context,exception.localizedMessage, Toast.LENGTH_LONG).show()
        }}else {
            Toast.makeText(context,"Lütfen tüm alanları doldurunuz",Toast.LENGTH_LONG)
        }


    }


}