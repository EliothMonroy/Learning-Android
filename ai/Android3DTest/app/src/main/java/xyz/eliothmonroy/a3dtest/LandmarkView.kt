package xyz.eliothmonroy.a3dtest

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.LruCache
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import java.util.Collections
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin
import kotlin.math.sqrt

/** Offline 3D mesh renderer with orbit and pinch-to-zoom controls. */
class LandmarkView(context: Context, private val model: String, private val interactive: Boolean) : View(context) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
    private val mesh: List<Face>
    private var buildingMesh: MutableList<Face>? = null
    private val projected: Array<FloatArray>
    private val depths: FloatArray
    private val vertices: FloatArray
    private val order: IntArray
    private val faceColors: IntArray
    private val colors: IntArray
    private val previewBounds = RectF()
    private var preview: Bitmap? = null
    private var previewKey = ""
    private var geometryDirty = true
    private var yaw = -0.48f
    private var pitch = 0.40f
    private var zoom = 1f
    private var lastX = 0f
    private var lastY = 0f
    private var vertexFloats = 0
    private val scale: ScaleGestureDetector? = if (interactive) {
        ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                zoom = (zoom * detector.scaleFactor).coerceIn(0.65f, 1.8f)
                requestFrame()
                return true
            }
        })
    } else null

    init {
        contentDescription = "Stylized 3D model of $model" +
            if (interactive) ". Drag to rotate. Pinch to zoom." else ""
        importantForAccessibility = if (interactive) IMPORTANT_FOR_ACCESSIBILITY_YES else IMPORTANT_FOR_ACCESSIBILITY_NO
        mesh = synchronized(modelMeshes) {
            modelMeshes[model] ?: run {
                val faces = ArrayList<Face>()
                buildingMesh = faces
                build()
                buildingMesh = null
                Collections.unmodifiableList(faces).also { modelMeshes[model] = it }
            }
        }
        projected = Array(mesh.size) { FloatArray(mesh[it].v.size * 2) }
        depths = FloatArray(mesh.size)
        order = IntArray(mesh.size) { it }
        faceColors = IntArray(mesh.size)
        var triangles = 0
        for (face in mesh) triangles += face.v.size - 2
        vertices = FloatArray(triangles * 6)
        colors = IntArray(triangles * 3)
    }

    private class Face(val color: Int, vararg val v: FloatArray) {
        val nx: Float
        val ny: Float
        val nz: Float
        init {
            val ux=v[1][0]-v[0][0]; val uy=v[1][1]-v[0][1]; val uz=v[1][2]-v[0][2]
            val vx=v[2][0]-v[0][0]; val vy=v[2][1]-v[0][1]; val vz=v[2][2]-v[0][2]
            val x=uy*vz-uz*vy; val y=uz*vx-ux*vz; val z=ux*vy-uy*vx
            val length=sqrt((x*x+y*y+z*z).toDouble()).toFloat()
            nx=if(length==0f) 0f else x/length
            ny=if(length==0f) 0f else y/length
            nz=if(length==0f) 0f else z/length
        }
    }

    private fun requestFrame() { geometryDirty = true; postInvalidateOnAnimation() }
    fun reset() { yaw = -0.48f; pitch = 0.4f; zoom = 1f; requestFrame() }
    fun rotate(delta: Float) { if (interactive) { yaw += delta; requestFrame() } }
    private fun col(value: String): Int = Color.parseColor(value)
    private fun addFace(face: Face) { checkNotNull(buildingMesh).add(face) }

    private fun box(x: Float,y: Float,z: Float,w: Float,h: Float,d: Float,color: Int) {
        val a=x-w/2; val b=x+w/2; val c=z-d/2; val e=z+d/2; val t=y+h
        val aa=floatArrayOf(a,y,c); val bb=floatArrayOf(b,y,c)
        val cc=floatArrayOf(b,y,e); val dd=floatArrayOf(a,y,e)
        val ee=floatArrayOf(a,t,c); val ff=floatArrayOf(b,t,c)
        val gg=floatArrayOf(b,t,e); val hh=floatArrayOf(a,t,e)
        addFace(Face(color,aa,dd,cc,bb)); addFace(Face(color,ee,ff,gg,hh))
        addFace(Face(color,aa,bb,ff,ee)); addFace(Face(color,bb,cc,gg,ff))
        addFace(Face(color,cc,dd,hh,gg)); addFace(Face(color,dd,aa,ee,hh))
    }

    private fun dome(x: Float,y: Float,z: Float,r: Float,height: Float,color: Int) {
        val n=if(r<0.16f) 8 else if(r<0.4f) 12 else 20
        val bands=if(height<0.05f) 1 else if(height<0.2f) 2 else 5
        for(j in 0 until bands) for(i in 0 until n) {
            val p=Array(4) { FloatArray(3) }
            val ii=intArrayOf(i,i+1,i+1,i); val jj=intArrayOf(j,j,j+1,j+1)
            for(k in 0 until 4) {
                val a=ii[k]*Math.PI*2/n; val b=jj[k]*Math.PI/2/bands
                p[k]=floatArrayOf(x+r*(cos(b)*cos(a)).toFloat(),y+height*sin(b).toFloat(),z+r*(cos(b)*sin(a)).toFloat())
            }
            addFace(Face(if(i%3==0) shade(color,0.91f) else color,*p))
        }
    }

    private fun cylinder(x: Float,y: Float,z: Float,r: Float,h: Float,color: Int) {
        val segments=if(r<0.06f) 8 else 16
        val cap=Array(segments) { FloatArray(3) }
        for(i in 0 until segments) {
            val a=i*Math.PI*2/segments; val b=(i+1)*Math.PI*2/segments
            val ax=x+r*cos(a).toFloat(); val az=z+r*sin(a).toFloat()
            val bx=x+r*cos(b).toFloat(); val bz=z+r*sin(b).toFloat()
            addFace(Face(color,floatArrayOf(ax,y,az),floatArrayOf(bx,y,bz),floatArrayOf(bx,y+h,bz),floatArrayOf(ax,y+h,az)))
            cap[i]=floatArrayOf(ax,y+h,az)
        }
        addFace(Face(color,*cap))
    }

    private fun tree(x: Float,z: Float) {
        cylinder(x,0.08f,z,0.035f,0.27f,col("#8A795B"))
        dome(x,0.25f,z,0.22f,0.49f,col("#527C68"))
        dome(x,0.22f,z,0.24f,0.23f,col("#678C70"))
    }

    private fun build() {
        val stone = col("#E9DECB")
        val trim = col("#FCF0DC")
        val dark = col("#7B8A80")
        val gold = col("#D99942")
        box(0f,-0.15f,0f,4.5f,0.16f,2.8f,col("#A6BFA7"))
        box(0f,0f,0f,3.9f,0.10f,2.3f,col("#D4D4BB"))
        if(model == "palace") {
            box(0f,0.1f,0f,3.25f,0.76f,1.5f,stone)
            box(0f,0.86f,0f,3.42f,0.13f,1.65f,trim)
            box(0f,0.99f,0.05f,1.65f,0.43f,1.15f,stone)
            box(0f,1.4f,0.05f,1.9f,0.1f,1.35f,trim)
            cylinder(0f,1.48f,0.05f,0.72f,0.22f,trim)
            dome(0f,1.69f,0.05f,0.78f,0.72f,gold)
            cylinder(0f,2.37f,0.05f,0.105f,0.18f,trim)
            dome(0f,2.55f,0.05f,0.14f,0.12f,gold)
            cylinder(0f,2.64f,0.05f,0.025f,0.17f,dark)
            for (x in floatArrayOf(-1.24f,1.24f)) {
                box(x,0.98f,0f,0.65f,0.15f,0.88f,stone)
                dome(x,1.13f,0f,0.35f,0.29f,gold)
                cylinder(x,1.39f,0f,0.03f,0.17f,trim)
            }
            // Recessed entrances and paired fluted columns on the front façade.
            var x = -1.32f
            while (x < 1.5f) {
                box(x,0.2f,-0.758f,0.17f,0.48f,0.025f,dark)
                dome(x,0.68f,-0.78f,0.09f,0.10f,trim)
                cylinder(x-0.115f,0.14f,-0.83f,0.035f,0.61f,trim)
                box(x,0.77f,-0.82f,0.26f,0.05f,0.13f,trim)
                x += 0.33f
            }
            for (i in 0 until 4) box(0f,0.015f+i*0.025f,-1.08f+i*0.08f,1.7f,0.026f,0.32f,trim)
            for (x in floatArrayOf(-1.55f,1.55f)) {
                box(x,0.99f,-0.65f,0.19f,0.18f,0.23f,trim)
                dome(x,1.17f,-0.65f,0.10f,0.20f,trim)
            }
        } else if(model == "angel") {
            for (i in 0 until 4) box(0f,0.1f+i*0.13f,0f,1.55f-i*0.25f,0.13f,1.55f-i*0.25f,stone)
            cylinder(0f,0.62f,0f,0.19f,1.65f,stone)
            cylinder(0f,2.23f,0f,0.3f,0.13f,trim)
            cylinder(0f,2.35f,0f,0.09f,0.36f,gold)
            dome(0f,2.69f,0f,0.10f,0.14f,gold)
            addFace(Face(gold,floatArrayOf(0f,2.59f,0f),floatArrayOf(-0.53f,2.97f,0f),floatArrayOf(-0.30f,2.57f,0f)))
            addFace(Face(gold,floatArrayOf(0f,2.59f,0f),floatArrayOf(0.53f,2.97f,0f),floatArrayOf(0.30f,2.57f,0f)))
            box(0.2f,2.46f,0f,0.35f,0.06f,0.05f,gold)
        } else if(model == "soumaya") {
            val n = 30
            val levels = 16
            for (j in 0 until levels) for (i in 0 until n) {
                val p = Array(4) { FloatArray(3) }
                val ii = intArrayOf(i, i+1, i+1, i)
                val jj = intArrayOf(j, j, j+1, j+1)
                for (k in 0 until 4) {
                    val h = jj[k].toFloat()/levels
                    val a = ii[k]*Math.PI*2/n+h*0.4
                    val r = 0.8f-0.32f*sin(h*Math.PI).toFloat()+0.28f*h
                    p[k] = floatArrayOf(r*cos(a).toFloat(),0.12f+h*2.35f,r*0.65f*sin(a).toFloat())
                }
                addFace(Face(if ((i+j)%3==0) col("#BDC9C9") else col("#DFE5DF"), *p))
            }
            dome(0f,2.47f,0f,1.08f,0.025f,col("#E8ECE7"))
        } else if(model == "revolution") {
            for (x in floatArrayOf(-0.73f,0.73f)) for (z in floatArrayOf(-0.53f,0.53f))
                box(x,0.1f,z,0.42f,1.55f,0.42f,stone)
            box(0f,1.6f,0f,1.95f,0.3f,1.55f,stone)
            cylinder(0f,1.9f,0f,0.7f,0.24f,stone)
            dome(0f,2.14f,0f,0.77f,0.6f,col("#739A84"))
        } else {
            box(0f,0.1f,0f,2.8f,0.85f,1.35f,stone)
            box(0f,0.95f,0f,3f,0.1f,1.5f,trim)
            var x = -1.1f
            while (x < 1.3f) {
                box(x, 0.35f, -0.69f, 0.14f, 0.4f, 0.03f, dark)
                x += 0.36f
            }
            if(model == "cathedral") {
                for (x in floatArrayOf(-1.03f,1.03f)) {
                    box(x,1.05f,-0.4f,0.55f,0.8f,0.6f,stone)
                    box(x,1.85f,-0.4f,0.63f,0.08f,0.67f,trim)
                    dome(x,1.93f,-0.4f,0.29f,0.38f,gold)
                    cylinder(x,2.3f,-0.4f,0.025f,0.2f,dark)
                }
                dome(0f,1.05f,0.2f,0.53f,0.72f,gold)
            } else {
                cylinder(0.85f,1.04f,0.18f,0.34f,0.9f,stone)
                box(0.85f,1.91f,0.18f,0.78f,0.12f,0.78f,trim)
                for (i in 0 until 5) box(-1f+i*0.4f,1.03f,-0.66f,0.12f,0.15f,0.13f,trim)
            }
        }
        tree(-1.83f,0.7f)
        tree(1.83f,0.65f)
        tree(-1.9f,-0.7f)
        tree(1.9f,-0.65f)

    }

    override fun onSizeChanged(w: Int,h: Int,oldw: Int,oldh: Int) {
        super.onSizeChanged(w,h,oldw,oldh)
        geometryDirty=true; preview=null
        previewBounds.set(0f,0f,w.toFloat(),h.toFloat())
        previewKey="$model:$w:$h"
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if(width==0 || height==0) return
        if(!interactive) {
            val bitmap=preview ?: (previews.get(previewKey) ?: createPreview()).also { preview=it }
            paint.color=Color.WHITE
            canvas.drawBitmap(bitmap,null,previewBounds,paint)
        } else {
            if(geometryDirty) prepareGeometry(width,height)
            drawModel(canvas,width,height)
        }
    }

    private fun createPreview(): Bitmap {
        val factor=min(1f,768f/max(width,height))
        val w=max(1,Math.round(width*factor)); val h=max(1,Math.round(height*factor))
        val bitmap=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)
        prepareGeometry(w,h)
        drawModel(Canvas(bitmap),w,h)
        previews.put(previewKey,bitmap)
        return bitmap
    }

    private fun drawModel(canvas: Canvas,w: Int,h: Int) {
        val s=min(w/6f,h/4.2f)*zoom; val ox=w*0.5f; val oy=h*0.55f
        paint.color=Color.argb(20,23,65,51)
        canvas.drawOval(ox-s*2f,oy+s*0.60f,ox+s*2.1f,oy+s*1.19f,paint)
        paint.color=Color.WHITE
        canvas.drawVertices(Canvas.VertexMode.TRIANGLES,vertexFloats,vertices,0,null,0,colors,0,null,0,0,paint)
    }

    /** Keep primitive buffers and indexed loops on the hot path. */
    private fun prepareGeometry(w: Int,h: Int) {
        val cy=cos(yaw.toDouble()).toFloat(); val sy=sin(yaw.toDouble()).toFloat()
        val cp=cos(pitch.toDouble()).toFloat(); val sp=sin(pitch.toDouble()).toFloat()
        val s=min(w/6f,h/4.2f)*zoom; val ox=w*0.5f; val oy=h*0.55f
        var i=0
        while(i<mesh.size) {
            val face=mesh[i]; val dst=projected[i]; var depth=0f
            var k=0
            while(k<face.v.size) {
                val p=face.v[k]
                val x=p[0]*cy+p[2]*sy; val z=-p[0]*sy+p[2]*cy; val y=p[1]-1.1f
                val yy=y*cp+z*sp; val zz=-y*sp+z*cp; val perspective=7f/(7f+zz)
                dst[k*2]=ox+x*s*perspective; dst[k*2+1]=oy-yy*s*perspective; depth+=zz
                k++
            }
            depths[i]=depth/face.v.size
            val nx=face.nx*cy+face.nz*sy; var nz=-face.nx*sy+face.nz*cy
            val ny=face.ny*cp+nz*sp; nz=-face.ny*sp+nz*cp
            val light=if(face.nx==0f && face.ny==0f && face.nz==0f) 1f else 0.76f+0.24f*abs(-0.4f*nx+0.8f*ny-0.5f*nz)
            faceColors[i]=shade(face.color,light)
            i++
        }
        sortFaces(0,order.size-1)
        var offset=0
        for(face in order) {
            val p=projected[face]
            var k=1
            while(k<p.size/2-1) {
                offset=vertex(offset,p,0,faceColors[face])
                offset=vertex(offset,p,k,faceColors[face])
                offset=vertex(offset,p,k+1,faceColors[face])
                k++
            }
        }
        vertexFloats=offset; geometryDirty=false
    }

    private fun vertex(offset: Int,p: FloatArray,index: Int,color: Int): Int {
        colors[offset/2]=color
        vertices[offset]=p[index*2]; vertices[offset+1]=p[index*2+1]
        return offset+2
    }

    private fun sortFaces(lo: Int,hi: Int) {
        var i=lo; var j=hi; val pivot=depths[order[(lo+hi) ushr 1]]
        while(i<=j) {
            while(depths[order[i]]>pivot) i++
            while(depths[order[j]]<pivot) j--
            if(i<=j) { val t=order[i]; order[i]=order[j]; order[j]=t; i++; j-- }
        }
        if(lo<j) sortFaces(lo,j)
        if(i<hi) sortFaces(i,hi)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val detector=scale ?: return false
        detector.onTouchEvent(event)
        when(event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                lastX=event.x; lastY=event.y
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                if(!detector.isInProgress) {
                    yaw+=(event.x-lastX)*0.009f
                    pitch=(pitch+(event.y-lastY)*0.005f).coerceIn(0.08f,0.85f)
                    requestFrame()
                }
                lastX=event.x; lastY=event.y
            }
            MotionEvent.ACTION_UP -> { performClick(); parent.requestDisallowInterceptTouchEvent(false) }
            MotionEvent.ACTION_CANCEL -> parent.requestDisallowInterceptTouchEvent(false)
        }
        return true
    }

    override fun performClick(): Boolean { super.performClick(); return true }

    private companion object {
        val modelMeshes=HashMap<String,List<Face>>()
        val previews=object : LruCache<String,Bitmap>(12*1024*1024) {
            override fun sizeOf(key: String,value: Bitmap): Int = value.allocationByteCount
        }
        fun shade(color: Int,factor: Float): Int = Color.rgb(
            min(255,(Color.red(color)*factor).toInt()),
            min(255,(Color.green(color)*factor).toInt()),
            min(255,(Color.blue(color)*factor).toInt()),
        )
    }
}
