package com.geekbrains.tests.presenter.details

import com.geekbrains.tests.view.ViewContract
import com.geekbrains.tests.view.details.ViewDetailsContract
import com.geekbrains.tests.view.search.ViewSearchContract

class DetailsPresenter internal constructor(
     var count: Int = 0
) : PresenterDetailsContract {

    private var viewContract: ViewDetailsContract? = null

    override fun setCounter(count: Int) {
        this.count = count
    }

    override fun onIncrement() {
        count++
        viewContract?.setCount(count)
    }

    override fun onDecrement() {
        count--
        viewContract?.setCount(count)
    }

    override fun getview(): ViewDetailsContract? {
        return this.viewContract
    }

    override fun onAttach(view: ViewContract) {
        this.viewContract = view as ViewDetailsContract

    }

    override fun onDetach() {
        this.viewContract = null
    }




}
