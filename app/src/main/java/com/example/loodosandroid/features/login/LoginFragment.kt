package com.example.loodosandroid.features.login

import android.util.Log
import android.widget.Toast
import com.example.loodosandroid.R
import com.example.loodosandroid.base.ui.BaseFragment
import com.example.loodosandroid.databinding.FragmentLoginBinding
import com.example.loodosandroid.features.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(){
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    companion object {
        const val TAG = "LoginFragment"
    }


    override fun onInitDataBinding() {
        (requireActivity() as MainActivity).showBottomNavigation(false)
        mAuth = Firebase.auth
        val user = mAuth.currentUser

        if (user != null) {
            (requireActivity() as MainActivity).showBottomNavigation()
            viewModel.navigateToSearchCoinsFragment()

        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        viewBinding.registerOrSignInButton.setOnClickListener {
            val email = viewBinding.etEmail.text.toString()
            val password = viewBinding.etPassword.text.toString()
            signIn(email,password)
        }

    }

    override fun getContentViewId(): Int = R.layout.fragment_login

    private fun signUp(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = mAuth.currentUser
                    (requireActivity() as MainActivity).showBottomNavigation()
                    viewModel.navigateToSearchCoinsFragment()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(requireContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signIn(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = mAuth.currentUser
                    (requireActivity() as MainActivity).showBottomNavigation()
                    viewModel.navigateToSearchCoinsFragment()
                } else {
                    when (task.exception) {
                        is FirebaseAuthInvalidUserException -> {
                            signUp(email, password)
                        }
                        else -> {
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(requireContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
    }
}