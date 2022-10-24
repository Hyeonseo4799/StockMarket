package com.project.stockmarket.domain.usecase

import com.project.stockmarket.domain.model.KSIC
import com.project.stockmarket.domain.repository.IndustryCodeRepository
import javax.inject.Inject

class SetIndustryCodeUseCase @Inject constructor(
    private val repository: IndustryCodeRepository
) {
    suspend operator fun invoke(ksic: KSIC) {
        repository.insertIndustryCode(ksic)
    }
}