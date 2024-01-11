package com.example.backpress

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.backpress.databinding.ActivityMainBinding
import com.example.helperlibrary.showToast

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG: String = "123aaa"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextButton.setOnClickListener {
            openFragment(FirstFragment.getInstance())
        }

        binding.moveTaskToBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        addOnBackPressedCallback()
    }

    fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.container, fragment).addToBackStack(fragment.javaClass.simpleName).commit()
    }

    private fun addOnBackPressedCallback() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val fragments = supportFragmentManager.fragments
                when (fragments.takeIf { it.isNotEmpty() }?.last()) {
                    is FirstFragment -> {
                        showToast("First fragment removed from stack")
                        supportFragmentManager.popBackStack()
                    }

                    is SecondFragment -> {
                        showToast("Second fragment removed from stack")
                        supportFragmentManager.popBackStack()
                    }

                    null -> moveTaskToBack(true)

                    else -> supportFragmentManager.popBackStack()
                }
            }
        })
    }

//    @SuppressLint("MissingSuperCall")
//    override fun onBackPressed() {
//        Log.d(TAG, "onBackPressed:")
//        val fragments = supportFragmentManager.fragments
//        when (fragments.takeIf { it.isNotEmpty() }?.last()) {
//            is FirstFragment -> {
//                showToast("First fragment removed from stack")
//                supportFragmentManager.popBackStack()
//            }
//
//            is SecondFragment -> {
//                showToast("Second fragment removed from stack")
//                supportFragmentManager.popBackStack()
//            }
//
//            null -> moveTaskToBack(true)
//
//            else -> supportFragmentManager.popBackStack()
//        }
//    }

}