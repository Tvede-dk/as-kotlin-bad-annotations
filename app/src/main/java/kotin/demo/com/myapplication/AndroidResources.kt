package kotin.demo.com.myapplication

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.widget.TextView

/**
 * Created by Kasper Tvede on 27-07-2017.
 */

@DrawableRes
fun getDrawable(): Int {
    return R.drawable.abc_ab_share_pack_mtrl_alpha
}


fun useDrawable() {
    val myDrawable = getDrawable()
    TextView(MainActivity()).setText(myDrawable) //works
}


fun myFunFunction(@StringRes testStr: Int, @DrawableRes myImage: Int, view: TextView) {
    view.setText(testStr)
    view.setHintTextColor(myImage)
}


//works
fun useMyFunFunction() {
    myFunFunction(R.string.abc_action_bar_home_description, R.drawable.abc_ab_share_pack_mtrl_alpha, TextView(MainActivity()))
}

fun TextView.myFunction(@StringRes testStr: Int, @DrawableRes myImage: Int) {
    setText(testStr)
    setBackgroundResource(myImage)
}

fun useMyExtension() {
    TextView(MainActivity()).myFunction(R.drawable.abc_cab_background_internal_bg, R.color.abc_tint_spinner)
    //now drawable is flagged as if it should be a stringRes , and the string input is not flagged at all ???
    //if we pass a string as the second argument it belives all is ok ?
    TextView(MainActivity()).myFunction(R.drawable.abc_cab_background_internal_bg, R.string.app_name)
    //which is clearly wrong..
}





