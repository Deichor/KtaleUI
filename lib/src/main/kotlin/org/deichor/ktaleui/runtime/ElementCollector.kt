package org.deichor.ktaleui.runtime

import org.deichor.ktaleui.element.ContainerElement
import org.deichor.ktaleui.element.EventHandler
import org.deichor.ktaleui.element.UIElement

internal fun collectHandlers(elements: List<UIElement>): Map<String, Map<String, EventHandler>> {
    val result = mutableMapOf<String, Map<String, EventHandler>>()
    collectHandlersRecursive(elements, result)
    return result
}

private fun collectHandlersRecursive(
    elements: List<UIElement>,
    result: MutableMap<String, Map<String, EventHandler>>,
) {
    for (element in elements) {
        val id = element.id
        if (id != null && element.eventHandlers.isNotEmpty()) {
            result[id] = element.eventHandlers.toMap()
        }
        if (element is ContainerElement) {
            collectHandlersRecursive(element.children, result)
        }
    }
}

internal fun collectElementsWithEvents(elements: List<UIElement>): List<UIElement> {
    val result = mutableListOf<UIElement>()
    collectElementsRecursive(elements, result)
    return result
}

private fun collectElementsRecursive(elements: List<UIElement>, result: MutableList<UIElement>) {
    for (element in elements) {
        if (element.id != null && element.eventHandlers.isNotEmpty()) {
            result.add(element)
        }
        if (element is ContainerElement) {
            collectElementsRecursive(element.children, result)
        }
    }
}
