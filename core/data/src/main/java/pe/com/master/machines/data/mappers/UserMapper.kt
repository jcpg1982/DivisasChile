package pe.com.master.machines.data.mappers

import pe.com.master.machines.model.model.User
import pe.com.master.machines.sqlite.model.entity.UserEntity

fun User.asModel() = UserEntity(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    password = this.password,
)