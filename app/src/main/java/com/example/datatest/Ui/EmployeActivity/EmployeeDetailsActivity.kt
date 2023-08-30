package com.example.datatest.Ui.EmployeActivity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.datatest.Network.Local.*
import com.example.datatest.Network.Remote.*
import com.example.datatest.R
import de.hdodenhof.circleimageview.CircleImageView


class EmployeeDetailsActivity : AppCompatActivity(),EmployesAdapter.EmployesAdapterListener{


    private lateinit var database:EmployeeDatabase
    lateinit var viewModel:EmployeeViewModel
    lateinit var viewModelRemote: EmployeeViewModelRemote

    private  lateinit var selectedNurse: Employe
    private val retrofitService = EmployeeService.getInstance()
    private var employeeArrylist = arrayListOf<Employe>()
    private var empAdapter: EmployesAdapter? = null
    private var rv:RecyclerView?=null
    private var tvEdit:TextView?=null
    private var tvFirstNAme:EditText?=null
    private var tvLastNAme:EditText?=null
    private var tvEmail:EditText?=null
    private var delete:TextView?=null
    private var cvIv: CircleImageView?=null


    private var posEmployee = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)
        rv = findViewById(R.id.rvEmp)
        delete = findViewById(R.id.tvDelete)
        tvEdit = findViewById(R.id.tvEdit)
        tvFirstNAme = findViewById(R.id.etFirstNAme)
        tvLastNAme = findViewById(R.id.etLastNAme)
        tvEmail = findViewById(R.id.etEmail)
        cvIv = findViewById(R.id.profile_image)


        initialization()
        roomDataBaseSetup()
        viewModelSetup()
//        setRecyclerView()
    }


    private fun initialization(){
        delete?.setOnClickListener {
        viewModel.deleteEmploye(Employe(posEmployee,tvEmail?.text.toString(),tvFirstNAme?.text.toString(),tvLastNAme?.text.toString(),employeeArrylist[posEmployee].avatar))
        }
        tvEdit?.setOnClickListener {
            viewModel.updateEmploye(Employe(posEmployee,tvEmail?.text.toString(),tvFirstNAme?.text.toString(),tvLastNAme?.text.toString(),employeeArrylist[posEmployee].avatar))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("idis","it ${item.itemId}")
        if (item.itemId == R.id.refresh ) {
           viewModel.getRefreshData()
        }
        return super.onOptionsItemSelected(item)
    }



   private fun roomDataBaseSetup(){
        database=EmployeeDatabase(this)
        val repository = EmployeeRepository(database)
        val factory = EmployeViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory).get(EmployeeViewModel::class.java)
        viewModel.allnurse.observe(this){ list ->

            list.let {
                employeeArrylist= it as ArrayList<Employe>

            }
            setRecyclerView()
            updateUi(0)
      empAdapter?.notifyDataSetChanged()
        }
    }

    private fun updateUi(post:Int){
        tvFirstNAme?.setText(employeeArrylist[post].First_name)
        tvLastNAme?.setText(employeeArrylist[post].Last_name)
        tvEmail?.setText(employeeArrylist[post].Email)
        cvIv?.let {
            Glide
                .with(this)
                .load(employeeArrylist[post].avatar)
                .centerCrop()
                .into(it)
        };

    }

    private fun setRecyclerView(){
        rv?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        empAdapter = EmployesAdapter(employeeArrylist, this)
       rv?.adapter = empAdapter

        empAdapter!!.setAdapterListener(this)
    }

    private fun viewModelSetup() {
        viewModelRemote =
            ViewModelProvider(this, EmpolyeeRemoteViewmodelFactory(EmployeeRepoRemote(retrofitService),this)).get(
                EmployeeViewModelRemote::class.java
            )

        viewModelRemote.getRemoteData()

        viewModelRemote.empDataLiveData.observe(this, Observer {
            if (it.data!=null&&it.data.size>0){
                it.data.forEach {
                    viewModel.insertEmploye(Employe(it.id,it.email,it.first_name,it.last_name,it.avatar))
                }

            }

        })
    }

    override fun onItemClick(id: Int) {
        Log.d("theid","the pos $id")
        posEmployee = id
        updateUi(id-1)

    }

}