package xyz.eliothmonroy.placeholdersocial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
    title: String,
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
            text = title
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
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
                .then(
                    if (isSelected) Modifier.border(
                        width = 2.dp,
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primary
                    ) else Modifier
                ),
            contentAlignment = Alignment.Center,
        ) {
            if (name == null) {
                Image(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape),
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
            text = name ?: "All"
        )
    }
}

@Composable
fun UserFilterRow(modifier: Modifier = Modifier, items: List<UserFilter>) {
    LazyRow(
        modifier = modifier.height(84.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(items = items) { item ->
            UserFilterItem(name = item.userName, isSelected = true)
        }
    }
}

@Composable
fun UserFilterSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        SectionHeader(title = "People", showActionButton = true)
        UserFilterRow(items = userFilterList)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun CommentsChip(
    modifier: Modifier = Modifier,
    numberOfComments: Int = 0
) {
    AssistChip(
        modifier = modifier,
        onClick = {},
        label = {
            Text(
                text = "$numberOfComments comments",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.ChatBubbleOutline, contentDescription = null)
        },
        shape = ShapeDefaults.Small,
        colors = AssistChipDefaults.assistChipColors(containerColor = Color.Transparent)
    )
}

@Composable
fun Bookmark(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    IconToggleButton(
        modifier = modifier.size(48.dp),
        checked = isSelected,
        onCheckedChange = {}
    ) {
        Icon(
            imageVector = if (isSelected) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
            contentDescription = null
        )
    }
}

@Composable
fun PostCardFooter(
    modifier: Modifier = Modifier,
    comments: Int = 0,
    isBookmarked: Boolean = false
) {
    Row(
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CommentsChip(numberOfComments = comments)
        Bookmark(isSelected = isBookmarked)
    }
}

@Composable
fun PostCardHeader(
    modifier: Modifier = Modifier,
    name: String,
    handle: String
) {
    Row(
        modifier = modifier
            .height(40.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = name.first().toString())
        }
        Column(modifier = Modifier.fillMaxHeight()) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "@$handle",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun PostCardBody(
    modifier: Modifier = Modifier,
    postTitle: String,
    postBody: String
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = postTitle,
            maxLines = 2,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = postBody,
            maxLines = 3,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    post: Post
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth(),
        shape = ShapeDefaults.Medium,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant),
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 4.dp)
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            PostCardHeader(
                name = post.userName,
                handle = post.userHandle
            )
            PostCardBody(
                postTitle = post.postTitle,
                postBody = post.postBody
            )
            PostCardFooter(
                comments = post.comments,
                isBookmarked = post.isBookmarked
            )
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        UserFilterSection()
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
        ) { paddingValue ->
            HomeScreen(modifier = Modifier.padding(paddingValue))
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
        SectionHeader(title = "Title", showActionButton = true)
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

@Preview(showBackground = true, widthDp = screenWidth)
@Composable
private fun UserFilterRowPreview() {
    PlaceHolderSocialTheme {
        UserFilterRow(items = userFilterList)
    }
}

@Preview(showBackground = true, widthDp = screenWidth)
@Composable
private fun UserFilterSectionPreview() {
    PlaceHolderSocialTheme {
        UserFilterSection()
    }
}

@Preview(showBackground = true, widthDp = screenWidth)
@Composable
private fun HomeScreenPreview() {
    PlaceHolderSocialTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun CommentsChipPreview() {
    PlaceHolderSocialTheme {
        CommentsChip(numberOfComments = 5)
    }
}

@Preview(showBackground = true)
@Composable
private fun BookmarkPreview() {
    PlaceHolderSocialTheme {
        Bookmark(isSelected = false)
    }
}

@Preview(showBackground = true, widthDp = screenWidth)
@Composable
private fun PostCardFooterPreview() {
    PlaceHolderSocialTheme {
        PostCardFooter(comments = 5, isBookmarked = true)
    }
}

@Preview(showBackground = true, widthDp = screenWidth)
@Composable
private fun PostCardHeaderPreview() {
    PlaceHolderSocialTheme {
        PostCardHeader(name = "Elioth", handle = "elioth")
    }
}

@Preview(showBackground = true, widthDp = screenWidth)
@Composable
private fun PostCardBodyPreview() {
    PlaceHolderSocialTheme {
        PostCardBody(
            postTitle = usersPosts.first().postTitle,
            postBody = usersPosts.first().postBody
        )
    }
}

@Preview(showBackground = true, widthDp = screenWidth)
@Composable
private fun PostCardPreview() {
    PlaceHolderSocialTheme {
        PostCard(post = usersPosts.first())
    }
}

// test data
data class UserFilter(val userName: String?)

data class Post(
    val userName: String,
    val userHandle: String,
    val postTitle: String,
    val postBody: String,
    val comments: Int,
    val isBookmarked: Boolean
)

val usersPosts = listOf(
    Post(
        userName = "Elioth",
        userHandle = "elioth",
        postTitle = "Sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        postBody = "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto",
        comments = 5,
        isBookmarked = false
    ),
    Post(
        userName = "Sandy",
        userHandle = "sandy",
        postTitle = "Sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        postBody = "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto",
        comments = 4,
        isBookmarked = false
    ),
    Post(
        userName = "Paco",
        userHandle = "paqui",
        postTitle = "Sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        postBody = "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto",
        comments = 3,
        isBookmarked = false
    ),
    Post(
        userName = "Winnie",
        userHandle = "wais",
        postTitle = "Sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        postBody = "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto",
        comments = 2,
        isBookmarked = false
    ),
    Post(
        userName = "Oli",
        userHandle = "oli",
        postTitle = "Sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        postBody = "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto",
        comments = 1,
        isBookmarked = false
    ),
    Post(
        userName = "Cristiano",
        userHandle = "cr7",
        postTitle = "Sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        postBody = "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto",
        comments = 0,
        isBookmarked = false
    )
)

val userFilterList = listOf(UserFilter(null)) + usersPosts.map { UserFilter(it.userName) }

// Constants
const val screenWidth = 360
const val screenHeight = 800