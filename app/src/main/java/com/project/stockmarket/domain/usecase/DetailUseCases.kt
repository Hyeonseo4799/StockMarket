package com.project.stockmarket.domain.usecase

import javax.inject.Inject

data class DetailUseCases @Inject constructor(
    val getCorporationInfo: GetCorporationInfoUseCase,
    val getStockPriceInfo: GetStockPriceInfoUseCase,
    val getStockIssuanceInfo: GetStockIssuanceInfoUseCase,
    val getIndustryClassificationByKSIC: GetIndustryClassificationByKSICUseCase,
    val setIndustryCode: SetIndustryCodeUseCase,
    val getKSIC: GetKSICUseCase
)
