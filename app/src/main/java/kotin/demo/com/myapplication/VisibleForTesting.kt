package kotin.demo.com.myapplication

import android.support.annotation.VisibleForTesting

/**
 * Created by Kasper Tvede on 29-07-2017.
 */


open class VisibleForTestingExample {


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onlyInside(): Int {
        return 42
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    fun notSoSectret(): Int {
        return onlyInside() + 2 //this is NOT Flagged ?? so other visible for testings works ?
    }


    @VisibleForTesting
    fun secret2() {

    }

    @VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
    fun superOpen() {

    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun noneOpen() {

    }

    fun fromPrivate() {
        onlyInside()   //is wrongly flagged, this is still in private scope.
        notSoSectret()
        secret2()  //is wrongly flagged, this is still in private scope.
        superOpen()
        noneOpen()
    }
}

class InheritsExample : VisibleForTestingExample() {

    fun fromInherit() {
        onlyInside()
        notSoSectret()
        secret2()
        superOpen()
        noneOpen()

    }
}

class NotInhrited() {

    fun testAll() {
        val toTest = VisibleForTestingExample()

        toTest.noneOpen()
        toTest.superOpen()
        toTest.secret2()
        toTest.notSoSectret() //Wrongly, this is not flagged, but we are not inheriting from it ?????
        toTest.onlyInside()

    }
}