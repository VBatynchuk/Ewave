package com.batynchuk.ewave.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

data class Workers (val io: CoroutineDispatcher = Dispatchers.IO, val main: CoroutineDispatcher = Dispatchers.Main)