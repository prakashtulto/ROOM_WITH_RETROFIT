package com.example.datatest.Ui.EmployeActivity

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.datatest.Network.Local.Employe
import com.example.datatest.R

class EmployesAdapter(
    val result: ArrayList<Employe>,
    val context: Context,

    ) : RecyclerView.Adapter<InjuryAdapterViewHolder>() {

    lateinit var employesAdapterListner: EmployesAdapterListener

    interface EmployesAdapterListener {
        fun onItemClick(id:Int)
    }
    fun setAdapterListener(employesAdapterListner: EmployesAdapterListener) {
        this.employesAdapterListner = employesAdapterListner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InjuryAdapterViewHolder {
        Log.d("revdvdvd","running")
        return InjuryAdapterViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_text,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: InjuryAdapterViewHolder, position: Int) {
        Log.d("revdvdvd","running")
        var item = result[position]
        holder.tvEmp.text =item.First_name
        holder.tvEmp.setOnClickListener { item.id?.let { it1 ->
            employesAdapterListner.onItemClick(
                it1
            )
        } }

    }

    override fun getItemCount(): Int {
     return result.size
    }


}

class InjuryAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvEmp: TextView = view.findViewById(R.id.tvEmp)

}