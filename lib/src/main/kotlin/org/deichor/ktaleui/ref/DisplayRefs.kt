package org.deichor.ktaleui.ref

// Label
class LabelRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val text get() = UISelector("#$id.Text")
    val textSpans get() = UISelector("#$id.TextSpans")
    val style get() = UISelector("#$id.Style")
}

// ProgressBar
class ProgressBarRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val value get() = UISelector("#$id.Value")
    val bar get() = UISelector("#$id.Bar")
    val barTexturePath get() = UISelector("#$id.BarTexturePath")
    val effectTexturePath get() = UISelector("#$id.EffectTexturePath")
    val effectWidth get() = UISelector("#$id.EffectWidth")
    val effectHeight get() = UISelector("#$id.EffectHeight")
    val effectOffset get() = UISelector("#$id.EffectOffset")
    val alignment get() = UISelector("#$id.Alignment")
    val direction get() = UISelector("#$id.Direction")
}

// CircularProgressBar
class CircularProgressBarRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val value get() = UISelector("#$id.Value")
    val color get() = UISelector("#$id.Color")
}

// Sprite
class SpriteRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val frame get() = UISelector("#$id.Frame")
    val isPlaying get() = UISelector("#$id.IsPlaying")
    val autoPlay get() = UISelector("#$id.AutoPlay")
    val texturePath get() = UISelector("#$id.TexturePath")
    val framesPerSecond get() = UISelector("#$id.FramesPerSecond")
    val angle get() = UISelector("#$id.Angle")
    val repeatCount get() = UISelector("#$id.RepeatCount")
}

// TimerLabel
class TimerLabelRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val seconds get() = UISelector("#$id.Seconds")
    val text get() = UISelector("#$id.Text")
    val textSpans get() = UISelector("#$id.TextSpans")
    val direction get() = UISelector("#$id.Direction")
    val paused get() = UISelector("#$id.Paused")
    val style get() = UISelector("#$id.Style")
}

// HotkeyLabel
class HotkeyLabelRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val text get() = UISelector("#$id.Text")
    val textSpans get() = UISelector("#$id.TextSpans")
    val style get() = UISelector("#$id.Style")
}

// SceneBlur
class SceneBlurRef @PublishedApi internal constructor(id: String) : ElementRef(id)
