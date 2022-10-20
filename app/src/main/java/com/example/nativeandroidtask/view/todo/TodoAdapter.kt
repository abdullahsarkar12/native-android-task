package com.example.nativeandroidtask.view.todo
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nativeandroidtask.R
import com.example.nativeandroidtask.data.model.Todo
import java.util.*
import kotlin.CharSequence
import kotlin.Int
import kotlin.toString

class TodoAdapter(private val context: Context) : RecyclerView.Adapter<TodoAdapter.ViewHolder>(),
    Filterable {

    var todoList: ArrayList<Todo> = arrayListOf()
    var filterTodoList: ArrayList<Todo> = arrayListOf()


    @SuppressLint("NotifyDataSetChanged")
    fun onDataChange(list: List<Todo>) {
        todoList = list as ArrayList<Todo>
        filterTodoList = todoList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.row_todo, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = filterTodoList.get(position)
        holder.tvId.text = todo.id.toString()
        holder.tvUserId.text = todo.userId.toString()
        holder.tvTitle.text = todo.title.toString()
        holder.tvCompleted.text = todo.completed.toString()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val userId = constraint.toString()
                val list = arrayListOf<Todo>()

                if (userId.isEmpty()) {
                    filterTodoList = todoList
                } else {
                   todoList.forEach {
                       if (it.userId.toString() == userId){
                           list.add(it)
                       }
                   }
                }

                val filterResults = FilterResults()
                filterResults.values = list
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterTodoList = results?.values as ArrayList<Todo>
                notifyDataSetChanged()
            }

        }
    }

    override fun getItemCount(): Int {
        return filterTodoList.size
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvId: TextView = itemView.findViewById(R.id.tvId)
        val tvUserId: TextView = itemView.findViewById(R.id.tvUserId)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvCompleted: TextView = itemView.findViewById(R.id.tvCompleted)
    }


}