package com.scallop.marveldex.domain.common

abstract class Mapper<in E, T> {
    abstract fun mapFrom(from: E): T
}