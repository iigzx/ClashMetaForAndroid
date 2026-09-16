package com.cmfa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class AgeKeyPair(
    val secretKey: String,
    val publicKey: String
)
