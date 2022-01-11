package com.zero.pecodetest_devnestor.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import com.zero.pecodetest_devnestor.R
import androidx.fragment.app.commit
import com.zero.pecodetest_devnestor.ui.main.MainActivity

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    var viewBinding: T? = null

    protected lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = onCreate(inflater, container)
        return viewBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setFocus(view)
        observe()
        observeError()
    }

    abstract fun onCreate(i: LayoutInflater, c: ViewGroup?): T
    abstract fun initialize()

    private fun setFocus(view: View) {
        view.apply {
            isFocusableInTouchMode = true
            requestFocus()
        }
    }

    override fun onAttach(context: Context) {
        mainActivity = (requireActivity() as MainActivity)
        super.onAttach(context)
    }

    private fun observeError() {
    }

    protected open fun observe() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    fun addFragment(
        fragment: Fragment,
        addBackStack: Boolean = true,
        forceAddBackStack: Boolean = false,
        @IdRes id: Int? = mainActivity.parentLayoutId,
        tag: String = fragment.hashCode().toString()
    ) {
        if (id != null) {
            activity?.supportFragmentManager?.commit(allowStateLoss = true) {
                if (addBackStack && (forceAddBackStack || !fragment.isAdded)) addToBackStack(tag)
                setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_rigft
                )
                replace(id, fragment)
            }
        }
    }
    fun addAlertFragment(
        fragment: Fragment,
        addBackStack: Boolean = true,
        @IdRes id: Int? = mainActivity.parentLayoutId,
        tag: String = fragment.hashCode().toString()
    ) {
        if (id != null) {
            activity?.supportFragmentManager?.commit(allowStateLoss = true) {
                if (addBackStack && !fragment.isAdded) addToBackStack(tag)
                setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_rigft
                )
                add(id, fragment)
            }
        }
    }

    fun replaceFragment(fragment: Fragment, @IdRes id: Int? = mainActivity.parentLayoutId) {
        if (id != null) {
            activity?.supportFragmentManager?.commit(allowStateLoss = true) {
                replace(id, fragment)
            }
        }
    }

    fun finishFragment() {
        activity?.supportFragmentManager?.popBackStackImmediate()
    }
}



fun FragmentActivity.initialFragment(fragment: Fragment, @IdRes containerId: Int) {
    //val containerId = ViewModelProviders.of(this)[BaseViewModel::class.java].parentLayoutId
    supportFragmentManager.commit(allowStateLoss = true) {
        replace(containerId, fragment)
    }
}

fun FragmentActivity.finishFragment() {
    supportFragmentManager.popBackStack()
}