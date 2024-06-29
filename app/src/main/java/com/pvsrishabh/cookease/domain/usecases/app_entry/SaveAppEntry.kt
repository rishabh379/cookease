package com.pvsrishabh.cookease.domain.usecases.app_entry

import com.pvsrishabh.cookease.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}