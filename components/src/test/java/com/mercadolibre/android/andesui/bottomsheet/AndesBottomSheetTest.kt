package com.mercadolibre.android.andesui.bottomsheet

import android.graphics.Color
import android.os.Build
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.View
import android.widget.FrameLayout
import com.mercadolibre.android.andesui.BuildConfig
import com.mercadolibre.android.andesui.bottomsheet.state.AndesBottomSheetState
import com.nhaarman.mockitokotlin2.anyOrNull
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.internal.util.reflection.FieldSetter
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [Build.VERSION_CODES.LOLLIPOP] )
class AndesBottomSheetTest {
    private var context = RuntimeEnvironment.application
    private lateinit var andesBottomSheet: AndesBottomSheet

    @Test
    fun `only context constructor`() {
        andesBottomSheet = AndesBottomSheet(context)

        assertEquals(andesBottomSheet.peekHeight, DEFAULT_PEEK_HEIGHT)
        assertEquals(andesBottomSheet.cornerRadius, DEFAULT_CORNER_RADIUS)
        assertEquals(andesBottomSheet.bottomSheetBackgroundColor, DEFAULT_BACKGROUND_COLOR)
        assertEquals(andesBottomSheet.state, DEFAULT_BOTTOM_SHEET_STATE)
    }

    @Test
    fun `params constructor`() {
        val peekHeight = 250
        val cornerRadius = 25
        andesBottomSheet = AndesBottomSheet(context, peekHeight, cornerRadius, Color.BLACK, AndesBottomSheetState.EXPANDED)

        assertEquals(andesBottomSheet.peekHeight, peekHeight)
        assertEquals(andesBottomSheet.cornerRadius, cornerRadius)
        assertEquals(andesBottomSheet.bottomSheetBackgroundColor, Color.BLACK)
        assertEquals(andesBottomSheet.state, AndesBottomSheetState.EXPANDED)
    }

    @Test
    fun `set fragment should set fragment`() {
        val mockFragment = mock(Fragment::class.java)
        val mockFragmentManager = mockFragmentManager()

        andesBottomSheet = AndesBottomSheet(context)
        andesBottomSheet.setFragment(mockFragmentManager, mockFragment)

        verify(mockFragmentManager).beginTransaction()
    }

    @Test
    fun `set View should call addView on Frame Layout`() {
        val mockFrameLayout = mock(FrameLayout::class.java)
        val mockView = mock(View::class.java)
        andesBottomSheet = AndesBottomSheet(context)
        FieldSetter.setField(andesBottomSheet, andesBottomSheet::class.java.getDeclaredField("frameView"), mockFrameLayout)

        andesBottomSheet.setView(mockView)

        verify(mockFrameLayout).addView(mockView)
    }

    @Test
    fun `removeViews should not call removeAllViews on frameView if no views are attached`() {
        val mockFrameLayout = mock(FrameLayout::class.java)
        andesBottomSheet = AndesBottomSheet(context)
        FieldSetter.setField(andesBottomSheet, andesBottomSheet::class.java.getDeclaredField("frameView"), mockFrameLayout)
        `when`(mockFrameLayout.childCount).thenReturn(0)

        andesBottomSheet.removeViews()

        verify(mockFrameLayout, never()).removeAllViews()
    }

    @Test
    fun `removeViews should call removeAllViews on frameView if views are attached`() {
        val mockFrameLayout = mock(FrameLayout::class.java)
        andesBottomSheet = AndesBottomSheet(context)
        FieldSetter.setField(andesBottomSheet, andesBottomSheet::class.java.getDeclaredField("frameView"), mockFrameLayout)
        `when`(mockFrameLayout.childCount).thenReturn(1)

        andesBottomSheet.removeViews()

        verify(mockFrameLayout).removeAllViews()
    }

    @Test
    fun `expand method should set state to expanded`() {
        andesBottomSheet = AndesBottomSheet(context)
        andesBottomSheet.state = AndesBottomSheetState.COLLAPSED

        andesBottomSheet.expand()

        assertEquals(andesBottomSheet.state, AndesBottomSheetState.EXPANDED)
    }

    @Test
    fun `expand method should set state to expanded even if already expanded`() {
        andesBottomSheet = AndesBottomSheet(context)
        andesBottomSheet.state = AndesBottomSheetState.EXPANDED

        andesBottomSheet.expand()

        assertEquals(andesBottomSheet.state, AndesBottomSheetState.EXPANDED)
    }

    @Test
    fun `collapse method should set state to collapsed`() {
        andesBottomSheet = AndesBottomSheet(context)
        andesBottomSheet.state = AndesBottomSheetState.EXPANDED

        andesBottomSheet.collapse()

        assertEquals(andesBottomSheet.state, AndesBottomSheetState.COLLAPSED)
    }

    @Test
    fun `collapse method should set state to collapsed even if already collapsed`() {
        andesBottomSheet = AndesBottomSheet(context)
        andesBottomSheet.state = AndesBottomSheetState.COLLAPSED

        andesBottomSheet.collapse()

        assertEquals(andesBottomSheet.state, AndesBottomSheetState.COLLAPSED)
    }

    @Test
    fun `listener should be notified on expand`() {
        andesBottomSheet = AndesBottomSheet(context)
        val mockListener = mock(BottomSheetListener::class.java)

        andesBottomSheet.setBottomSheetListener(mockListener)
        andesBottomSheet.expand()

        verify(mockListener).onExpanded()
    }

    @Test
    fun `listener should not be notified on expand if already expanded`() {
        andesBottomSheet = AndesBottomSheet(context)
        andesBottomSheet.state = AndesBottomSheetState.EXPANDED
        val mockListener = mock(BottomSheetListener::class.java)

        andesBottomSheet.setBottomSheetListener(mockListener)
        andesBottomSheet.expand()

        verify(mockListener, never()).onExpanded()
    }

    @Test
    fun `listener should be notified on collapse`() {
        andesBottomSheet = AndesBottomSheet(context)
        andesBottomSheet.state = AndesBottomSheetState.EXPANDED
        val mockListener = mock(BottomSheetListener::class.java)

        andesBottomSheet.setBottomSheetListener(mockListener)
        andesBottomSheet.collapse()

        verify(mockListener).onCollapsed()
    }

    @Test
    fun `listener should not be notified on collapse if already collapsed`() {
        andesBottomSheet = AndesBottomSheet(context)
        andesBottomSheet.state = AndesBottomSheetState.COLLAPSED
        val mockListener = mock(BottomSheetListener::class.java)

        andesBottomSheet.setBottomSheetListener(mockListener)
        andesBottomSheet.collapse()

        verify(mockListener, never()).onCollapsed()
    }

    private fun mockFragmentManager() : FragmentManager {
        val fragmentTransaction = mock(FragmentTransaction::class.java)
        `when`(fragmentTransaction.replace(anyOrNull(), any(Fragment::class.java))).thenReturn(fragmentTransaction)
        `when`(fragmentTransaction.commit()).thenReturn(0)

        val fragmentManager = mock(FragmentManager::class.java)
        `when`(fragmentManager.beginTransaction()).thenReturn(fragmentTransaction)

        return fragmentManager
    }

    companion object {
        private const val DEFAULT_PEEK_HEIGHT = 0
        private const val DEFAULT_CORNER_RADIUS = 0
        private const val DEFAULT_BACKGROUND_COLOR = Color.TRANSPARENT
        private val DEFAULT_BOTTOM_SHEET_STATE = AndesBottomSheetState.COLLAPSED
    }
}