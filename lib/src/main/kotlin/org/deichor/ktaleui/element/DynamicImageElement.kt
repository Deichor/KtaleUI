package org.deichor.ktaleui.element

/**
 * A container element that represents a dynamic image loaded at runtime.
 * Serializes as a Group in .ui output. The background texture path is set
 * dynamically at runtime via [DynamicImageManager.sendImage].
 *
 * Usage in DSL:
 * ```
 * dynamicImage("AvatarImage") {
 *     imageUrl = "https://hyvatar.io/render/PlayerName?size=128"
 *     anchor { width = 64; height = 64 }
 * }
 * ```
 *
 * The [imageUrl] and [imageFilePath] properties are metadata for the
 * runtime system — they are NOT serialized into the .ui file.
 * At runtime, call `page.setDynamicImage(elementId, url)` to load
 * and display the image.
 */
class DynamicImageElement : ContainerElement("Group") {

    /** Remote URL to load at runtime. Not serialized to .ui output. */
    var imageUrl: String? = null

    /** Local file path alternative. Not serialized to .ui output. */
    var imageFilePath: String? = null

    // Default background: first dynamic image slot placeholder
    init {
        background = "UI/Custom/Pages/Elements/DynamicImage1.png"
    }
}
