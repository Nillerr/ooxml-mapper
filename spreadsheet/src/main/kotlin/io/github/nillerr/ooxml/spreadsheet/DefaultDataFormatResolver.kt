package io.github.nillerr.ooxml.spreadsheet

import io.github.nillerr.ooxml.spreadsheet.annotation.CellStyle
import io.github.nillerr.ooxml.spreadsheet.internal.firstOrLast
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.reflect.typeOf

open class DefaultDataFormatResolver : DataFormatResolver {
    override fun getDataFormat(styles: List<CellStyle>, columnInfo: ColumnInfo): String? {
        val dataFormat = styles.dropLast(1).firstOrLast({ it.dataFormat }) { it.isNotEmpty() }
        if (dataFormat.isNotEmpty()) {
            return dataFormat
        }

        return getDataFormat(columnInfo)
    }

    open fun getDataFormat(columnInfo: ColumnInfo): String? {
        return when (columnInfo.propertyType) {
            typeOf<Int>() -> null
            typeOf<Double>() -> "#,##0.00"
            typeOf<BigDecimal>() -> "#,##0.00"
            typeOf<LocalDate>() -> "dd/mm/yyyy"
            typeOf<LocalTime>() -> "HH:MM"
            typeOf<LocalDateTime>() -> "dd/mm/yyy HH:MM"
            typeOf<Instant>() -> "dd/mm/yyy HH:MM:SS"
            else -> null
        }
    }
}