package me.rocka.fcitx5test.native

import java.io.Serializable


data class InputMethodEntry(
    val uniqueName: String,
    val name: String,
    val icon: String,
    val nativeName: String,
    val label: String,
    val languageCode: String,
    val isConfigurable: Boolean
)

data class RawConfig(
    val name: String,
    val comment: String,
    var value: String,
    var subItems: Array<RawConfig>? = arrayOf()
) :Serializable {
    constructor(name: String, value: String) : this(name, "", value, null)
    constructor(name: String, v: Boolean) : this(name, "", if (v) "True" else "False", null)
    constructor(subItems: Array<RawConfig>) : this("", "", "", subItems)
    constructor(name: String, subItems: Array<RawConfig>) : this(name, "", "", subItems)

    operator fun get(name: String) = findByName(name)!!

    fun findByName(name: String): RawConfig? {
        return subItems?.find { it.name == name }
    }

    /**
     * generated by Android Studio
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RawConfig

        if (name != other.name) return false
        if (comment != other.comment) return false
        if (value != other.value) return false
        if (subItems != null) {
            if (other.subItems == null) return false
            if (!subItems.contentEquals(other.subItems)) return false
        } else if (other.subItems != null) return false

        return true
    }

    /**
     * generated by Android Studio
     */
    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + comment.hashCode()
        result = 31 * result + value.hashCode()
        result = 31 * result + (subItems?.contentHashCode() ?: 0)
        return result
    }
}

enum class AddonCategory {
    InputMethod, Frontend, Loader, Module, UI;

    companion object {
        fun fromInt(i: Int) = values().first { it.ordinal == i }
    }
}

data class AddonInfo(
    val uniqueName: String,
    val name: String,
    val comment: String,
    val category: AddonCategory,
    val isConfigurable: Boolean,
    val enabled: Boolean,
    val onDemand: Boolean
) {
    constructor(
        uniqueName: String,
        name: String,
        comment: String,
        category: Int,
        isConfigurable: Boolean,
        enabled: Boolean,
        onDemand: Boolean
    ) : this(
        uniqueName,
        name,
        comment,
        AddonCategory.fromInt(category),
        isConfigurable,
        enabled,
        onDemand
    )
}
