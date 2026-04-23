package org.deichor.ktaleui.ref

// TextButton
class TextButtonRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val text get() = UISelector("#$id.Text")
    val textSpans get() = UISelector("#$id.TextSpans")
    val disabled get() = UISelector("#$id.Disabled")
    val style get() = UISelector("#$id.Style")
}

// BackButton
class BackButtonRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val disabled get() = UISelector("#$id.Disabled")
    val style get() = UISelector("#$id.Style")
}

// ActionButton
class ActionButtonRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val actionName get() = UISelector("#$id.ActionName")
    val keyBindingLabel get() = UISelector("#$id.KeyBindingLabel")
    val bindingModifier1Label get() = UISelector("#$id.BindingModifier1Label")
    val bindingModifier2Label get() = UISelector("#$id.BindingModifier2Label")
    val isAvailable get() = UISelector("#$id.IsAvailable")
    val isHoldBinding get() = UISelector("#$id.IsHoldBinding")
    val alignment get() = UISelector("#$id.Alignment")
    val disabled get() = UISelector("#$id.Disabled")
    val style get() = UISelector("#$id.Style")
}

// TabButton
class TabButtonRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val disabled get() = UISelector("#$id.Disabled")
    val style get() = UISelector("#$id.Style")
}

// DropdownEntry
class DropdownEntryRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val value get() = UISelector("#$id.Value")
    val text get() = UISelector("#$id.Text")
    val iconTexturePath get() = UISelector("#$id.IconTexturePath")
}
