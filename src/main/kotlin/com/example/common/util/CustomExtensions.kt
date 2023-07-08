package com.example.common.util

import com.example.api.dto.IdResponse

fun Long.toResponse() = IdResponse(this)
