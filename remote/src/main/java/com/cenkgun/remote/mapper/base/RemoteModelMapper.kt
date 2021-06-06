package com.cenkgun.remote.mapper.base

interface RemoteModelMapper<in M, out E> {
    fun mapFromModel(model: M): E
}