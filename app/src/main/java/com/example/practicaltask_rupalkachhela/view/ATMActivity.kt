package com.example.practicaltask_rupalkachhela.view

import android.os.Bundle
import com.example.practicaltask_rupalkachhela.R
import com.example.practicaltask_rupalkachhela.base.BaseActivity
import com.example.practicaltask_rupalkachhela.databinding.ActivityAtmactivityBinding
import com.example.practicaltask_rupalkachhela.viewmodel.ATMViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.practicaltask_rupalkachhela.BR


@AndroidEntryPoint
class ATMActivity : BaseActivity<ActivityAtmactivityBinding,ATMViewModel>() {

    override val layoutId: Int
        get() = R.layout.activity_atmactivity
    override val bindingVariable: Int
        get() = BR._all

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}