package com.roque.meuingresso

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.platform.app.InstrumentationRegistry
import com.roque.meuingresso.ui.features.main.MainScreen
import com.roque.meuingresso.ui.navigation.NavGraph
import com.roque.meuingresso.ui.navigation.Screen
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun app_starts_at_movies_screen_and_shows_toolbar() {
        composeTestRule.setContent {
            MainScreen()
        }

        composeTestRule
            .onAllNodesWithText("Filmes", useUnmergedTree = true)
            .onFirst()
            .assertExists()

        val nodes = composeTestRule.onAllNodesWithText("Filmes", useUnmergedTree = true)
        nodes.fetchSemanticsNodes().let {
            assert(it.size >= 2) { "Deveria encontrar o texto Filmes na Toolbar e na BottomNav" }
        }
    }

    @Test
    fun verify_navigation_to_details_screen() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val navController = TestNavHostController(context).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
        }

        composeTestRule.setContent {
            NavGraph(navController = navController)
        }

        composeTestRule.waitForIdle()

        assertEquals(
            Screen.MovieList.route,
            navController.currentBackStackEntry?.destination?.route
        )
    }
}