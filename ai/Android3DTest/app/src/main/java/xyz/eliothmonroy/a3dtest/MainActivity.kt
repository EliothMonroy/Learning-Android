package xyz.eliothmonroy.a3dtest

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var content: LinearLayout
    private lateinit var scroll: ScrollView
    private lateinit var prefs: SharedPreferences
    private lateinit var saved: MutableSet<String>
    private lateinit var navigation: BottomNavigationView
    private lateinit var discoveryResults: LinearLayout
    private var category = "All places"
    private var tab = R.id.nav_discover
    private var renderedTab = -1
    private val pages = HashMap<Int, LinearLayout>()
    private val scrollPositions = HashMap<Int, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = getSharedPreferences("distrito", MODE_PRIVATE)
        saved = HashSet(prefs.getStringSet("saved", emptySet()).orEmpty())
        if (savedInstanceState != null) {
            tab = savedInstanceState.getInt("tab", R.id.nav_discover)
            category = savedInstanceState.getString("category") ?: "All places"
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
            isAppearanceLightNavigationBars = true
        }
        window.isNavigationBarContrastEnforced = false
        val root = column().apply { setBackgroundColor(BG) }
        ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
            val safe = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
            view.setPadding(safe.left, safe.top, safe.right, safe.bottom)
            WindowInsetsCompat.CONSUMED
        }
        setContentView(root)
        root.addView(header())
        scroll = ScrollView(this).apply {
            isFillViewport = true
            isVerticalScrollBarEnabled = false
        }
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))
        navigation = BottomNavigationView(this).apply {
            setBackgroundColor(BG)
            itemIconTintList = states(INK, MUTED)
            itemTextColor = states(INK, MUTED)
            itemActiveIndicatorColor = ColorStateList.valueOf(MINT)
            menu.add(0, R.id.nav_discover, 0, "Discover").setIcon(Symbol("compass", INK))
            menu.add(0, R.id.nav_map, 1, "City map").setIcon(Symbol("map", INK))
            menu.add(0, R.id.nav_saved, 2, "Saved").setIcon(Symbol("heart", INK))
            setOnItemSelectedListener { item ->
                tab = item.itemId
                render()
                true
            }
        }
        root.addView(View(this).apply { setBackgroundColor(LINE) }, LinearLayout.LayoutParams(-1, dp(1)))
        root.addView(navigation, LinearLayout.LayoutParams(-1, dp(76)))
        navigation.selectedItemId = tab
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("tab", tab)
        outState.putString("category", category)
    }

    private fun states(yes: Int, no: Int) = ColorStateList(
        arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf()),
        intArrayOf(yes, no),
    )
    private fun dp(value: Int): Int = dp(value.toFloat())
    private fun dp(value: Float): Int = Math.round(value * resources.displayMetrics.density)
    private fun column() = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
    private fun row() = LinearLayout(this).apply { gravity = Gravity.CENTER_VERTICAL }
    private fun text(value: String, size: Int, color: Int, bold: Boolean) = TextView(this).apply {
        text = value
        textSize = size.toFloat()
        setTextColor(color)
        includeFontPadding = false
        typeface = Typeface.create(if (bold) "sans-serif-medium" else "sans-serif", if (bold) Typeface.BOLD else Typeface.NORMAL)
    }
    private fun label(value: String) = text(value, 10, MUTED, true).apply { letterSpacing = 0.13f }
    private fun space(layout: LinearLayout, height: Int) {
        layout.addView(View(this), LinearLayout.LayoutParams(1, dp(height)))
    }
    private fun round(color: Int, radius: Int) = GradientDrawable().apply {
        setColor(color)
        cornerRadius = dp(radius).toFloat()
    }
    private fun icon(name: String, description: String, action: () -> Unit) = ImageButton(this).apply {
        setImageDrawable(Symbol(name, INK))
        scaleType = ImageView.ScaleType.FIT_CENTER
        contentDescription = description
        background = RippleDrawable(ColorStateList.valueOf(MINT), round(Color.TRANSPARENT, 24), null)
        setPadding(dp(12), dp(12), dp(12), dp(12))
        setOnClickListener { action() }
    }

    private fun header(): View = row().apply {
        setPadding(dp(24), dp(12), dp(16), dp(10))
        addView(text("distrito", 29, INK, true).apply { letterSpacing = -0.05f })
        addView(text("°", 30, Color.rgb(207, 125, 76), true))
        addView(View(this@MainActivity), LinearLayout.LayoutParams(0, 1, 1f))
        addView(text("⌖  CDMX", 11, INK, true).apply {
            letterSpacing = 0.06f
            setPadding(dp(12), dp(9), dp(12), dp(9))
            background = round(MINT, 20)
        })
        addView(icon("info", "About Distrito") {
            MaterialAlertDialogBuilder(this@MainActivity)
                .setTitle("A different view of the city")
                .setMessage("Your pocket collection of Mexico City icons. Explore six handcrafted, stylized 3D models, save your favorites, and find your way there.\n\nModels and stories work offline. Directions open your maps app. Models are artistic interpretations.")
                .setPositiveButton("Let’s explore", null).show()
        }, LinearLayout.LayoutParams(dp(48), dp(48)))
    }

    private fun card(color: Int, radius: Int) = MaterialCardView(this).apply {
        this.radius = dp(radius).toFloat()
        cardElevation = 0f
        setCardBackgroundColor(color)
        strokeWidth = dp(1)
        strokeColor = LINE
    }
    private fun button(title: String, filled: Boolean) = MaterialButton(
        this, null, if (filled) com.google.android.material.R.attr.materialButtonStyle
        else com.google.android.material.R.attr.materialButtonOutlinedStyle,
    ).apply {
        text = title
        textSize = 13f
        isAllCaps = false
        cornerRadius = dp(28)
        insetTop = 0
        insetBottom = 0
        setTextColor(if (filled) BG else INK)
        if (filled) backgroundTintList = ColorStateList.valueOf(INK)
    }

    private fun toggle(attraction: Attraction) {
        if (!saved.remove(attraction.id)) saved.add(attraction.id)
        prefs.edit().putStringSet("saved", HashSet(saved)).apply()
        for (page in pages.values) refreshBookmarks(page)
        if (renderedTab == R.id.nav_saved) {
            content.removeAllViews()
            savedScreen()
        } else pages.remove(R.id.nav_saved)
    }
    private fun bookmark(attraction: Attraction) = icon("heart", "Save ${attraction.name}") {
        toggle(attraction)
    }.apply {
        tag = attraction
        updateBookmark(this, attraction)
    }
    private fun updateBookmark(button: ImageButton, attraction: Attraction) {
        val selected = attraction.id in saved
        button.setImageDrawable(Symbol(if (selected) "heartFill" else "heart", INK))
        button.contentDescription = if (selected) "Remove ${attraction.name} from saved" else "Save ${attraction.name}"
    }
    private fun refreshBookmarks(view: View) {
        val attraction = view.tag as? Attraction
        if (view is ImageButton && attraction != null) updateBookmark(view, attraction)
        if (view is ViewGroup) for (i in 0 until view.childCount) refreshBookmarks(view.getChildAt(i))
    }

    private fun render() {
        if (renderedTab == tab) return
        if (::content.isInitialized) {
            scrollPositions[renderedTab] = scroll.scrollY
            scroll.removeView(content)
        }
        val cached = pages[tab]
        content = cached ?: column().apply { setPadding(dp(24), dp(20), dp(24), dp(26)) }
        if (cached == null) {
            pages[tab] = content
            when (tab) {
                R.id.nav_map -> mapScreen()
                R.id.nav_saved -> savedScreen()
                else -> discoverScreen()
            }
        }
        scroll.addView(content)
        renderedTab = tab
        val destination = tab
        val target = scrollPositions[tab] ?: 0
        scroll.post { if (renderedTab == destination) scroll.scrollTo(0, target) }
    }

    private fun discoverScreen() {
        content.addView(label("THE CITY. A WHOLE NEW PERSPECTIVE."))
        space(content, 12)
        content.addView(text("A city of wonders.\nYours to explore.", 34, INK, true).apply {
            letterSpacing = -0.04f
            setLineSpacing(dp(1).toFloat(), 1f)
        })
        space(content, 10)
        content.addView(text("Discover the heart of Mexico City in 3D.", 14, MUTED, false))
        space(content, 23)
        val filters = HorizontalScrollView(this).apply { isHorizontalScrollBarEnabled = false }
        val chips = ChipGroup(this).apply {
            setSingleLine(true)
            isSingleSelection = true
            isSelectionRequired = true
        }
        for (name in listOf("All places", "Architecture", "Museums", "Monuments")) {
            val chip = Chip(this).apply {
                text = name
                isCheckable = true
                isChecked = name == category
                setEnsureMinTouchTargetSize(true)
                textSize = 12f
                chipCornerRadius = dp(22).toFloat()
                isCheckedIconVisible = false
                chipBackgroundColor = states(INK, BG)
                setTextColor(states(BG, MUTED))
                chipStrokeWidth = dp(1).toFloat()
                chipStrokeColor = ColorStateList.valueOf(LINE)
                setOnClickListener {
                    if (category != name) {
                        category = name
                        refreshDiscovery()
                    }
                }
            }
            chips.addView(chip)
        }
        filters.addView(chips)
        content.addView(filters, LinearLayout.LayoutParams(-1, dp(48)))
        space(content, 17)
        discoveryResults = column()
        content.addView(discoveryResults)
        refreshDiscovery()
    }

    private fun refreshDiscovery() {
        val target = discoveryResults
        target.removeAllViews()
        val matches = Attraction.ALL.filter { category == "All places" || category == it.category }
        target.addView(feature(matches.first()))
        space(target, 22)
        val section = row()
        section.addView(text(if (category == "All places") "Find your next stop" else "$category to discover", 21, INK, true),
            LinearLayout.LayoutParams(0, -2, 1f))
        section.addView(text("View all  ↗", 12, INK, true).apply {
            minHeight = dp(48)
            gravity = Gravity.CENTER
            setOnClickListener { showAll() }
        })
        target.addView(section)
        space(target, 9)
        val rail = HorizontalScrollView(this).apply { isHorizontalScrollBarEnabled = false }
        val cards = row()
        val more = if (category == "All places") Attraction.ALL.subList(1, Attraction.ALL.size) else matches
        for (attraction in more) {
            cards.addView(smallCard(attraction), LinearLayout.LayoutParams(dp(208), -2).apply { marginEnd = dp(14) })
        }
        rail.addView(cards)
        target.addView(rail)
        space(target, 22)
        target.addView(text("✧   A little closer. A little more curious.\n      Every landmark has a story.", 12, INK, false).apply {
            setPadding(dp(16), dp(18), dp(16), dp(18))
            background = round(MINT, 16)
        })
    }

    private fun feature(attraction: Attraction): View {
        val card = card(MINT, 24)
        val body = column()
        val scene = FrameLayout(this)
        scene.addView(LandmarkView(this, attraction.model, false), FrameLayout.LayoutParams(-1, dp(235)))
        val badge = text("✦  THE 3D COLLECTION", 9, INK, true).apply {
            letterSpacing = 0.13f
            setPadding(dp(12), dp(8), dp(12), dp(8))
            background = round(Color.rgb(243, 246, 234), 20)
        }
        scene.addView(badge, FrameLayout.LayoutParams(-2, -2, Gravity.TOP or Gravity.START).apply {
            setMargins(dp(16), dp(16), 0, 0)
        })
        scene.addView(bookmark(attraction), FrameLayout.LayoutParams(dp(48), dp(48), Gravity.TOP or Gravity.END).apply {
            setMargins(0, dp(8), dp(8), 0)
        })
        val orbit = text("360°", 11, INK, true).apply {
            setPadding(dp(10), dp(6), dp(10), dp(6))
            background = round(Color.rgb(243, 246, 234), 20)
        }
        scene.addView(orbit, FrameLayout.LayoutParams(-2, -2, Gravity.BOTTOM or Gravity.END).apply {
            setMargins(0, 0, dp(16), dp(10))
        })
        scene.setOnClickListener { detail(attraction) }
        body.addView(scene)
        val lower = column().apply { setPadding(dp(19), dp(10), dp(19), dp(18)) }
        lower.addView(label(attraction.area.uppercase(Locale.ROOT) + "  ·  " + attraction.category.uppercase(Locale.ROOT)))
        space(lower, 8)
        lower.addView(text(attraction.name, 23, INK, true))
        space(lower, 7)
        lower.addView(text(attraction.description, 13, MUTED, false))
        space(lower, 16)
        lower.addView(button("Explore in 3D  ↗", true).apply { setOnClickListener { detail(attraction) } },
            LinearLayout.LayoutParams(-1, dp(48)))
        body.addView(lower)
        card.addView(body)
        return card
    }

    private fun smallCard(attraction: Attraction): View {
        val card = card(BG, 19)
        val body = column()
        val art = FrameLayout(this).apply {
            setBackgroundColor(if (attraction.model == "angel") Color.rgb(239, 231, 212) else Color.rgb(226, 234, 226))
        }
        art.addView(LandmarkView(this, attraction.model, false), FrameLayout.LayoutParams(-1, dp(148)))
        art.addView(bookmark(attraction), FrameLayout.LayoutParams(dp(48), dp(48), Gravity.TOP or Gravity.END))
        body.addView(art)
        val copy = column().apply { setPadding(dp(13), dp(13), dp(13), dp(15)) }
        copy.addView(label(attraction.category.uppercase(Locale.ROOT)))
        space(copy, 7)
        copy.addView(text(attraction.name, 16, INK, true).apply { minLines = 2 })
        space(copy, 7)
        copy.addView(text(attraction.area + "  ↗", 11, MUTED, false))
        body.addView(copy)
        card.addView(body)
        card.setOnClickListener { detail(attraction) }
        return card
    }

    private fun placeRow(attraction: Attraction): View {
        val card = card(BG, 18)
        val row = row().apply { setPadding(dp(6), dp(6), dp(10), dp(6)) }
        row.addView(LandmarkView(this, attraction.model, false).apply { background = round(MINT, 12) },
            LinearLayout.LayoutParams(dp(88), dp(92)))
        val info = column().apply { setPadding(dp(12), 0, dp(5), 0) }
        info.addView(text(attraction.name, 15, INK, true))
        space(info, 7)
        info.addView(text(attraction.area, 12, MUTED, false))
        row.addView(info, LinearLayout.LayoutParams(0, -2, 1f))
        row.addView(text("↗", 21, INK, false))
        card.addView(row)
        card.setOnClickListener { detail(attraction) }
        return card
    }

    private fun showAll() {
        val dialog = BottomSheetDialog(this)
        val list = column().apply { setPadding(dp(24), dp(16), dp(24), dp(24)) }
        val heading = row()
        heading.addView(text("The city collection", 26, INK, true), LinearLayout.LayoutParams(0, -2, 1f))
        heading.addView(icon("close", "Close collection", dialog::dismiss), LinearLayout.LayoutParams(dp(48), dp(48)))
        list.addView(heading)
        space(list, 16)
        for (attraction in Attraction.ALL) { list.addView(placeRow(attraction)); space(list, 10) }
        dialog.setContentView(ScrollView(this).apply { addView(list) })
        dialog.show()
    }

    private fun savedScreen() {
        content.addView(label("A COLLECTION OF YOUR OWN"))
        space(content, 12)
        content.addView(text("Your city, saved.", 32, INK, true))
        space(content, 10)
        content.addView(text(saved.size.toString() + if (saved.size == 1) " place to come back to." else " places to come back to.", 14, MUTED, false))
        space(content, 24)
        if (saved.isEmpty()) {
            content.addView(LandmarkView(this, "angel", false), LinearLayout.LayoutParams(-1, dp(270)))
            content.addView(text("Keep a little inspiration.", 23, INK, true).apply { gravity = Gravity.CENTER })
            space(content, 12)
            content.addView(text("Tap the heart on any landmark to build\nyour own Mexico City collection.", 14, MUTED, false).apply { gravity = Gravity.CENTER })
            space(content, 26)
            content.addView(button("Discover places  ↗", true).apply { setOnClickListener { navigation.selectedItemId = R.id.nav_discover } },
                LinearLayout.LayoutParams(-1, dp(50)))
        } else {
            for (attraction in Attraction.ALL) if (attraction.id in saved) {
                content.addView(placeRow(attraction))
                space(content, 12)
            }
        }
    }

    private fun detail(attraction: Attraction) {
        val dialog = BottomSheetDialog(this)
        val layout = column().apply {
            setPadding(dp(22), dp(14), dp(22), dp(24))
            background = round(BG, 28)
        }
        val body = ScrollView(this).apply { addView(layout) }
        val top = row()
        top.addView(label("LANDMARK / " + attraction.category.uppercase(Locale.ROOT)), LinearLayout.LayoutParams(0, -2, 1f))
        top.addView(icon("close", "Close landmark", dialog::dismiss), LinearLayout.LayoutParams(dp(48), dp(48)))
        layout.addView(top)
        layout.addView(text(attraction.name, 26, INK, true))
        space(layout, 8)
        layout.addView(text(attraction.area, 13, MUTED, false))
        val model = LandmarkView(this, attraction.model, true).apply { background = round(MINT, 22) }
        layout.addView(model, LinearLayout.LayoutParams(-1, dp(285)).apply { topMargin = dp(20) })
        layout.addView(text("DRAG TO ROTATE  ·  PINCH TO ZOOM", 10, MUTED, true).apply {
            gravity = Gravity.CENTER
            setPadding(0, dp(13), 0, dp(8))
        })
        val controls = row()
        val left = button("↶ Rotate", false).apply { setOnClickListener { model.rotate(-0.35f) } }
        val reset = button("Reset", false).apply { setOnClickListener { model.reset() } }
        val right = button("Rotate ↷", false).apply { setOnClickListener { model.rotate(0.35f) } }
        for (button in listOf(left, reset, right)) {
            controls.addView(button, LinearLayout.LayoutParams(0, dp(48), 1f).apply {
                setMargins(dp(2), 0, dp(2), 0)
            })
        }
        layout.addView(controls)
        space(layout, 19)
        layout.addView(text(attraction.detail, 15, INK, false).apply { setLineSpacing(dp(6).toFloat(), 1f) })
        space(layout, 20)
        val save = button(if (attraction.id in saved) "Saved to your places  ♥" else "Save this place  ♡", true)
        save.setOnClickListener {
            toggle(attraction)
            save.text = if (attraction.id in saved) "Saved to your places  ♥" else "Save this place  ♡"
        }
        layout.addView(save, LinearLayout.LayoutParams(-1, dp(50)))
        space(layout, 10)
        layout.addView(button("Get directions  ↗", false).apply { setOnClickListener { directions(attraction) } },
            LinearLayout.LayoutParams(-1, dp(50)))
        dialog.setContentView(body)
        dialog.setOnShowListener {
            val sheet = dialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            if (sheet != null) {
                sheet.layoutParams.height = (resources.displayMetrics.heightPixels * 0.91f).toInt()
                BottomSheetBehavior.from(sheet).state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        dialog.show()
    }

    private fun directions(attraction: Attraction) {
        val uri = Uri.parse("geo:${attraction.lat},${attraction.lon}?q=${attraction.lat},${attraction.lon}(${Uri.encode(attraction.name)})")
        try {
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        } catch (_: ActivityNotFoundException) {
            MaterialAlertDialogBuilder(this).setTitle(attraction.name)
                .setMessage("Coordinates: ${attraction.lat}, ${attraction.lon}\n\nInstall a maps app to get directions.")
                .setPositiveButton("OK", null).show()
        }
    }

    private fun mapScreen() {
        content.addView(label("GET TO KNOW YOUR SURROUNDINGS"))
        space(content, 12)
        content.addView(text("One remarkable city.", 30, INK, true))
        space(content, 10)
        content.addView(text("Six icons. Countless ways to explore.", 14, MUTED, false))
        space(content, 22)
        val map = card(MINT, 22)
        map.addView(CityMap(this), FrameLayout.LayoutParams(-1, dp(330)))
        content.addView(map)
        space(content, 12)
        content.addView(label("ILLUSTRATED MAP · TAP A PIN"))
        space(content, 22)
        for (attraction in Attraction.ALL) { content.addView(placeRow(attraction)); space(content, 12) }
    }

    private inner class CityMap(context: Context) : View(context) {
        private val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
        private val points = Array(Attraction.ALL.size) { FloatArray(2) }
        private val labelTypeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
        init {
            contentDescription = "Illustrated Mexico City landmark map. Accessible landmark list follows below."
        }
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            val w = width.toFloat()
            val h = height.toFloat()
            canvas.drawColor(MINT)
            paint.strokeWidth = dp(7).toFloat()
            paint.color = Color.rgb(243, 244, 230)
            for (i in 0 until 10) canvas.drawLine(w*i/8, -20f, w*i/8-w*0.22f, h, paint)
            for (i in 1 until 8) canvas.drawLine(0f, h*i/7, w, h*i/7-h*0.13f, paint)
            paint.color = Color.rgb(177, 201, 165)
            canvas.drawOval(w*0.06f, h*0.57f, w*0.47f, h*1.08f, paint)
            paint.color = Color.rgb(239, 212, 166)
            paint.strokeWidth = dp(12).toFloat()
            canvas.drawLine(w*0.22f, h*0.9f, w*0.74f, h*0.36f, paint)
            paint.color = MUTED
            paint.textSize = dp(10).toFloat()
            paint.typeface = labelTypeface
            canvas.drawText("POLANCO", w*0.10f, h*0.1f, paint)
            canvas.drawText("CENTRO", w*0.69f, h*0.19f, paint)
            canvas.drawText("CHAPULTEPEC", w*0.07f, h*0.94f, paint)
            paint.textSize = dp(9).toFloat()
            canvas.drawText("REFORMA", w*0.35f, h*0.66f, paint)
            for (i in Attraction.ALL.indices) {
                val attraction = Attraction.ALL[i]
                val x = (0.12+(attraction.lon+99.205)/0.072*0.76).toFloat()*w
                val y = (0.16+(19.442-attraction.lat)/0.024*0.65).toFloat()*h
                points[i][0] = x
                points[i][1] = y
                paint.color = INK
                canvas.drawCircle(x, y, dp(16).toFloat(), paint)
                paint.color = BG
                paint.textSize = dp(12).toFloat()
                paint.textAlign = Paint.Align.CENTER
                canvas.drawText((i+1).toString(), x, y+dp(4), paint)
                paint.textAlign = Paint.Align.LEFT
            }
        }
        override fun onTouchEvent(event: MotionEvent): Boolean {
            if (event.action == MotionEvent.ACTION_UP) {
                for (i in points.indices) {
                    if (Math.hypot((event.x-points[i][0]).toDouble(), (event.y-points[i][1]).toDouble()) < dp(28)) {
                        detail(Attraction.ALL[i])
                        performClick()
                        return true
                    }
                }
                performClick()
            }
            return true
        }
        override fun performClick(): Boolean { super.performClick(); return true }
    }

    private companion object {
        val INK = Color.rgb(24, 61, 53)
        val MUTED = Color.rgb(113, 127, 117)
        val BG = Color.rgb(249, 248, 242)
        val MINT = Color.rgb(225, 235, 219)
        val LINE = Color.rgb(224, 228, 216)
    }
}
