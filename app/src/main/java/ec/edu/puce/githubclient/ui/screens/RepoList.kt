package ec.edu.puce.githubclient.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ec.edu.puce.githubclient.ui.components.RepoItem
import ec.edu.puce.githubclient.viewmodels.RepoListViewModel
import androidx.compose.material3.Text

@Composable
fun RepoList(
    modifier: Modifier = Modifier,
    viewModel: RepoListViewModel = viewModel ()
) {
    val repos by viewModel.repos.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errMsg by viewModel.errMsg.collectAsState()

    Box (
        modifier= modifier
    ) {
      if (isLoading) {
          CircularProgressIndicator(
              modifier = Modifier.align(Alignment.Center)
          )
      }

      errMsg?.let {
          Text(
              text = it,
              color = MaterialTheme.colorScheme.error,
              modifier = Modifier
                  .align(Alignment.Center)
                  .padding(all = 16.dp)
          )
      }
        if (!isLoading && errMsg == null) {
            LazyColumn (
                modifier = Modifier.fillMaxWidth()
            ) {
                items(count = repos.size) { i ->
                    RepoItem(repository = repos[i])
                }
            }
        }
      }
    }

