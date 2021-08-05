package info.softweb.gauravtatvsofttest.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.softweb.gauravtatvsofttest.R
import info.softweb.gauravtatvsofttest.adapters.UserrecyclerviewAdapter
import info.softweb.gauravtatvsofttest.databinding.FragmentUserListBinding
import info.softweb.gauravtatvsofttest.factory.UserViewModelFactory
import info.softweb.gauravtatvsofttest.model.Data
import info.softweb.gauravtatvsofttest.network.UsersApi
import info.softweb.gauravtatvsofttest.repositories.UserRepository
import info.softweb.gauravtatvsofttest.viewmodels.UserViewModel


class UserListFragment : Fragment() {

    private lateinit var mLinearLayoutManager: LinearLayoutManager
    private var pastVisiblesItems: Int=0
    private var totalItemCount: Int=0
    private var visibleItemCount: Int=0
    lateinit var viewModel: UserViewModel
    private lateinit var bindingFragment:FragmentUserListBinding
    private lateinit var adapter:UserrecyclerviewAdapter
    private var userList=ArrayList<Data>()
    private var loading:Boolean=true
    private var page=1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFragment = DataBindingUtil.inflate(inflater,R.layout.fragment_user_list,container,false)
        return bindingFragment.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callApi()
    }

    private fun callApi() {
      viewModel.getAllUsers(page)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retrofitService = UsersApi.getInstance()
        val mainRepository = UserRepository(retrofitService)
        mLinearLayoutManager= LinearLayoutManager(this.requireContext())
        bindingFragment.recyclerview.layoutManager =mLinearLayoutManager
        //creating our adapter

        adapter = UserrecyclerviewAdapter(this.requireContext(),userList)
        bindingFragment.recyclerview.adapter = adapter


        viewModel = ViewModelProvider(this, UserViewModelFactory(mainRepository)).get(UserViewModel::class.java)


        viewModel.userList.observe(viewLifecycleOwner) {
            it.data?.let {
                adapter.updateData(it as ArrayList)
            }
        }

        bindingFragment.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    loading = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    Log.e("test", "reached the last element of recyclerview")
                    visibleItemCount = mLinearLayoutManager.getChildCount()
                    totalItemCount = mLinearLayoutManager.getItemCount()
                    pastVisiblesItems = mLinearLayoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            loading = false
                            page++
                          callApi()
                        }
                    }
                }
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(this.requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                bindingFragment.progressBar.visibility = View.VISIBLE
            } else {
                bindingFragment.progressBar.visibility = View.GONE
            }
        })

    }

}