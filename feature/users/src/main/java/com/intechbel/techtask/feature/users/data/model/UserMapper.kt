package com.intechbel.techtask.feature.users.data.model

import com.intechbel.techtask.feature.users.domain.model.User
import com.intechbel.techtask.feature.users.domain.model.UserName
import com.intechbel.techtask.network.data.mapper.BaseMapper

class UserMapper : BaseMapper<UserResponse, List<User>>() {

    override fun mapData(from: UserResponse): List<User> {
        return from.results.map { mapUserModel(it) }
    }

    private fun mapUserModel(from: UserApiModel): User {
        return User(
            name = UserName(
                title = from.name.title,
                first = from.name.first,
                last = from.name.last,
            ),
            email = from.email,
            picture = from.picture.large,
        )
    }
}
