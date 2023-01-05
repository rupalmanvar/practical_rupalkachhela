package com.example.practicaltask_rupalkachhela.view

import android.os.Bundle
import android.util.Log
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

    private fun setClickEvents() {
        binding.btnAdd.setOnClickListener {
            if (amountIsNotEmpty()) {
                addAmountToDB()
            }
        }

        binding.btnWithdraw.setOnClickListener {
            if (amountIsNotEmpty()) {
                if (checkValidationForWithdrawAmount()) {
                    withDrawAmount()
                }
            }
        }

        binding.btnTransactionHistory.setOnClickListener {
            getTransactionHistory()

        }
    }

    fun amountIsNotEmpty(): Boolean {
        if (viewModel!!.amount.isNullOrBlank()) {
            Toast.makeText(this@ATMActivity, "Enter amount", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun addAmountToDB() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel!!.addAmount(AddAmount(amount = viewModel!!.amount))
            withContext(Dispatchers.Main) {
                totalBalance()
                binding.inputAmount.editText?.text?.clear()
            }
        }
    }

    private fun withDrawAmount() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel!!.insertWithDrawAmount(WithdrawAmount(amount = viewModel!!.amount))
            withContext(Dispatchers.Main) {
                totalBalance()
                updateLastTranslation(viewModel!!.amount.toInt(), true)
                binding.inputAmount.editText?.text?.clear()
            }
        }
    }


    fun checkValidationForWithdrawAmount(): Boolean {
        val addTotal = viewModel!!.getTotalBalance()
        val withdrawTotal = viewModel!!.getTotalWithdrawAmount()

        val totalBalance = addTotal - withdrawTotal

        if (!viewModel!!.amount.isNullOrBlank()) {
            if (viewModel!!.amount.toInt() > totalBalance) {
                Toast.makeText(
                    this@ATMActivity,
                    "Insufficient Balance",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
        }

        return true
    }

    private fun insertNotes() {
//
//        val note2000 = Notes("2000", 25)
//        val note500 = Notes("500", 25)
//        val note100 = Notes("100", 25)
//        val note20 = Notes("20", 25)
//        val note10 = Notes("10", 25)
        val note2000 = Notes("2000", 23)
        val note500 = Notes("500", 25)
        val note100 = Notes("100", 50)
        val note20 = Notes("20", 100)
        val note10 = Notes("10", 21)

        CoroutineScope(Dispatchers.IO).launch {
            viewModel!!.insertNotes(note2000)
            viewModel!!.insertNotes(note500)
            viewModel!!.insertNotes(note100)
            viewModel!!.insertNotes(note20)
            viewModel!!.insertNotes(note10)
        }
    }


    private fun getTransactionHistory() {
        var trasactions: List<WithdrawAmount> = ArrayList()
        if (viewModel!!.getTransactionList().isNotEmpty()) {
            trasactions = viewModel!!.getTransactionList()

            val builder: StringBuilder = StringBuilder()
            builder.append("Transaction History:\n\n")
            for (i in trasactions.indices) {
                builder.append("Transaction - $i : ${trasactions[i].amount}\n")
                binding.txtTrasnsactionHistory.text = builder.toString()
            }
        }
    }

    fun showWithdrawAmountDetail(amount: Int, updateNotes: Boolean = false) {
        val amg = calculationForAvailableNotes(amount, updateNotes)
        val builder: StringBuilder = StringBuilder()
        builder.append("Withdraw amount detail : $amount \n\n")
        for ((index, value) in amg.withIndex()) {
            if (value.numberOfNotes != 0) {
                val t = value.numberOfNotes * value.note.toInt()
                builder.append("${value.note} * ${value.numberOfNotes} = $t/-\n")
            }
        }
        binding.txtTrasnsactionHistory.text = builder.toString()

    }



    private fun updateLastTranslation(amount: Int, updateNotes: Boolean = false) {
        val amg = calculationForAvailableNotes(amount, updateNotes)
        val builder: java.lang.StringBuilder = StringBuilder()
        builder.append("Last Transaction : $amount \n\n")

        for ((index, value) in amg.withIndex()) {
//            val t = value.numberOfNotes * value.note.toInt()
//            builder.append("${value.note} * ${value.numberOfNotes} = $t \n")

            if (value.numberOfNotes != 0) {
                val t = value.numberOfNotes * value.note.toInt()
                builder.append("${value.note} * ${value.numberOfNotes} = $t \n")

            }


        }

        binding.txtTrasnsactionHistory.text = builder.toString()

    }
    private fun calculationForAvailableNotes(
        amount: Int, updateNotes: Boolean = false
    ): ArrayList<Notes> {

        val notes = ArrayList<Notes>()
        val notesList = viewModel!!.getAllNotes()
        val resultsList = ArrayList<Notes>()
        var total = amount

        val it = notesList.filter {
            it.numberOfNotes != 0
        }
        for ((index, value) in it.withIndex()!!) {
            val note = Math.floor((total / value.note.toInt()).toDouble()).toInt()
            if (note <= value.numberOfNotes) {
                notes.add(Notes(value.note, note))
                if (updateNotes) {
                    val it = value.numberOfNotes - note
                    viewModel!!.updateNotes(it, value.note)

                }
            }
            total = amount % value.note.toInt()
        }
        total = amount
        for ((index, value) in notes.withIndex()) {
            val note = Math.floor((total / value.note.toInt()).toDouble()).toInt()
            resultsList.add(Notes(value.note, note))
            total = amount % value.note.toInt()
        }
        return resultsList
    }


}