package hr.kotlin.zadatak_02_06

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var number1EditText: EditText
    private lateinit var number2EditText: EditText
    private lateinit var addButton: Button
    private lateinit var editButton: Button
    private lateinit var deleteButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NumberPairAdapter
    private var selectedPosition: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        number1EditText = findViewById(R.id.editNumber1)
        number2EditText = findViewById(R.id.editNumber2)
        addButton = findViewById(R.id.addButton)
        editButton = findViewById(R.id.editButton)
        deleteButton = findViewById(R.id.deleteButton)
        recyclerView = findViewById(R.id.recyclerView)

        adapter = NumberPairAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener {
            val number1 = number1EditText.text.toString().toInt()
            val number2 = number2EditText.text.toString().toInt()
            adapter.addPair(NumberPair(number1, number2))
            number1EditText.text.clear()
            number2EditText.text.clear()
            selectedPosition = -1
        }

        editButton.setOnClickListener {
            if (selectedPosition >= 0) {
                val number1 = number1EditText.text.toString().toInt()
                val number2 = number2EditText.text.toString().toInt()
                adapter.editPair(selectedPosition, NumberPair(number1, number2))
                number1EditText.text.clear()
                number2EditText.text.clear()
                selectedPosition = -1
            }
        }

        deleteButton.setOnClickListener {
            if (selectedPosition >= 0) {
                adapter.deletePair(selectedPosition)
                number1EditText.text.clear()
                number2EditText.text.clear()
                selectedPosition = -1
            }
        }

        adapter.listener = object : NumberPairAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                selectedPosition = position
                val pair = adapter.getNumberPairAtPosition(position)
                number1EditText.setText(pair.number1.toString())
                number2EditText.setText(pair.number2.toString())
            }
        }
    }
}