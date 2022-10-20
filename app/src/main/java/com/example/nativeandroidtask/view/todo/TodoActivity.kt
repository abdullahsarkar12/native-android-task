package com.example.nativeandroidtask.view.todo
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.nativeandroidtask.R
import kotlinx.android.synthetic.main.activity_main.*


class TodoActivity : AppCompatActivity() {

    private lateinit var viewModel: TodoViewModel
    private lateinit var todoAdapter: TodoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        inItUserSpinner()

        initTodoAdapter()

        observeViewModel()
    }

    private fun inItUserSpinner() {
        spinnerUser.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val user = viewModel.userList.value?.get(position)
                Toast.makeText(this@TodoActivity, user?.id.toString(), Toast.LENGTH_LONG).show()
                todoAdapter.filter.filter(user?.id.toString())
            }
        }
    }

    private fun initTodoAdapter() {
        todoAdapter = TodoAdapter(this)
        rvTodo.adapter = todoAdapter
    }

    private fun observeViewModel() {
        viewModel.getUserList()

        viewModel.getTodoList()

        viewModel.userList.observe(this){
            spinnerUser.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,it)
        }

        viewModel.todoList.observe(this){
            todoAdapter.onDataChange(it)
        }
    }
}
