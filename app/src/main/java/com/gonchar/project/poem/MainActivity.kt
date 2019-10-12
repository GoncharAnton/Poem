package com.gonchar.project.poem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener{
            button.visibility = View.INVISIBLE
            poem.text = readText()
        }
    }

    private fun readText(): CharSequence? {
        val sb = StringBuilder()
        val reader = BufferedReader(InputStreamReader(assets.open("poem.txt")))
        try {
            var mLine : String?
            mLine = reader.readLine()
            while (mLine != null) {
                sb.append(mLine + 10.toChar())
                mLine = reader.readLine()
            }
        }catch (e : IOException ){
            print(e)
        }finally {
            reader.close()
            return sb
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString("textView",poem.text.toString())
            putInt("button", button.visibility)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        button.visibility = savedInstanceState.getInt("button")
        poem.text = savedInstanceState.getString("textView")
    }
}
