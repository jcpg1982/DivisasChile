package pe.com.master.machines.data.mappers

import pe.com.master.machines.model.model.User
import pe.com.master.machines.sqlite.model.entity.UserEntity

fun UserEntity.asModel() = User(
    id = this.id,
    firstName = this.firstName.orEmpty(),
    lastName = this.lastName.orEmpty(),
    email = this.email.orEmpty(),
    password = this.password.orEmpty(),
)

fun List<UserEntity>?.asModel() = this?.map { it.asModel() } ?: emptyList()