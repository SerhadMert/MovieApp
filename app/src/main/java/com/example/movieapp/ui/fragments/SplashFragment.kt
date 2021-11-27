package com.example.movieapp.ui.fragments

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.base.BaseFragment
import com.example.movieapp.databinding.FragmentSplashBinding


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lottieAnimationListener()
    }
    private fun lottieAnimationListener() {
        binding.lottieAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
                Log.v("Animation", "Animation started")
            }

            override fun onAnimationEnd(p0: Animator?) {
                findNavController().navigate(R.id.action_splashFragment_to_movieListFragment)
            }

            override fun onAnimationCancel(p0: Animator?) {
                Log.v("Animation", "Animation cancelled")
            }

            override fun onAnimationRepeat(p0: Animator?) {
                Log.v("Animation", "Animation repeated")
            }
        })
    }
}
