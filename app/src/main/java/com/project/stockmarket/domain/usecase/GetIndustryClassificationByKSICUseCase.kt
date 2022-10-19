package com.project.stockmarket.domain.usecase

import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.repository.IndustryCodeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIndustryClassificationByKSICUseCase @Inject constructor(
    private val repository: IndustryCodeRepository
) {
    operator fun invoke(ksic: String): Flow<NetworkResult<String>> {
        return repository.getIndustryClassificationByKSIC(ksic)
    }
}