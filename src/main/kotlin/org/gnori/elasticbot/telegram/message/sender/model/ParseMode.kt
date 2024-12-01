package org.gnori.elasticbot.telegram.message.sender.model

import org.gnori.elasticbot.common.phase.description.model.PhaseDescriptionFormatType

enum class ParseMode {
    HTML,
    MARKDOWN,
    NULL;

    companion object {
        fun of(descriptionFormatType: PhaseDescriptionFormatType): ParseMode =
            when (descriptionFormatType) {
                PhaseDescriptionFormatType.NULL -> NULL
                PhaseDescriptionFormatType.HTML -> HTML
                PhaseDescriptionFormatType.MARKDOWN -> MARKDOWN
            }
    }
}