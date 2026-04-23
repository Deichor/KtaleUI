package org.deichor.ktaleui.ref

// TextField
class TextFieldRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val value get() = UISelector("#$id.Value")
    val placeholderText get() = UISelector("#$id.PlaceholderText")
    val passwordChar get() = UISelector("#$id.PasswordChar")
    val style get() = UISelector("#$id.Style")
    val placeholderStyle get() = UISelector("#$id.PlaceholderStyle")
    val decoration get() = UISelector("#$id.Decoration")
    val autoFocus get() = UISelector("#$id.AutoFocus")
    val autoSelectAll get() = UISelector("#$id.AutoSelectAll")
    val isReadOnly get() = UISelector("#$id.IsReadOnly")
    val maxLength get() = UISelector("#$id.MaxLength")
}

// CompactTextField
class CompactTextFieldRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val value get() = UISelector("#$id.Value")
    val placeholderText get() = UISelector("#$id.PlaceholderText")
    val passwordChar get() = UISelector("#$id.PasswordChar")
    val style get() = UISelector("#$id.Style")
    val placeholderStyle get() = UISelector("#$id.PlaceholderStyle")
    val decoration get() = UISelector("#$id.Decoration")
    val autoFocus get() = UISelector("#$id.AutoFocus")
    val autoSelectAll get() = UISelector("#$id.AutoSelectAll")
    val isReadOnly get() = UISelector("#$id.IsReadOnly")
    val maxLength get() = UISelector("#$id.MaxLength")
}

// MultilineTextField
class MultilineTextFieldRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val scrollbarStyle get() = UISelector("#$id.ScrollbarStyle")
    val value get() = UISelector("#$id.Value")
    val style get() = UISelector("#$id.Style")
    val placeholderStyle get() = UISelector("#$id.PlaceholderStyle")
    val decoration get() = UISelector("#$id.Decoration")
    val autoFocus get() = UISelector("#$id.AutoFocus")
    val autoSelectAll get() = UISelector("#$id.AutoSelectAll")
    val isReadOnly get() = UISelector("#$id.IsReadOnly")
    val maxLength get() = UISelector("#$id.MaxLength")
    val maxLines get() = UISelector("#$id.MaxLines")
    val maxVisibleLines get() = UISelector("#$id.MaxVisibleLines")
    val autoGrow get() = UISelector("#$id.AutoGrow")
    val contentPadding get() = UISelector("#$id.ContentPadding")
    val placeholderText get() = UISelector("#$id.PlaceholderText")
}

// NumberField
class NumberFieldRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val value get() = UISelector("#$id.Value")
    val passwordChar get() = UISelector("#$id.PasswordChar")
    val format get() = UISelector("#$id.Format")
    val style get() = UISelector("#$id.Style")
    val placeholderStyle get() = UISelector("#$id.PlaceholderStyle")
    val decoration get() = UISelector("#$id.Decoration")
    val autoFocus get() = UISelector("#$id.AutoFocus")
    val autoSelectAll get() = UISelector("#$id.AutoSelectAll")
    val isReadOnly get() = UISelector("#$id.IsReadOnly")
    val maxLength get() = UISelector("#$id.MaxLength")
}

// CodeEditor
class CodeEditorRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val scrollbarStyle get() = UISelector("#$id.ScrollbarStyle")
    val value get() = UISelector("#$id.Value")
    val language get() = UISelector("#$id.Language")
    val lineNumberWidth get() = UISelector("#$id.LineNumberWidth")
    val lineNumberBackground get() = UISelector("#$id.LineNumberBackground")
    val lineNumberTextColor get() = UISelector("#$id.LineNumberTextColor")
    val lineNumberPadding get() = UISelector("#$id.LineNumberPadding")
    val style get() = UISelector("#$id.Style")
    val placeholderStyle get() = UISelector("#$id.PlaceholderStyle")
    val decoration get() = UISelector("#$id.Decoration")
    val autoFocus get() = UISelector("#$id.AutoFocus")
    val autoSelectAll get() = UISelector("#$id.AutoSelectAll")
    val isReadOnly get() = UISelector("#$id.IsReadOnly")
    val maxLength get() = UISelector("#$id.MaxLength")
    val maxLines get() = UISelector("#$id.MaxLines")
    val maxVisibleLines get() = UISelector("#$id.MaxVisibleLines")
    val autoGrow get() = UISelector("#$id.AutoGrow")
    val contentPadding get() = UISelector("#$id.ContentPadding")
    val placeholderText get() = UISelector("#$id.PlaceholderText")
}
