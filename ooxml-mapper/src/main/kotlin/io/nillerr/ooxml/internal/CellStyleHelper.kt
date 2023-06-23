package io.nillerr.ooxml.internal

import io.nillerr.ooxml.annotation.CellStyle
import io.nillerr.ooxml.style.HorizontalAlignment
import org.apache.poi.ss.usermodel.CellStyle as ApacheCellStyle

internal object CellStyleHelper {
    fun setHorizontalAlignment(style: ApacheCellStyle, annotations: List<CellStyle>) {
        val annotation = annotations.firstOrNull { it.horizontalAlignment != HorizontalAlignment.UNSPECIFIED }
        if (annotation != null) {
            style.setHorizontalAlignment(annotation.horizontalAlignment)
        }

        style.setHorizontalAlignment(annotations.last().horizontalAlignment)
    }
}
