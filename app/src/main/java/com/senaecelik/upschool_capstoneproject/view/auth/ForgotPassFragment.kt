package com.senaecelik.upschool_capstoneproject.view.auth

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.senaecelik.upschool_capstoneproject.databinding.FragmentForgotPassBinding

class ForgotPassFragment : Fragment() {

    private lateinit var dataBinding: FragmentForgotPassBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var buttonReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance();
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentForgotPassBinding.inflate(inflater, container, false)
        buttonReset = dataBinding.btnSend
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonReset.setOnClickListener {
            resetPass()
        }
    }

    private fun resetPass() {
        val email = dataBinding.emailTextField.text.toString()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(context, "Lütfen email adresinizi giriniz", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Şifreni yenilemen için e-mail adresine link gönderdik.",
                        Toast.LENGTH_LONG
                    ).show();
                } else {
                    Toast.makeText(
                        context,
                        "Şifre yenileme işlemi başarısız oldu.",
                        Toast.LENGTH_SHORT
                    ).show();
                }
            }
    }
}