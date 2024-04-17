package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    private val myName: MyName = MyName("Anthony Boutherin")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.myName = myName

        // Less performance with findViewById in big project, because it reads all the layout everytime it called
        // findViewById<Button>(R.id.done_button).setOnClickListener {
        //    addNickname(it)
        // }
        binding.doneButton.setOnClickListener {
            addNickname(it)
        }

    }

    private fun addNickname(view: View) {

        // Best Practise to pick variables using dataBinding
        binding.apply {
            //nicknameText.text = binding.nicknameEdit.text
            myName?.nickname = nicknameEdit.text.toString()
            // Invalidate all binding expressions and request a new rebind to refresh UI (User Interface)
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        // Hide the Keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
