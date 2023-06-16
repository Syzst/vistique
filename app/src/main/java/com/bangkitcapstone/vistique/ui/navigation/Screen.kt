package com.bangkitcapstone.vistique.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Marketplace: Screen("marketplace")
    object Scan: Screen("scan")
    object Explore: Screen("explore")
//    object Store: Screen("store")
}
