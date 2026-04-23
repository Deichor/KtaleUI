package org.deichor.ktaleui.ref

/**
 * Base class for type-safe UI element references.
 *
 * Each subclass corresponds to a specific element type and exposes
 * only the properties valid for that type as [UISelector] getters.
 * This ensures that invalid selectors like `#Label.Value` cause
 * compile-time errors instead of runtime errors.
 *
 * Usage:
 * ```kotlin
 * val hpLabel = LabelRef("HpLabel")
 * val hpBar = ProgressBarRef("HpBar")
 *
 * val ui = ktaleUI("myUI") {
 *     label(hpLabel) { text = "100" }
 *     progressBar(hpBar) { value = 1.0f }
 * }
 *
 * page.update {
 *     set(hpLabel.text, "50")     // compiles
 *     set(hpBar.value, 0.5f)     // compiles
 *     // set(hpLabel.value, 0.5f) // won't compile!
 * }
 * ```
 */
open class ElementRef @PublishedApi internal constructor(val id: String) {
    // Common properties shared by all UIElement types
    val visible get() = UISelector("#$id.Visible")
    val hitTestVisible get() = UISelector("#$id.HitTestVisible")
    val tooltipText get() = UISelector("#$id.TooltipText")
    val tooltipTextSpans get() = UISelector("#$id.TooltipTextSpans")
    val textTooltipStyle get() = UISelector("#$id.TextTooltipStyle")
    val textTooltipShowDelay get() = UISelector("#$id.TextTooltipShowDelay")
    val anchor get() = UISelector("#$id.Anchor")
    val padding get() = UISelector("#$id.Padding")
    val flexWeight get() = UISelector("#$id.FlexWeight")
    val contentWidth get() = UISelector("#$id.ContentWidth")
    val contentHeight get() = UISelector("#$id.ContentHeight")
    val autoScrollDown get() = UISelector("#$id.AutoScrollDown")
    val keepScrollPosition get() = UISelector("#$id.KeepScrollPosition")
    val mouseWheelScrollBehaviour get() = UISelector("#$id.MouseWheelScrollBehaviour")
    val background get() = UISelector("#$id.Background")
    val maskTexturePath get() = UISelector("#$id.MaskTexturePath")
    val outlineColor get() = UISelector("#$id.OutlineColor")
    val outlineSize get() = UISelector("#$id.OutlineSize")
    val overscroll get() = UISelector("#$id.Overscroll")
}

/**
 * Base ref for container elements. Adds layout and scrollbar properties.
 */
open class ContainerRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val layoutMode get() = UISelector("#$id.LayoutMode")
    val scrollbarStyle get() = UISelector("#$id.ScrollbarStyle")
}
