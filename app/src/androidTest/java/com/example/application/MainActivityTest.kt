package com.example.application


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val activityTest = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkMenu() {
        // Проверяем, что приложение открыто
        onView(withId(R.id.container)).check(matches(isDisplayed()))

        // Проверяем наличие кнопок меню
        onView(withId(R.id.navigation_home)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_dashboard)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_notifications)).check(matches(isDisplayed()))
    }
    @Test
    fun checkBtn1() {
        // Проверяем, что приложение открыто
        onView(withId(R.id.container)).check(matches(isDisplayed()))

        // Нажимаем на перый элемент меню
        onView(withId(R.id.navigation_home)).perform(click())

        // Проверяем, что после нажатия на элемент меню отображается соответствующий фрагмент
        onView(withId(R.id.nav_host_fragment_activity_main))
            .check(matches(hasDescendant(withId(R.id.begin_studies_button))));
    }

    @Test
    fun checkBtn2() {
        // Проверяем, что приложение открыто
        onView(withId(R.id.container)).check(matches(isDisplayed()))

        // Нажимаем на второй элемент меню
        onView(withId(R.id.navigation_dashboard)).perform(click())

        // Проверяем, что после нажатия на элемент меню отображается соответствующий фрагмент
        onView(withId(R.id.nav_host_fragment_activity_main))
            .check(matches(hasDescendant(withText("Переводчик с английского"))));
    }

    @Test
    fun checkBtn3() {
        // Проверяем, что приложение открыто
        onView(withId(R.id.container)).check(matches(isDisplayed()))

        // Нажимаем на третий элемент меню
        onView(withId(R.id.navigation_notifications)).perform(click())
    }

    @Test
    fun checkTest() {
        // Проверяем, что приложение открыто
        onView(withId(R.id.container)).check(matches(isDisplayed()))

        // Нажимаем на элемент меню
        onView(withId(R.id.navigation_dashboard)).perform(click())

        // Проверяем, что после нажатия на элемент меню отображается соответствующий фрагмент
        onView(withId(R.id.nav_host_fragment_activity_main))
            .check(matches(hasDescendant(withText("Переводчик с английского"))));

        onView(withId(R.id.nav_host_fragment_activity_main))
            .check(matches(hasDescendant(withId(R.id.begin_button))));

        onView(withId(R.id.nav_host_fragment_activity_main))
            .check(matches(hasDescendant(withId(R.id.test_image1))));

        // Получаем и нажимаем на кнопку начала теста
        onView(withId(R.id.begin_button)).perform(click());

        // Наличие вариантов ответа
        onView(withId(R.id.nav_host_fragment_activity_main))
            .check(matches(hasDescendant(withId(R.id.variant1))));
        onView(withId(R.id.nav_host_fragment_activity_main))
            .check(matches(hasDescendant(withId(R.id.variant2))));
        onView(withId(R.id.nav_host_fragment_activity_main))
            .check(matches(hasDescendant(withId(R.id.variant3))));
        onView(withId(R.id.nav_host_fragment_activity_main))
            .check(matches(hasDescendant(withId(R.id.variant4))));

        // Наличие остальных элементов
        onView(withId(R.id.nav_host_fragment_activity_main))
            .check(matches(hasDescendant(withId(R.id.question))));
        onView(withId(R.id.nav_host_fragment_activity_main))
            .check(matches(hasDescendant(withId(R.id.next))));
        onView(withId(R.id.nav_host_fragment_activity_main))
            .check(matches(hasDescendant(withId(R.id.result))));
        onView(withId(R.id.nav_host_fragment_activity_main))
            .check(matches(hasDescendant(withId(R.id.count))));

        onView(withId(R.id.next)).perform(click());
    }

    @Test
    fun checkHomePage() {
        // Проверяем, что приложение открыто
        onView(withId(R.id.container)).check(matches(isDisplayed()))

        // Нажимаем элемент меню
        onView(withId(R.id.navigation_home)).perform(click())

        // Проверяем, что после нажатия на элемент меню отображается соответствующий фрагмент
        onView(withId(R.id.nav_host_fragment_activity_main))
            .check(matches(hasDescendant(withId(R.id.begin_studies_button))));

        // Проверка работы кнопки
        onView(withId(R.id.begin_studies_button)).perform(click());
        onView(withId(R.id.nav_host_fragment_activity_main))
            .check(matches(hasDescendant(withText("Переводчик с английского"))));
    }

    @Test
    fun checkTestsPage() {
        // Проверяем, что приложение открыто
        onView(withId(R.id.container)).check(matches(isDisplayed()))

        // Нажимаем на элемент меню
        onView(withId(R.id.navigation_dashboard)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_dashboard)).perform(click())

        // Проверяем, что после нажатия на элемент меню отображается соответствующий фрагмент
        onView(withId(R.id.begin_button)).check(matches(isDisplayed()));

        // Проверяем работу кнопки начала теста
        onView(withId(R.id.begin_button)).perform(click());

        onView(withId(R.id.variant1)).check(matches(isDisplayed()))
        onView(withId(R.id.variant2)).check(matches(isDisplayed()))
        onView(withId(R.id.variant3)).check(matches(isDisplayed()))
        onView(withId(R.id.variant4)).check(matches(isDisplayed()))
    }

    @Test
    fun checkProfilePage() {
        // Проверяем, что приложение открыто
        onView(withId(R.id.container)).check(matches(isDisplayed()))

        // Нажимаем на третий элемент меню
        onView(withId(R.id.navigation_notifications)).perform(click())

        // Проверка наличия элементов
        onView(withId(R.id.email_et)).check(matches(isDisplayed()))
        onView(withId(R.id.password_et)).check(matches(isDisplayed()))
        onView(withId(R.id.login_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.reg_btn)).check(matches(isDisplayed()))
    }
}
