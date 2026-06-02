package ec.edu.puce.githubclient.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter
import ec.edu.puce.githubclient.ui.theme.GithubClientTheme
import ec.edu.puce.githubclient.viewmodels.RepoFormViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoForm(
    onBackClick: () -> Unit = {},
    onSaveSuccess: () -> Unit = {},
    viewModel: RepoFormViewModel = viewModel ()
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val isSuccess by viewModel.isSuccess.collectAsState()
    val errMsg by viewModel.errMsg.collectAsState()

    var name by remember { mutableStateOf(value = "") }
    var description by remember { mutableStateOf(value = "") }

    LaunchedEffect(key1 = isSuccess) {
        if(isSuccess) {
            onSaveSuccess()
            viewModel.resetSuccess()
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Formulario as repositorio") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Regresar",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }

    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "nombre del repositorio") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(height = 12.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(text = "descripcion del repositorio") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 5
                )

                if (!errMsg.isNullOrBlank()) {
                    Spacer(modifier = Modifier.height(height = 48.dp))
                    Text(
                        text = errMsg!!,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }


                Spacer(modifier = Modifier.height(height = 48.dp))
                Button(
                    onClick = { viewModel.createRepository(name, description) },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Guardar"
                    )
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    Text(text = "Guardar")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepoFormPreview() {
    GithubClientTheme () {
        RepoForm()
    }
}