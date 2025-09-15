package pe.com.master.machines.conversorDivisa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.com.master.machines.design.utils.Utils.backEnterTransition
import pe.com.master.machines.design.utils.Utils.backExitTransition
import pe.com.master.machines.design.utils.Utils.forwardEnterTransition
import pe.com.master.machines.design.utils.Utils.forwardExitTransition
import pe.com.master.machines.home.navigation.HomeRoute
import pe.com.master.machines.home.screen.HomeScreen
import pe.com.master.machines.login.navigation.LoginRoute
import pe.com.master.machines.login.screen.LoginScreen
import pe.com.master.machines.register.navigation.RegisterRoute
import pe.com.master.machines.register.screen.RegisterUserScreen

@Composable
fun NavigationMainWrapper(
    modifier: Modifier = Modifier
) {
    
    val navController = rememberNavController()
    
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeRoute,//LoginRoute,
        enterTransition = { forwardEnterTransition },
        exitTransition = { forwardExitTransition },
        popEnterTransition = { backEnterTransition },
        popExitTransition = { backExitTransition }
    ) {
        
        composable<LoginRoute> {
            LoginScreen(
                navigateToRegister = {
                    navController.navigate(RegisterRoute)
                },
                navigateToHome = {
                    navController.navigate(HomeRoute) {
                        popUpTo<LoginRoute> { inclusive = true }
                        launchSingleTop = true
                    }
                },
            )
        }
        
        composable<RegisterRoute> {
            RegisterUserScreen(
                navigateToBack = {
                    navController.navigateUp()
                },
                navigateToHome = {
                    navController.navigate(HomeRoute) {
                        popUpTo<LoginRoute> { inclusive = true }
                        launchSingleTop = true
                    }
                },
            )
        }
        
        composable<HomeRoute> {
            HomeScreen(
                navigateToLogin = {
                    navController.navigate(LoginRoute) {
                        popUpTo<HomeRoute> { inclusive = true }
                        launchSingleTop = true
                    }
                },
            )
        }
    }
    
}