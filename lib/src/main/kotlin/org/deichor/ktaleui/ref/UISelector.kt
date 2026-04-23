package org.deichor.ktaleui.ref

import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder

/**
 * A type-safe wrapper for UI element property selectors.
 *
 * Instead of writing raw strings like `"#HpLabel.Text"`,
 * use typed [ElementRef] subclasses to get compile-time checked selectors:
 *
 * ```kotlin
 * val hpLabel = LabelRef("HpLabel")
 *
 * page.update {
 *     set(hpLabel.text, "50")  // compile-time safe!
 * }
 * ```
 */
@JvmInline
value class UISelector(val path: String)

// Type-safe overloads for UICommandBuilder.set() using UISelector

fun UICommandBuilder.set(selector: UISelector, value: String): UICommandBuilder =
    set(selector.path, value)

fun UICommandBuilder.set(selector: UISelector, value: Boolean): UICommandBuilder =
    set(selector.path, value)

fun UICommandBuilder.set(selector: UISelector, value: Int): UICommandBuilder =
    set(selector.path, value)

fun UICommandBuilder.set(selector: UISelector, value: Float): UICommandBuilder =
    set(selector.path, value)

fun UICommandBuilder.set(selector: UISelector, value: Double): UICommandBuilder =
    set(selector.path, value)

fun <T : Any> UICommandBuilder.set(selector: UISelector, value: List<T>): UICommandBuilder =
    set(selector.path, value)

fun <T : Any> UICommandBuilder.set(selector: UISelector, value: Array<T>): UICommandBuilder =
    set(selector.path, value)
