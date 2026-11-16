package xyz.eliothmonroy.placeholdersocial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.eliothmonroy.placeholdersocial.ui.theme.PlaceHolderSocialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Composable
fun SectionHeader(
    modifier: Modifier = Modifier,
    showActionButton: Boolean = false
) {
    Row(
        modifier = modifier
            .height(48.dp)
            .padding(start = 16.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleMedium,
            text = "Text"
        )
        if (showActionButton) {
            TextButton(
                modifier = Modifier.height(40.dp),
                onClick = {},
            ) {
                Text(text = "See all")
            }
        }
    }
}

@Composable
fun UserFilterItem(
    modifier: Modifier = Modifier,
    name: String? = null,
    isSelected: Boolean = false
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .then(
                    if (isSelected) Modifier.border(
                        width = 2.dp,
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primary
                    ) else Modifier
                ),
            contentAlignment = Alignment.Center
        ) {
            if (name == null) {
                Image(
                    modifier = Modifier.size(24.dp).clip(CircleShape),
                    imageVector = Icons.Default.Group,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurfaceVariant)
                )
            } else {
                Text(
                    text = name.first().toString(),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        Text(
            modifier = Modifier.paddingFromBaseline(top = 4.dp),
            text = name ?: "All")
    }

}

@Composable
fun App(modifier: Modifier = Modifier) {
    PlaceHolderSocialTheme {
        Scaffold(
            modifier = modifier,
            topBar = { TopBarCustom() },
            bottomBar = { BottomNavBar() },
            floatingActionButton = { FloatingActionButton() },
            floatingActionButtonPosition = FabPosition.End,
        ) { _ ->

        }
    }
}

@Composable
fun TopBarCustom(modifier: Modifier = Modifier) {
    // Custom top app bar
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(start = 16.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleLarge,
            text = "Feed"
        )
        IconButton(
            onClick = {}
        ) {
            Image(imageVector = Icons.Outlined.Search, contentDescription = null)
        }

        IconButton(
            onClick = {}
        ) {
            Icon(imageVector = Icons.Outlined.Notifications, contentDescription = null)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    // Material design
    TopAppBar(
        modifier = modifier,
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
        title = {
            Text(
                text = "Title",
            )
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
            }
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Outlined.Notifications, contentDescription = null)
            }
        }
    )
}

@Composable
fun BottomNavBar(modifier: Modifier = Modifier) {
    BottomAppBar(
        modifier = modifier,
    ) {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = null)
            },
            label = {
                Text("Feed")
            },
            alwaysShowLabel = true,
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(imageVector = Icons.Outlined.CheckCircle, contentDescription = null)
            },
            label = {
                Text("Todos")
            },
            alwaysShowLabel = true,
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(imageVector = Icons.Outlined.PhotoLibrary, contentDescription = null)
            },
            label = {
                Text("Albums")
            },
            alwaysShowLabel = true,
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(imageVector = Icons.Outlined.Person, contentDescription = null)
            },
            label = {
                Text("Profile")
            },
            alwaysShowLabel = true,
        )
    }
}

@Composable
fun FloatingActionButton(modifier: Modifier = Modifier) {
    FloatingActionButton(
        modifier = modifier,
        onClick = {},
        shape = MaterialTheme.shapes.large,
        elevation = FloatingActionButtonDefaults.elevation(6.dp)
    ) {
        Icon(imageVector = Icons.Default.Edit, contentDescription = null)
    }
}


// Previews

@Preview(showBackground = true, widthDp = screenWidth)
@Composable
private fun SectionHeaderPreview() {
    PlaceHolderSocialTheme {
        SectionHeader(showActionButton = true)
    }
}

@Preview(showBackground = true, widthDp = screenWidth)
@Composable
private fun TopBarPreview() {
    PlaceHolderSocialTheme {
        TopBar()
    }
}

@Preview(showBackground = true, widthDp = screenWidth)
@Composable
private fun TopBarCustomPreview() {
    PlaceHolderSocialTheme {
        TopBarCustom()
    }
}

@Preview(showBackground = true, widthDp = screenWidth)
@Composable
private fun BottomNavBarPrev() {
    PlaceHolderSocialTheme {
        BottomNavBar()
    }
}

@Preview(showBackground = true, widthDp = screenWidth, heightDp = screenHeight)
@Composable
private fun AppPreview() {
    App()
}

@Preview(showBackground = true)
@Composable
private fun FloatingActionButtonPreview() {
    PlaceHolderSocialTheme {
        FloatingActionButton()
    }
}

@Preview(showBackground = true)
@Composable
private fun UserFilterItemPreview() {
    PlaceHolderSocialTheme {
        UserFilterItem(name = "Elioth", isSelected = true)
    }
}

// Constants
const val screenWidth = 360
const val screenHeight = 800