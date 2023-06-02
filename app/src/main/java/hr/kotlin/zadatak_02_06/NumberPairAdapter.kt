package hr.kotlin.zadatak_02_06

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NumberPairAdapter : RecyclerView.Adapter<NumberPairAdapter.NumberPairViewHolder>() {

    private val numberPairs = mutableListOf<NumberPair>()
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    var listener: OnItemClickListener? = null

    fun addPair(pair: NumberPair) {
        numberPairs.add(pair)
        notifyItemInserted(numberPairs.size - 1)
    }

    fun editPair(position: Int, pair: NumberPair) {
        numberPairs[position] = pair
        notifyItemChanged(position)
    }

    fun deletePair(position: Int) {
        numberPairs.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getNumberPairAtPosition(position: Int): NumberPair {
        return numberPairs[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberPairViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return NumberPairViewHolder(view)
    }

    override fun onBindViewHolder(holder: NumberPairViewHolder, position: Int) {
        holder.number1.text = numberPairs[position].number1.toString()
        holder.number2.text = numberPairs[position].number2.toString()
        holder.itemView.setOnClickListener {
            listener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return numberPairs.size
    }

    class NumberPairViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val number1: TextView = view.findViewById(R.id.number1)
        val number2: TextView = view.findViewById(R.id.number2)
    }
}