package org.gnori.bgauassistantbot.assistant.telegrambot.message.callback

enum class CallbackDataType(val raw: Int) {
    PHASE(0),
    ACTION(1);

    override fun toString(): String {
        return "$raw"
    }

    companion object {
        fun of(raw: Int): CallbackDataType {
            return when(raw) {
                PHASE.raw -> PHASE
                ACTION.raw -> ACTION
                else -> throw IllegalArgumentException("unknown raw $raw")
            }
        }
    }
}