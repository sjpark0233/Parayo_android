package com.example.parayo.product

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.parayo.R
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

class ProductMainActivity : BaseActivity<ProductMainViewModel>() {

    override val viewModelType = ProductMainViewModel::class

    private val ui by lazy {
        ProductMainUI(getViewModel())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)
        setupDrawerListener()
    }

    private fun setupDrawerListener() {
        var toggle = ActionBarDrawerToggle(
            this,
            ui.drawerLayout,
            ui.toolbar,
            R.string.open_drawer_description,
            R.string.close_drawer_description
        )

        ui.drawerLayout.addDrawerListener(toggle)

        toggle.syncState()
    }
}