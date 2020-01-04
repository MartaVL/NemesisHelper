package es.nemesis.helper

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity()
{
    private var board : Board = Board("Intruders")

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        val clickListener = View.OnClickListener {
            view -> when(view.id)
            {
                R.id.menu_options ->
                    showMenuPopUp(view)
            }
        }

        menu_options.setOnClickListener(clickListener)
    }

    private fun showMenuPopUp(view : View)
    {
        var popupMenu: PopupMenu? = null;
        val wrapper = ContextThemeWrapper(this, R.style.popupMenuStyle)

        popupMenu = PopupMenu(wrapper, view)
        popupMenu.inflate(R.menu.menu_main)

        popupMenu.setOnMenuItemClickListener (PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            when(item!!.itemId)
            {
                R.id.action_legacy ->
                    Toast.makeText(this@MainActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                R.id.action_normal ->
                {
                    Toast.makeText(this@MainActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                    showBoardGame()

                }
                R.id.action_settings ->
                {
                    Toast.makeText(this@MainActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                }
             }

            true
        })

        popupMenu.show()
    }

    private fun showBoardGame()
    {
        //showConfigurationCharacterPopUp(view);
        val buttonTakeAlien = Button(this)
        buttonTakeAlien.id = View.generateViewId()

        val imagenTakeAlien = ImageView(this)
        imagenTakeAlien.id = View.generateViewId()

        val textTakeAlien = TextView(this)
        textTakeAlien.id = View.generateViewId()
        textTakeAlien.setTextColor(resources.getColor(R.color.red, null))

        val textGameBag = TextView(this)
        textGameBag.id = View.generateViewId()

        val layout = findViewById<ConstraintLayout>(R.id.content_main)

        // Create Button Dynamically
        buttonTakeAlien.setText(R.string.takeAlien)
        buttonTakeAlien.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        buttonTakeAlien.setOnClickListener {

            val(img, message) = board.alienBag.alienBagDevelopment()
            textGameBag.text = board.alienBag.game.toString()

            val res: Resources = resources
            val resID: Int = res.getIdentifier(img, "drawable", packageName)
            val drawable: Drawable = res.getDrawable(resID, null)

            imagenTakeAlien.setImageDrawable(drawable)

            textTakeAlien.text = message
        }

        // Add Button to LinearLayout
        layout?.addView(buttonTakeAlien)
        layout?.addView(textTakeAlien)
        layout?.addView(imagenTakeAlien)
        layout?.addView(textGameBag)

        val constraintSet = ConstraintSet()
        constraintSet.clone(layout)
        constraintSet.connect(buttonTakeAlien.id, ConstraintSet.TOP, textTakeAlien.id, ConstraintSet.BOTTOM)
        constraintSet.connect(textTakeAlien.id, ConstraintSet.TOP, textGameBag.id, ConstraintSet.BOTTOM)
        constraintSet.applyTo(layout)
    }

    private fun showConfigurationCharacterPopUp(view2 : View)
    {
        Log.d("NemesisHelper", "Partida normal");

        val inflater:LayoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // Inflate a custom view using layout inflater
        val view = inflater.inflate(R.layout.character_layout,null)

        // Initialize a new instance of popup window
        val popupWindow = PopupWindow(
            view, // Custom view to show in popup window
            LinearLayout.LayoutParams.WRAP_CONTENT, // Width of popup window
            LinearLayout.LayoutParams.WRAP_CONTENT // Window height
        )

        popupWindow.showAtLocation(
            view2, // Location to display popup window
            Gravity.START, // Exact position of layout to display popup
            0, // X offset
            0 // Y offset
        )
    }
}
