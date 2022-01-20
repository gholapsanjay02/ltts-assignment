package com.example.practice




import androidx.test.core.app.ActivityScenario
import org.junit.Test
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity



class MainActivityTest {


    val activityScenario = ActivityScenario.launch(MainActivity::class.java)


    @Test
    fun testLaunch()
    {
        val originalActivityState = activityScenario.state
        print("current state ${originalActivityState}")
        activityScenario.recreate()
    }

    @Test fun testEventCreate() {
        val scenario = launchActivity<MainActivity>()
        scenario.moveToState(Lifecycle.State.CREATED)
    }

    @Test
    fun checkForUpdate() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity {
            checkForUpdate()
        }
    }


}