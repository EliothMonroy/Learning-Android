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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
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
            .padding(start = 16.dp, end = if (showActionButton) 4.dp else 16.dp),
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
fun UserFilterList(modifier: Modifier = Modifier, items: List<UserFilter>) {
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
fun UserFilterSection(
    modifier: Modifier = Modifier,
    items: List<UserFilter>
) {
    Column(modifier = modifier) {
        SectionHeader(title = "People", showActionButton = true)
        UserFilterList(items = items)
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
    isSelected: Boolean = false,
    onPostBookmarked: (Boolean) -> Unit
) {
    IconToggleButton(
        modifier = modifier.size(48.dp),
        checked = isSelected,
        onCheckedChange = { value ->
            onPostBookmarked(value)
        }
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
    isBookmarked: Boolean = false,
    onPostBookmarked: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CommentsChip(numberOfComments = comments)
        Bookmark(isSelected = isBookmarked, onPostBookmarked = onPostBookmarked)
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
    post: Post,
    onPostBookmarked: (Int, Boolean) -> Unit
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
                isBookmarked = post.isBookmarked,
                onPostBookmarked = { value -> onPostBookmarked(post.id, value) }
            )
        }
    }
}

@Composable
fun PostCardList(
    modifier: Modifier = Modifier,
    postList: List<Post>,
    onPostBookmarked: (Int, Boolean) -> Unit
) {
    LazyColumn(
        modifier = modifier.padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(items = postList, key = { it.id }) { post ->
            PostCard(post = post, onPostBookmarked = onPostBookmarked)
        }
    }
}

