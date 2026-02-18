package org.deichor.ktaleui

import com.hypixel.hytale.common.plugin.PluginIdentifier
import com.hypixel.hytale.server.core.asset.AssetModule
import com.hypixel.hytale.server.core.plugin.JavaPlugin
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path

object KtaleUI {

    private var initialized = false
    private val registeredPaths = mutableMapOf<String, String>()

    fun init(plugin: JavaPlugin, vararg definitions: KtaleUIDefinition) {
        val assetModule = AssetModule.get()
        val pluginId = PluginIdentifier(plugin.manifest).toString()

        // Unregister plugin's asset pack to release JAR FileSystem
        assetModule.unregisterPack(pluginId)

        // Write .ui files into the plugin JAR
        writeToJar(plugin.file, definitions)

        // Re-register the JAR as asset pack (with .ui files included)
        assetModule.registerPack(pluginId, plugin.file, plugin.manifest, false)
        initialized = true
    }

    internal fun getPath(definition: KtaleUIDefinition): String {
        return registeredPaths[definition.name]
            ?: error("UI '${definition.name}' is not registered. Pass it to KtaleUI.init().")
    }

    private fun writeToJar(jarPath: Path, definitions: Array<out KtaleUIDefinition>) {
        val env = mapOf("create" to "false")
        FileSystems.newFileSystem(jarPath, env).use { fs ->
            for (definition in definitions) {
                val safeName = definition.name.replace(Regex("[^a-zA-Z0-9_\\-]"), "_")
                val fileName = "$safeName.ui"
                val path = fs.getPath("Common", "UI", "Custom", fileName)
                Files.createDirectories(path.parent)
                Files.writeString(path, definition.serialize())
                registeredPaths[definition.name] = fileName
            }
        }
    }
}
