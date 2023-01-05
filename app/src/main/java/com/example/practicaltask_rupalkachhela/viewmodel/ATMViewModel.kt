package com.example.practicaltask_rupalkachhela.viewmodel

import androidx.lifecycle.ViewModel
import com.example.practicaltask_rupalkachhela.model.AddAmount
import com.example.practicaltask_rupalkachhela.model.Notes
import com.example.practicaltask_rupalkachhela.model.WithdrawAmount
import com.example.practicaltask_rupalkachhela.roomdb.ATMDao
import javax.inject.Inject

class ATMViewModel @Inject constructor(private val atmDao: ATMDao): ViewModel() {
    var amount: String = ""

    fun addAmount(addAmount:  AddAmount) {
        atmDao.addAmount(addAmount)
    }

    fun getTotalBalance(): Int {
        return atmDao.getTotalBalance()
    }


    fun getTotalWithdrawAmount(): Int {
        return atmDao.getTotalWithdrawAmount()
    }


    fun insertWithDrawAmount(withdrawAmount:  WithdrawAmount) {
        atmDao.insertWithDrawAmount(withdrawAmount)
    }

    fun insertNotes(notes: Notes) {
        atmDao.insertNotes(notes)
    }

    fun getAllNotes(): List<Notes> {
        return atmDao.getAllNotes()
    }

    fun updateNotes(numberOfNotes: Int, notes: String) {
        atmDao.updateNotes(numberOfNotes,notes)
    }

    fun getTransactionList(): List<WithdrawAmount> {
        return atmDao.getTransactionList()
    }


}