@Composable
fun LatestPostsSection(
    modifier: Modifier = Modifier,
    items: List<Post>,
    onPostBookmarked: (Int, Boolean) -> Unit
) {
    Column(modifier = modifier) {
        SectionHeader(title = "Latest posts")
        PostCardList(postList = items, onPostBookmarked = onPostBookmarked)
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: PlaceholderViewModel = viewModel {
        PlaceholderViewModel(
            repository = PlaceholderRepositoryImpl(
                localDataSource = LocalDataSource(),
                remoteDataSource = RemoteDataSource(PlaceholderApi.retrofitService)
            )
        )
    }
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        when (val state = uiState) {
            is UiState.Success -> {
                UserFilterSection(items = state.usersFilter)
                Spacer(modifier = Modifier.height(8.dp))
                LatestPostsSection(
                    items = state.posts,
                    onPostBookmarked = { postId, value -> viewModel.bookmarkPost(postId, value) })
            }

            is UiState.Loading -> {
                Text("Loading...")
            }

            is UiState.Error -> {
                Text(text = "Error: ${state.error}")
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    PlaceHolderSocialTheme {
        Scaffold(
            modifier = modifier,
            topBar = { TopBar() },
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
            Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
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
                style = MaterialTheme.typography.titleLarge,
                text = "Feed"
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
    NavigationBar(
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
private fun UserFilterListPreview() {
    PlaceHolderSocialTheme {
        UserFilterList(items = userFilterList)
    }
}

@Preview(showBackground = true, widthDp = screenWidth)
@Composable
private fun UserFilterSectionPreview() {
    PlaceHolderSocialTheme {
        UserFilterSection(items = userFilterList)
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
        Bookmark(isSelected = false, onPostBookmarked = {})
    }
}

@Preview(showBackground = true, widthDp = screenWidth)
@Composable
private fun PostCardFooterPreview() {
    PlaceHolderSocialTheme {
        PostCardFooter(comments = 5, isBookmarked = true, onPostBookmarked = {})
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
        PostCard(post = usersPosts.first(), onPostBookmarked = { _, _ -> })
    }
}

@Preview(showBackground = true, widthDp = screenWidth)
@Composable
private fun PostCardListPreview() {
    PlaceHolderSocialTheme {
        PostCardList(postList = usersPosts, onPostBookmarked = { _, _ -> })
    }
}

@Preview(showBackground = true, widthDp = screenWidth)
@Composable
private fun LatestPostsSectionPreview() {
    PlaceHolderSocialTheme {
        LatestPostsSection(items = usersPosts, onPostBookmarked = { _, _ -> })
    }
}

// test data
data class UserFilter(val userName: String?)

data class Post(
    val id: Int,
    val userName: String,
    val userHandle: String,
    val postTitle: String,
    val postBody: String,
    val comments: Int,
    val isBookmarked: Boolean
)

val usersPosts = listOf(
    Post(
        id = 1,
        userName = "Elioth",
        userHandle = "elioth",
        postTitle = "Sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        postBody = "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto",
        comments = 5,
        isBookmarked = false
    ),
    Post(
        id = 2,
        userName = "Sandy",
        userHandle = "sandy",
        postTitle = "Sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        postBody = "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto",
        comments = 4,
        isBookmarked = false
    ),
    Post(
        id = 3,
        userName = "Paco",
        userHandle = "paqui",
        postTitle = "Sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        postBody = "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto",
        comments = 3,
        isBookmarked = false
    ),
    Post(
        id = 4,
        userName = "Winnie",
        userHandle = "wais",
        postTitle = "Sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        postBody = "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto",
        comments = 2,
        isBookmarked = false
    ),
    Post(
        id = 5,
        userName = "Oli",
        userHandle = "oli",
        postTitle = "Sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        postBody = "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto",
        comments = 1,
        isBookmarked = false
    ),
    Post(
        id = 6,
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

// Network layer
@Serializable
data class UserApiModel(val id: Int, val name: String, val username: String)

@Serializable
data class PostApiModel(val userId: Int, val id: Int, val title: String, val body: String)

@Serializable
data class CommentApiModel(val postId: Int)

interface PlaceholderApiService {
    @GET("users")
    suspend fun getUsers(): List<UserApiModel>

    @GET("posts")
    suspend fun getPosts(): List<PostApiModel>

    @GET("comments")
    suspend fun getComments(): List<CommentApiModel>
}

object PlaceholderApi {
    private const val BASE_URL =
        "https://jsonplaceholder.typicode.com/"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL).build()

    val retrofitService: PlaceholderApiService by lazy { retrofit.create(PlaceholderApiService::class.java) }
}

interface DataSource {
    suspend fun getUsers(): List<UserApiModel>

    suspend fun getPosts(): List<PostApiModel>
    suspend fun getComments(): List<CommentApiModel>
}

class RemoteDataSource(val retrofitService: PlaceholderApiService) : DataSource {
    override suspend fun getUsers(): List<UserApiModel> {
        return retrofitService.getUsers()
    }

    override suspend fun getPosts(): List<PostApiModel> {
        return retrofitService.getPosts()
    }

    override suspend fun getComments(): List<CommentApiModel> {
        return retrofitService.getComments()
    }
}

class LocalDataSource : DataSource {
    override suspend fun getUsers(): List<UserApiModel> {
        return emptyList()
    }

    override suspend fun getPosts(): List<PostApiModel> {
        return emptyList()
    }

    override suspend fun getComments(): List<CommentApiModel> {
        return emptyList()
    }
}

// Repository Layer

data class UserModel(val id: Int, val name: String, val username: String)

data class PostModel(val userId: Int, val id: Int, val title: String, val body: String)

data class CommentModel(val postId: Int)

interface PlaceholderRepository {
    suspend fun getUsers(): List<UserModel>

    suspend fun getPosts(): List<PostModel>
    suspend fun getComments(): List<CommentModel>
}

class PlaceholderRepositoryImpl(
    val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource
) : PlaceholderRepository {
    override suspend fun getUsers(): List<UserModel> {
        var data = localDataSource.getUsers()
        if (data.isEmpty()) {
            data = remoteDataSource.getUsers()
        }
        return data.map {
            UserModel(
                id = it.id,
                name = it.name,
                username = it.username
            )
        }
    }

    override suspend fun getPosts(): List<PostModel> {
        var data = localDataSource.getPosts()
        if (data.isEmpty()) {
            data = remoteDataSource.getPosts()
        }
        return data.map {
            PostModel(
                userId = it.userId,
                id = it.id,
                title = it.title,
                body = it.body
            )
        }
    }

    override suspend fun getComments(): List<CommentModel> {
        var data = localDataSource.getComments()
        if (data.isEmpty()) {
            data = remoteDataSource.getComments()
        }
        return data.map {
            CommentModel(
                postId = it.postId
            )
        }
    }
}

sealed interface UiState {
    data class Success(val usersFilter: List<UserFilter>, val posts: List<Post>) : UiState
    object Loading : UiState
    data class Error(val error: String) : UiState
}


class PlaceholderViewModel(
    val repository: PlaceholderRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private var users = emptyList<UserModel>()

    private var comments = emptyList<CommentModel>()

    private var posts = emptyList<PostModel>()

    private var userFilters = emptyList<UserFilter>()

    private var postsList = emptyList<Post>()

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch(dispatcher) {
            try {
                users = repository.getUsers()
                comments = repository.getComments()
                posts = repository.getPosts()
                userFilters = users.map { UserFilter(userName = it.username) }
                postsList = posts.map {
                    Post(
                        id = it.id,
                        userName = users.find { user -> user.id == it.userId }?.name ?: "",
                        userHandle = users.find { user -> user.id == it.userId }?.username ?: "",
                        postTitle = it.title,
                        postBody = it.body,
                        comments = comments.count { comment -> comment.postId == it.id },
                        isBookmarked = false
                    )
                }
                _uiState.value = UiState.Success(
                    usersFilter = userFilters,
                    posts = postsList
                )
            } catch (e: IOException) {
                _uiState.value = UiState.Error(e.toString())
            }
        }
    }

    fun bookmarkPost(postId: Int, isBookmarked: Boolean) {
        _uiState.update { state ->
            if (state !is UiState.Success) return@update state
            state.copy(
                posts = state.posts.map { post ->
                    if (post.id == postId) {
                        post.copy(isBookmarked = isBookmarked)
                    } else post
                }
            )
        }
    }
}
