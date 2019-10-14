package com.gonchar.project.poem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    /**
     * this method create instance state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //fill the TextView “poem” with content which was reading from the file
        poem.text = readText()
    }

    /**
     * this method read text from file "poem.txt"
     * this file located in assets folder
     */
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

    /**
     * this method used for save instance state.
     * It make possible restore instance state if it was destroyed
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString("textView",poem.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    /**
     * this method restore instance state if it was destroyed
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        poem.text = savedInstanceState.getString("textView")
    }
}
