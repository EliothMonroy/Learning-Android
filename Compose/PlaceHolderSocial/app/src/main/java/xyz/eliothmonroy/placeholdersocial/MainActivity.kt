package xyz.eliothmonroy.placeholdersocial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.eliothmonroy.placeholdersocial.ui.theme.PlaceHolderSocialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaceHolderSocialTheme {
            }
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


// Previews

@Preview(showBackground = true, widthDp = screenWidth)
@Composable
private fun TopBarPreview() {
    PlaceHolderSocialTheme {
        TopBar()
    }
}

@Preview(showBackground = true, widthDp = screenHeight)
@Composable
private fun TopBarCustomPreview() {
    PlaceHolderSocialTheme {
        TopBarCustom()
    }
}

// Constants
const val screenWidth = 360
const val screenHeight = 800