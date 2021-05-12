package com.scallop.marveldex.ui.commons

import java.math.BigInteger
import java.security.MessageDigest

object SecurityUtils {
    fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray()))
            .toString(MD5_RADIX)
            .padStart(MD5_LENGTH, '0')
    }

    private const val MD5_RADIX = 16
    private const val MD5_LENGTH = 32
}
