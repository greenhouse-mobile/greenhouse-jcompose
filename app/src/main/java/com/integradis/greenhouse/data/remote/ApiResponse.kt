package com.integradis.greenhouse.data.remote

import com.integradis.greenhouse.data.model.RecentRecord

class ApiResponse(
    val response: String,
    val results: List<RecentRecord>
)