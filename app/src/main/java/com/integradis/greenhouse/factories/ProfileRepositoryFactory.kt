package com.integradis.greenhouse.factories

import com.integradis.greenhouse.repositories.UserRepository
import com.integradis.greenhouse.shared.SharedPreferencesHelper

class UserRepositoryFactory private constructor() {
    companion object {
        private var userRepository: UserRepository? = null

        fun getUserRepository(sharedPreferencesHelper: SharedPreferencesHelper): UserRepository {
            if (userRepository == null) {
                userRepository = UserRepository(sharedPreferencesHelper = sharedPreferencesHelper)
            }
            return userRepository as UserRepository
        }
    }
}
