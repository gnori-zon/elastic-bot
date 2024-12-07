package org.gnori.elasticbot.common.ext

import java.time.Instant
import java.time.LocalDateTime
import java.util.TimeZone

fun LocalDateTime(timestampInMilliseconds: Long): LocalDateTime {
    return LocalDateTime.ofInstant(
        Instant.ofEpochMilli(timestampInMilliseconds),
        TimeZone.getDefault().toZoneId());
}