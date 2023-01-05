package com.example.practicaltask_rupalkachhela.view

import android.os.Bundle
import android.widget.Toast
import com.example.practicaltask_rupalkachhela.R
import com.example.practicaltask_rupalkachhela.base.BaseActivity
import com.example.practicaltask_rupalkachhela.databinding.ActivityAtmactivityBinding
import com.example.practicaltask_rupalkachhela.viewmodel.ATMViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.practicaltask_rupalkachhela.BR
import com.example.practicaltask_rupalkachhela.model.AddAmount
import com.example.practicaltask_rupalkachhela.model.Notes
import com.example.practicaltask_rupalkachhela.model.WithdrawAmount
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class ATMActivity : BaseActivity<ActivityAtmactivityBinding, ATMViewModel>() {

    override val layoutId: Int
        get() = R.layout.activity_atmactivity
    override val bindingVariable: Int
        get() = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        totalBalance()
        insertNotes()

        setClickEvents()
    }


    private fun totalBalance(): Int {
        val addTotal = viewModel!!.getTotalBalance()
        val withdrawTotal = viewModel!!.getTotalWithdrawAmount()

        var balance = addTotal - withdrawTotal
        binding.txtTotalBalance.text = "Total Balance : $balance"

        return balance
    }

    fun setClickEvents() {
        binding.btnAdd.setOnClickListener {
            addAmountToDB()
        }

        binding.btnWithdraw.setOnClickListener {
            withDrawAmount()
        }
    }

    private fun addAmountToDB() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel!!.addAmount(AddAmount(amount = viewModel!!.amount))
            withContext(Dispatchers.Main) {
                totalBalance()
            }
        }
    }

    private fun withDrawAmount() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel!!.insertWithDrawAmount(WithdrawAmount(amount = viewModel!!.amount))
            withContext(Dispatchers.Main) {
                totalBalance()
            }
        }
    }

    private fun insertNotes() {

        val note2000 = Notes("2000", 25)
        val note500 = Notes("500", 25)
        val note100 = Notes("100", 25)
        val note20 = Notes("20", 25)
        val note10 = Notes("10", 25)

        CoroutineScope(Dispatchers.IO).launch {
            viewModel!!.insertNotes(note2000)
            viewModel!!.insertNotes(note500)
            viewModel!!.insertNotes(note100)
            viewModel!!.insertNotes(note20)
            viewModel!!.insertNotes(note10)
        }
    }
}