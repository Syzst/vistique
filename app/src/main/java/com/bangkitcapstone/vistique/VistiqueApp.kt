package com.bangkitcapstone.vistique

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bangkitcapstone.vistique.ui.components.MySearchBar
import com.bangkitcapstone.vistique.ui.navigation.NavigationItem
import com.bangkitcapstone.vistique.ui.navigation.Screen
import com.bangkitcapstone.vistique.ui.screen.camera.CameraScreen
import com.bangkitcapstone.vistique.ui.screen.explore.ExploreScreen
import com.bangkitcapstone.vistique.ui.screen.login.LoginActivity
import com.bangkitcapstone.vistique.ui.screen.marketplace.MarketplaceScreen
import com.bangkitcapstone.vistique.ui.screen.profile.ProfileActivity
import com.bangkitcapstone.vistique.ui.screen.welcome.WelcomeActivity
import com.bangkitcapstone.vistique.ui.theme.Shapes
import com.bangkitcapstone.vistique.ui.theme.VistiqueTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun VistiqueApp(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
){
    lateinit var auth: FirebaseAuth
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val context = (LocalContext.current as? Activity)

    auth = Firebase.auth

    if (auth.currentUser == null){
        context?.startActivity(Intent(context, WelcomeActivity::class.java))
        context?.finishAffinity()
    }

    Scaffold(
        topBar = {
            if (currentRoute == "marketplace" || currentRoute == "explore") {
                VisHeaderSearch()
            } else {
                VisHeader()
            }
        },
        bottomBar = { MyBottomBar(navController = navHostController)}
    ) { innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Scan.route) {
                CameraScreen()
            }
            composable(Screen.Explore.route) {
                ExploreScreen()
            }
            composable(Screen.Marketplace.route) {
                MarketplaceScreen()
            }
//            composable(Screen.Store.route){
//                StoreScreen()
//            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun VistiqueAppPreview(){
    VistiqueTheme {
        VistiqueApp( )
    }
}

@Composable
fun MyBottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        backgroundColor = Color.Transparent,
        modifier = modifier
            .padding(16.dp)
            .clip(Shapes.large)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "Scan",
                icon = Icons.Default.FilterCenterFocus,
                screen = Screen.Scan
            ),
            NavigationItem(
                title = "Explore",
                icon = Icons.Default.Explore,
                screen = Screen.Explore
            ),
            NavigationItem(
                title = "Marketplace",
                icon = Icons.Default.ShoppingCart,
                screen = Screen.Marketplace
            ),
//            NavigationItem(
//                title = "Store",
//                icon = Icons.Default.Store,
//                screen = Screen.Store
//            )
        )
        BottomNavigation(
            backgroundColor = Color.White,
            contentColor = MaterialTheme.colors.primary
        ) {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    })
            }
        }
    }
}

@Composable
fun VisHeader(
    modifier: Modifier = Modifier
) {
    val context = (LocalContext.current as? Activity)

    Surface(
        color =  Color.Transparent,
        modifier = modifier
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_text),
                    contentDescription = "",
                    Modifier
                        .height(45.dp)
                        .width(153.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = {context?.startActivity(
                        Intent(context, ProfileActivity::class.java)
                    )}
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        modifier = modifier
                            .size(45.dp),
                        tint = MaterialTheme.colors.primary,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

@Composable
fun VisHeaderSearch(
    modifier: Modifier = Modifier
) {
    val context = (LocalContext.current as? Activity)

    Surface(
        color =  Color.Transparent,
        modifier = modifier
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "",
                    Modifier.size(45.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                MySearchBar()
            }
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = {context?.startActivity(
                        Intent(context, ProfileActivity::class.java)
                    )}
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        modifier = modifier
                            .size(45.dp),
                        tint = MaterialTheme.colors.primary,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}