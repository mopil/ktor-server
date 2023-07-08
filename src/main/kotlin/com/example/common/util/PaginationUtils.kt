package com.example.common.util

object PaginationUtils {
    data class PageResponse<T> (
        val offset: Long,
        val limit: Int,
        val totalCount: Long,
        val isLast: Boolean,
        val data: List<T>
    )

    fun <T> List<T>.toPageResponse(offset: Long, limit: Int, totalCount: Long): PageResponse<T> {
        return PageResponse(
            offset = offset,
            limit = limit,
            totalCount = totalCount,
            isLast = offset + limit >= totalCount,
            data = this
        )
    }
}
