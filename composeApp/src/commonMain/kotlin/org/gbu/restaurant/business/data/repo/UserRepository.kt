package org.gbu.restaurant.business.data.repo

import kotlinx.coroutines.delay
import org.gbu.restaurant.business.data.entity.User

object UserRepository {

    private val placeHolderUser = User( // TODO delete when backend implemented
        id = "1",
        fName = "Greg",
        lName = "Bus",
        phone = "+421950290020",
        token = "bearer: qwertasdas"
    )

    suspend fun checkUserAuthentication(
        encryptedPhone: String,
        encryptedPassword: String
    ) : User? {
        delay(2000) // TODO()
        return placeHolderUser
    }

}