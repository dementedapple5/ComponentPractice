package com.example.danie.componentpractice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mylabelview.MyLabelView
import com.example.mysearchbar.MySearchBar
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), MySearchBar.OnSearchButtonClickedListener {
    override fun onSearchButtonClickedListener(source: MySearchBar, currentText: String) {
        Toast.makeText(this, "Searching for: "+currentText, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mySearchBar: MySearchBar = this.findViewById(R.id.mSearchBar)
        mySearchBar.setHintText("Search..")
        mySearchBar.setTextSize(12)


    }



}
