package com.example.practicaltask_rupalkachhela.base

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import javax.inject.Inject

abstract class BaseActivity <T : ViewDataBinding, V : ViewModel> : AppCompatActivity(){

    lateinit var binding: T

    @Inject
    lateinit var viewModel: V

    var activity: Activity? = null

    abstract val layoutId: Int
    abstract val bindingVariable: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this

        binding = DataBindingUtil.setContentView(activity!!, layoutId)
        binding.setVariable(bindingVariable, viewModel)

        binding.executePendingBindings()

    }

}