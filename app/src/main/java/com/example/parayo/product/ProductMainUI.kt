package com.example.parayo.product

import android.view.Gravity
import android.view.Menu.NONE
import android.view.MenuItem
import android.view.MenuItem.SHOW_AS_ACTION_ALWAYS
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.parayo.R
import com.example.parayo.common.Prefs
import com.example.parayo.signin.SigninActivity
import com.example.parayo.view.borderBottom
import com.google.android.material.navigation.NavigationView
import org.jetbrains.anko.*
import org.jetbrains.anko.design._BottomNavigationView
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.support.v4.drawerLayout

class ProductMainUI(
    private val viewModel: ProductMainViewModel
) : AnkoComponent<ProductMainActivity>,
    NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: Toolbar
    lateinit var navigationView: NavigationView

    override fun createView(ui: AnkoContext<ProductMainActivity>) = ui.drawerLayout {
        drawerLayout = this

        verticalLayout {
            toolbar {
                title = "Parayo"
                menu.add("Search")
                    .setIcon(R.drawable.ic_search)
                    .setShowAsAction(SHOW_AS_ACTION_ALWAYS)
            }.lparams(matchParent, wrapContent)
        }.lparams(matchParent, matchParent)

        navigationView = navigationView {
            ProductMainNavHeader()
                .createView(AnkoContext.create(context, this))
                .run(::addHeaderView)

            menu.apply {
                add(NONE, MENU_ID_INQUIRY, NONE, "내 문의").apply {
                    setIcon(R.drawable.ic_chat)
                }
                add(NONE, MENU_ID_LOGOUT, NONE, "로그아웃").apply {
                    setIcon(R.drawable.ic_signout)
                }
            }

            setNavigationItemSelectedListener(this@ProductMainUI)
        }.lparams(wrapContent, matchParent) {
            gravity = Gravity.START
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            MENU_ID_INQUIRY -> {
                viewModel.toast("내 문의")
            }
            MENU_ID_LOGOUT -> {
                Prefs.token = null
                Prefs.refreshToken = null
                viewModel.startActivityAndFinish<SigninActivity>()
            }
        }

        drawerLayout.closeDrawer(navigationView)

        return true
    }

    companion object {
        private const val MENU_ID_INQUIRY = 1
        private const val MENU_ID_LOGOUT = 2
    }
}