package com.example.my.randommaizer.name_number

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my.randommaizer.R
import kotlin.random.Random

class Name : AppCompatActivity() {

    private val names = mutableListOf<String>()

    private lateinit var adapter: NamesAdapter
    private lateinit var etName: EditText
    private lateinit var btnAddName: ImageButton
    private lateinit var btnRandom: ImageButton
    private lateinit var textResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)
        etName = findViewById(R.id.etName)
        btnAddName = findViewById(R.id.btnAddName)
        btnRandom = findViewById(R.id.btnRandom)
        textResult = findViewById(R.id.txtResult)

        adapter = NamesAdapter(names)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        btnAddName.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.button_click)
            btnAddName.startAnimation(animation)
            addName()
        }

        btnRandom.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.button_click)
            btnRandom.startAnimation(animation)
            selectRandomName()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addName() {
        val name = etName.text.toString()
        if (name.isNotEmpty()) {
            names.add(name)
            adapter.notifyDataSetChanged()
            etName.text.clear()
            etName.clearFocus()
        } else {
            Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun selectRandomName() {
        if (names.isEmpty()) {
            Toast.makeText(this,"Введите имя",Toast.LENGTH_SHORT).show()
            return
        }
        val randomIndex= Random.nextInt(names.size)
        val randomName=names[randomIndex]
        textResult.text = randomName
        names.removeAt(randomIndex)
        adapter.notifyDataSetChanged()
    }
}

class NamesAdapter(private val names: List<String>) :
    RecyclerView.Adapter<NamesAdapter.NameViewHolder>() {

    inner class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: TextView = itemView.findViewById(R.id.textName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_name, parent, false)
        return NameViewHolder(view)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val name = names[position]
        holder.textName.text = name
    }

}