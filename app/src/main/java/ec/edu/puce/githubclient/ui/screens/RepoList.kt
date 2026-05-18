package ec.edu.puce.githubclient.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ec.edu.puce.githubclient.ui.components.RepoItem

@Composable
fun RepoList(
    modifier: Modifier = Modifier
) {
    Column (
        modifier= modifier
    ){
        RepoItem(
            name = "Repositorio de Android ",
            description = "Repositorio creado en kotlin",
            avatarUrl = "https://wallpapers-clan.com/wp-content/uploads/2026/02/lofi-datsun-240z-palm-trees-retro-sunset-desktop-wallpaper-cover.jpg",
            language = "Kotlin"
        )
        RepoItem(
            name = "Repositorio de Django ",
            description = "Repositorio creado en Python",
            avatarUrl = "https://i.pinimg.com/736x/f6/99/3f/f6993fdb31af9cb04fcafb3f6e2fb105.jpg",
            language = "Python"
        )
        RepoItem(
            name = "Repositorio de IOs",
            description = "Repositorio creado en IOs",
            avatarUrl = "https://r1.ilikewallpaper.net/iphone-wallpapers/download/77636/Maldives-iphone-wallpaper-ilikewallpaper_com_640.jpg",
            language = "IOS"
        )
        RepoItem(
            name = "Repositorio de React",
            description = "Repositorio creado en React",
            avatarUrl = "https://i.pinimg.com/222x/9f/68/10/9f6810ef623c73253330a62eb287c1e0.jpg",
            language = "React"
        )

    }
}