package com.senaecelik.upschool_capstoneproject.view.main.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.senaecelik.upschool_capstoneproject.R
import com.senaecelik.upschool_capstoneproject.databinding.FragmentProfileBinding
import com.senaecelik.upschool_capstoneproject.view.splash.SplashActivity

class ProfileFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding) {
            emailText.text = firebaseAuth.currentUser?.email
            buttonFav.setOnClickListener {
                it.findNavController().navigate(R.id.action_profileFragment_to_favFragment)
            }
            buttonBag.setOnClickListener {
                it.findNavController().navigate(R.id.action_profileFragment_to_bagFragment)
            }

            buttonSignOut.setOnClickListener {

                firebaseAuth.signOut()
                val intent = Intent(requireContext(), SplashActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }

            backButton.setOnClickListener {
                it.findNavController().navigateUp()
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

