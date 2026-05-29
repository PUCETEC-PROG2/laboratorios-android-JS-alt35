package ec.edu.puce.githubclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ec.edu.puce.githubclient.ui.screens.RepoForm
import ec.edu.puce.githubclient.ui.screens.RepoList
import ec.edu.puce.githubclient.ui.theme.GithubClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GithubClientTheme {
                    var currentScreen by remember { mutableStateOf(value = "repoList") }
                    when (currentScreen) {
                        "repoList" -> RepoList (
                            onNavigateToForm = { currentScreen = "repoForm" }
                        )
                        "repoForm" -> RepoForm(
                            onBackClick = {currentScreen = "repoList" }
                        )
                    }
            }
        }
    }
}

