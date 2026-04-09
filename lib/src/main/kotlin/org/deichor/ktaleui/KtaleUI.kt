package org.deichor.ktaleui

import com.hypixel.hytale.common.plugin.PluginIdentifier
import com.hypixel.hytale.server.core.asset.AssetModule
import com.hypixel.hytale.server.core.plugin.JavaPlugin
import java.nio.file.Files
import java.nio.file.Path

object KtaleUI {

    private const val UI_PACK_SUFFIX = "_ktaleui"
    private const val GENERATED_DIR_NAME = "ktaleui-generated"

    private var initialized = false
    private val registeredPaths = mutableMapOf<String, String>()

    fun init(plugin: JavaPlugin, vararg definitions: KtaleUIDefinition) {
        val assetModule = AssetModule.get()
        val basePluginId = PluginIdentifier(plugin.manifest).toString()
        val uiPackId = basePluginId + UI_PACK_SUFFIX

        // Unregister previous UI pack in case init() is called again (hot reload).
        try {
            assetModule.unregisterPack(uiPackId)
        } catch (_: Throwable) {
            // Ignored — pack was not registered yet on first init.
        }

        // Write .ui files to an external directory instead of mutating the plugin JAR.
        //
        // Previously we opened the plugin JAR as a ZipFileSystem and wrote the generated
        // .ui entries directly into it. On Windows this fails intermittently because the
        // classloader keeps a file handle on the JAR, preventing the atomic rename that
        // jdk.nio.zipfs performs when the FileSystem is closed. That left a stranded
        // zipfstmp*.tmp file next to the JAR and threw:
        //   FileSystemException: The process cannot access the file because it is being
        //   used by another process
        // See: https://github.com/Orbis-Origins/Rogue/issues/48
        //
        // We now write the generated .ui files to an external directory and register
        // that directory as a secondary asset pack. The plugin's own JAR pack (auto-
        // registered by Hytale when IncludesAssetPack=true) stays untouched, so all
        // bundled static assets (images, textures, fonts, etc.) continue to resolve.
        val packRoot = plugin.dataDirectory.resolve(GENERATED_DIR_NAME)
        writeDefinitionsToDirectory(packRoot, definitions)

        assetModule.registerPack(uiPackId, packRoot, plugin.manifest, false)
        initialized = true
    }

    internal fun getPath(definition: KtaleUIDefinition): String {
        return registeredPaths[definition.name]
            ?: error("UI '${definition.name}' is not registered. Pass it to KtaleUI.init().")
    }

    private fun writeDefinitionsToDirectory(
        packRoot: Path,
        definitions: Array<out KtaleUIDefinition>
    ) {
        val uiDir = packRoot.resolve("Common").resolve("UI").resolve("Custom")

        // Wipe stale .ui files from previous runs so removed pages do not linger.
        if (Files.exists(uiDir)) {
            Files.walk(uiDir).use { stream ->
                stream
                    .filter { it != uiDir && it.toString().endsWith(".ui") }
                    .sorted(Comparator.reverseOrder())
                    .forEach { Files.deleteIfExists(it) }
            }
        }

        Files.createDirectories(uiDir)

        for (definition in definitions) {
            val safeName = definition.name.replace(Regex("[^a-zA-Z0-9_\\-]"), "_")
            val fileName = "$safeName.ui"
            val path = uiDir.resolve(fileName)
            Files.writeString(path, definition.serialize())
            registeredPaths[definition.name] = fileName
        }
    }
}
