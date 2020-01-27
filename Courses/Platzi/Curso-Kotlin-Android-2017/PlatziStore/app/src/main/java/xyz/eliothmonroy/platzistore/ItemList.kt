package xyz.eliothmonroy.platzistore

import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class ItemList : AnkoComponent<AdapterCart>{
    override fun createView(ui: AnkoContext<AdapterCart>): View = with(ui){
        verticalLayout {
            lparams(width= matchParent,height = wrapContent)
            cardView {
                linearLayout {
                    orientation=LinearLayout.HORIZONTAL
                    lparams(width=matchParent)
                    imageView(R.drawable.shoe_placeholder){
                        id=R.id.imgItem
                        scaleType=ImageView.ScaleType.CENTER_CROP
                    }.lparams(width=dip(0),height = dip(100),weight = 1f)
                    linearLayout {
                        padding=dip(5) //Dips son dp
                        orientation=LinearLayout.VERTICAL
                        textView("Titulo"){
                            id=R.id.txtTitleItem2
                            setTextAppearance(ctx,android.R.style.TextAppearance_Material_Large)
                        }
                        textView("Descripción"){
                            id=R.id.txtDescItem2
                            setTextAppearance(ctx,android.R.style.TextAppearance_Material_Small)
                        }
                        textView("Precio"){
                            id=R.id.txtPriceItem2
                            setTextAppearance(ctx,android.R.style.TextAppearance_Material_Medium)
                            textColor=resources.getColor(R.color.colorAccent)
                        }.lparams{
                            topMargin=dip(5)
                            gravity=Gravity.END
                        }
                    }.lparams(width=dip(0),height = wrapContent,weight = 2f)
                }
            }
        }
    }
}