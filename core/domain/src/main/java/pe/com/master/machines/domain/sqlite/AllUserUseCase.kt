package pe.com.master.machines.domain.sqlite

import pe.com.master.machines.data.repository.database.UserDataRepository
import javax.inject.Inject

class AllUserUseCase @Inject constructor(
    private var userDataRepository: UserDataRepository,
) {

    operator fun invoke() = userDataRepository.getAllUsers()
}