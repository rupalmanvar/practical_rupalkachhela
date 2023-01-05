package com.example.practicaltask_rupalkachhela.view

import android.os.Bundle
import com.example.practicaltask_rupalkachhela.R
import com.example.practicaltask_rupalkachhela.base.BaseActivity
import com.example.practicaltask_rupalkachhela.databinding.ActivityMainBinding
import com.example.practicaltask_rupalkachhela.viewmodel.ATMViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() :  BaseActivity<ActivityMainBinding,ATMViewModel>(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}