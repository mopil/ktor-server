package com.example.core.util

import com.example.api.dto.IdResponse

fun Long.toResponse() = IdResponse(this)
