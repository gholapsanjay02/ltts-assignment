package com.example.practice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import emp.EmployeeDetails
import androidx.recyclerview.widget.RecyclerView

class Adapter (private var data: EmployeeDetails) : RecyclerView.Adapter<Adapter.MyViewHolder>()
{
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val id : TextView = view.findViewById(R.id.ID)
        val name: TextView = view.findViewById(R.id.name)
        val email: TextView = view.findViewById(R.id.email)
        val city : TextView = view.findViewById(R.id.city)
        val companyName : TextView = view.findViewById(R.id.companyName)


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data1 = data.get(position)
         holder.id.text = "id: "+data1.id.toString();
         holder.name.text ="name:  ${data1.name}"
         holder.email.text ="email:  ${data1.email}"
         holder.city.text ="city:  ${data1.address.city}"
         holder.companyName.text ="company name: ${data1.company.name}"

         holder.id.setOnClickListener { deletItem(position) }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun deletItem(position: Int)
    {
        data.removeAt(position);
        notifyDataSetChanged()

    }
}