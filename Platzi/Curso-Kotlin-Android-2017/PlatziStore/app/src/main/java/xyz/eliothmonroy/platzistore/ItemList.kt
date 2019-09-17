package xyz.eliothmonroy.platzistore

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class ItemList : AnkoComponent<AdaptadorCarrito>{
    override fun createView(ui: AnkoContext<AdaptadorCarrito>): View = with(ui){
        verticalLayout {
            lparams(width= matchParent,height = wrapContent)
            cardView {
                linearLayout {
                    orientation=LinearLayout.HORIZONTAL
                    lparams(width=matchParent)
                    imageView(R.mipmap.ic_launcher){
                        id=R.id.imgItem
                        scaleType=ImageView.ScaleType.CENTER_CROP
                    }.lparams(width=dip(0),height = dip(100),weight = 1f)
                }
            }
        }
    }
}