package com.architecture.clean.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.architecture.clean.R
import com.architecture.clean.domain.model.FoodDto
import com.architecture.clean.ui.home.adapter.HomeAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject
import android.util.Pair as UtilPair


class HomeFragment : DaggerFragment() {
    private val TAG: String = HomeFragment::class.java.simpleName

    companion object {
        val FRAGMENT_NAME: String = HomeFragment::class.java.name
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }
    val adapter: HomeAdapter by lazy { HomeAdapter(arrayListOf()) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            homeData.observe(this@HomeFragment, Observer {
                initView(it)
            }
            )
            error.observe(this@HomeFragment, Observer {
                progressBar_home.visibility = View.GONE
                Toast.makeText(context, "${it?.message}", Toast.LENGTH_LONG).show()
            })

            foodsCount.observe(this@HomeFragment, Observer {
                Toast.makeText(context, "you have $it foods in your dataBase !!", Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun initView(it: FoodDto?) {
        rv_main_home.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_main_home.adapter = adapter
        progressBar_home.visibility = View.GONE
        if (it!!.results.isNotEmpty()) {
            adapter.clear()
            adapter.add(it.results)

        } else {
            Toast.makeText(context, context?.getString(R.string.empty_list), android.widget.Toast.LENGTH_LONG).show()
        }
    }


}