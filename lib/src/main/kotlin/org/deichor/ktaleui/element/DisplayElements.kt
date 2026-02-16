package org.deichor.ktaleui.element

import org.deichor.ktaleui.enums.ProgressBarAlignment
import org.deichor.ktaleui.enums.ProgressBarDirection
import org.deichor.ktaleui.enums.TimerDirection
import org.deichor.ktaleui.property.*

// Label - text display
class LabelElement : LeafElement("Label") {
    var text: String? = null
    var textSpans: List<LabelSpan>? = null
    var style: LabelStyle? = null

    fun onLinkActivating(handler: EventHandler) { eventHandlers["LinkActivating"] = handler }
    fun onTagMouseEntered(handler: EventHandler) { eventHandlers["TagMouseEntered"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            text?.let { put("Text", it) }
            textSpans?.let { put("TextSpans", it) }
            style?.let { put("Style", it) }
        }
    }
}

// ProgressBar
class ProgressBarElement : LeafElement("ProgressBar") {
    var value: Float? = null
    var bar: Any? = null // PatchStyle or String
    var barTexturePath: String? = null
    var effectTexturePath: String? = null
    var effectWidth: Int? = null
    var effectHeight: Int? = null
    var effectOffset: Int? = null
    var alignment: ProgressBarAlignment? = null
    var direction: ProgressBarDirection? = null

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            value?.let { put("Value", it) }
            bar?.let { put("Bar", it) }
            barTexturePath?.let { put("BarTexturePath", it) }
            effectTexturePath?.let { put("EffectTexturePath", it) }
            effectWidth?.let { put("EffectWidth", it) }
            effectHeight?.let { put("EffectHeight", it) }
            effectOffset?.let { put("EffectOffset", it) }
            alignment?.let { put("Alignment", it) }
            direction?.let { put("Direction", it) }
        }
    }
}

// CircularProgressBar
class CircularProgressBarElement : LeafElement("CircularProgressBar") {
    var value: Float? = null
    var color: String? = null

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            value?.let { put("Value", it) }
            color?.let { put("Color", it) }
        }
    }
}

// Sprite - spritesheet animation
class SpriteElement : LeafElement("Sprite") {
    var frame: SpriteFrame? = null
    var isPlaying: Boolean? = null
    var autoPlay: Boolean? = null
    var texturePath: String? = null
    var framesPerSecond: Int? = null
    var angle: Float? = null
    var repeatCount: Int? = null

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            frame?.let { put("Frame", it) }
            isPlaying?.let { put("IsPlaying", it) }
            autoPlay?.let { put("AutoPlay", it) }
            texturePath?.let { put("TexturePath", it) }
            framesPerSecond?.let { put("FramesPerSecond", it) }
            angle?.let { put("Angle", it) }
            repeatCount?.let { put("RepeatCount", it) }
        }
    }
}

// TimerLabel
class TimerLabelElement : LeafElement("TimerLabel") {
    var seconds: Int? = null
    var text: String? = null
    var textSpans: List<LabelSpan>? = null
    var direction: TimerDirection? = null
    var paused: Boolean? = null
    var style: LabelStyle? = null

    fun onLinkActivating(handler: EventHandler) { eventHandlers["LinkActivating"] = handler }
    fun onTagMouseEntered(handler: EventHandler) { eventHandlers["TagMouseEntered"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            seconds?.let { put("Seconds", it) }
            text?.let { put("Text", it) }
            textSpans?.let { put("TextSpans", it) }
            direction?.let { put("Direction", it) }
            paused?.let { put("Paused", it) }
            style?.let { put("Style", it) }
        }
    }
}

// HotkeyLabel
class HotkeyLabelElement : LeafElement("HotkeyLabel") {
    var text: String? = null
    var textSpans: List<LabelSpan>? = null
    var style: LabelStyle? = null

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            text?.let { put("Text", it) }
            textSpans?.let { put("TextSpans", it) }
            style?.let { put("Style", it) }
        }
    }
}

// SceneBlur - only common properties
class SceneBlurElement : LeafElement("SceneBlur")
