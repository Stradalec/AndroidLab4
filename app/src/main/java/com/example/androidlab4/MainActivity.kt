package com.example.androidlab4

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(), CellClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        var recyclerView: RecyclerView = findViewById(R.id.rView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        var limit = 0;
        var colorList:ArrayList<ColorData> = ArrayList()
        colorList.add(ColorData("Red", "#FF0000"))
        colorList.add(ColorData("Dark Green", "#038d00"))
        colorList.add(ColorData("Orange", "#ff8d00"))
        colorList.add(ColorData("Yellow", "#ffff00"))
        colorList.add(ColorData("Gray", "#212500"))
        colorList.add(ColorData("Blue", "#2125f0"))
        colorList.add(ColorData("Violet", "#451048"))
        colorList.add(ColorData("Black", "#000000"))
        while (limit < 10) {
            AddRandomColor(colorList)
            ++limit
        }


        var adapter = MyAdapter(this, colorList, this)
        recyclerView.adapter = adapter
    }

    override fun onCellClickListener(inputColor: String) {
        if (inputColor == "Violet") {
            Toast.makeText(this, "Let's Be Friends", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this, "IT’S $inputColor", Toast.LENGTH_SHORT).show()
        }

    }
}

interface CellClickListener{
    fun  onCellClickListener(inputColor: String)
}
private fun AddRandomColor(array: ArrayList<ColorData>){
    var  limit = array.size
    var random = (0..limit).random()
    array.add(array[random])
}

data class ColorData(
    val colorName: String,
    val colorHex: String
)
class MyAdapter(private val context: Context, private val list: ArrayList<ColorData>, private  val cellClickListener: CellClickListener) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var picture: View? = null;
        var smallTextView: TextView? = null;
        init {
            picture = itemView.findViewById(R.id.picture)
            smallTextView = itemView.findViewById(R.id.text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rview_item,parent,false)
        return  ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val colorData = list[position]
        holder.smallTextView?.text = colorData.colorName
        holder.picture?.setBackgroundColor(android.graphics.Color.parseColor(colorData.colorHex))
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(colorData.colorName)
        }
    }
}

