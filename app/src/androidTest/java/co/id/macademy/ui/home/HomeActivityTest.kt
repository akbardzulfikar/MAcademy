package co.id.macademy.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import co.id.macademy.R
import co.id.macademy.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dummyCourse = DataDummy.generateDummyCourses()

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun loadCourses() {
        delay2seconds()
        Espresso.onView(withId(R.id.rv_academy))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_academy))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyCourse.size))
    }

    @Test
    fun loadDetailCourse() {
        delay2seconds()
        Espresso.onView(withId(R.id.rv_academy))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        delay2seconds()
        Espresso.onView(withId(R.id.text_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.text_title))
            .check(ViewAssertions.matches(withText(dummyCourse[0].title)))
        Espresso.onView(withId(R.id.text_date))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.text_date))
            .check(ViewAssertions.matches(ViewMatchers.withText("Deadline ${dummyCourse[0].deadline}")))
    }

    @Test
    fun loadModule() {
        delay2seconds()
        Espresso.onView(withId(R.id.rv_academy))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        delay2seconds()
        Espresso.onView(withId(R.id.btn_start)).perform(ViewActions.click())
        delay2seconds()
        Espresso.onView(withId(R.id.rv_module))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadDetailModule() {
        delay2seconds()
        Espresso.onView(withId(R.id.rv_academy))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        delay2seconds()
        Espresso.onView(withId(R.id.btn_start)).perform(ViewActions.click())
        delay2seconds()
        Espresso.onView(withId(R.id.rv_module))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        delay2seconds()
        Espresso.onView(withId(R.id.web_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadBookmarks() {
        Espresso.onView(ViewMatchers.withText("Bookmark")).perform(ViewActions.click())
        delay2seconds()
        Espresso.onView(withId(R.id.rv_bookmark))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_bookmark))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyCourse.size))
    }

    private fun delay2seconds() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}