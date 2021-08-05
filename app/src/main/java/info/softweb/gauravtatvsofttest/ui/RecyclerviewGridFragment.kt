package info.softweb.gauravtatvsofttest.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.softweb.gauravtatvsofttest.R
import info.softweb.gauravtatvsofttest.databinding.FragmentRecyclerviewGridBinding

class RecyclerviewGridFragment : Fragment(), View.OnClickListener {
    private var numGrid: Int=0
    private lateinit var edtNumber: EditText
    private lateinit var btn: Button
    private lateinit var binding: FragmentRecyclerviewGridBinding
    private lateinit var recyclerView: RecyclerView
    private var arrayList=ArrayList<Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_recyclerview_grid,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        recyclerView=binding.recyclerView
        btn=binding.btnSubmit
        edtNumber=binding.editTextTextPersonName
        btn.setOnClickListener(this)
     /*   recyclerView.layoutManager = GridLayoutManager(this.requireActivity(),numGrid)
        //creating our adapter
        val adapter = GridrecyclerviewAdapter(numGrid)

        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter */
    }

    override fun onClick(v: View?) {
        when(v)
        {
            btn ->{
               if(TextUtils.isEmpty(edtNumber.text.toString().trim()))
               {
                   Toast.makeText(this.requireContext(),getString(R.string.enter_number),Toast.LENGTH_LONG).show()
               }
                else{
                   val tempNumber= Math.sqrt(edtNumber.text.toString().trim().toDouble())
                   val chars=tempNumber.toString().split(".")
                   if(chars[1].length==1 && chars[1]=="0")
                   {
                    numGrid=chars[1].toInt()
                   }
                }

            }
        }
    }

    fun checkPrime(number:Int) : Int
    {
        var i: Int
        var m = 0
        var flag = 0
        val n = number //it is the number to be checked

        m = n / 2
        if (n == 0 || n == 1) {
            println("$n is not prime number")
            flag=2
        } else {
            i = 2
            while (i <= m) {
                if (n % i == 0) {
                    println("$n is not prime number")
                    flag = 1
                }
                i++
            }
            if (flag == 0) {
                println("$n is prime number")
            }
        } //end of else
        return flag
    }
}