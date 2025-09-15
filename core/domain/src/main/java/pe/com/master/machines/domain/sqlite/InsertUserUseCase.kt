package pe.com.master.machines.domain.sqlite

import pe.com.master.machines.data.repository.database.UserDataRepository
import pe.com.master.machines.model.model.User
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private var userDataRepository: UserDataRepository,
) {

    operator fun invoke(user: User) = userDataRepository.insertUser(user)
}