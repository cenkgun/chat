package com.cenkgun.local.mapper

import com.cenkgun.local.DummyData.makeUser
import com.cenkgun.local.DummyData.makeUserEntity
import com.cenkgun.local.mappers.UserLocalModelMapper
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UserLocalModelMapperTest {

    private val sut = UserLocalModelMapper()

    @Test
    fun `maps entity to model`() {
        val expected = makeUserEntity()
        val result = sut.mapToModel(expected)

        assertThat(result.id).isEqualTo(expected.id)
        assertThat(result.nickname).isEqualTo(expected.nickname)
        assertThat(result.avatarURL).isEqualTo(expected.avatarURL)
    }


    @Test
    fun `maps model to entity`() {
        val expected = makeUser()
        val result = sut.mapToEntity(expected)

        assertThat(result.id).isEqualTo(expected.id)
        assertThat(result.nickname).isEqualTo(expected.nickname)
        assertThat(result.avatarURL).isEqualTo(expected.avatarURL)
    }
}