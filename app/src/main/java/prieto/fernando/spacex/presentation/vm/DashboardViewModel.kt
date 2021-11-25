package prieto.fernando.spacex.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import prieto.fernando.core.presentation.BaseViewModel
import prieto.fernando.domain.usecase.GetCompanyInfo
import prieto.fernando.spacex.presentation.dashboard.CompanyInfo
import prieto.fernando.spacex.presentation.dashboard.DashboardContract
import prieto.fernando.spacex.presentation.vm.mapper.CompanyInfoDomainToUiModelMapper
import timber.log.Timber
import javax.inject.Inject

abstract class DashboardViewModel : BaseViewModel
<DashboardContract.Event, DashboardContract.State, DashboardContract.Effect>() {
    abstract fun companyInfo()
}

@HiltViewModel
class DashboardViewModelImpl @Inject constructor(
    private val getCompanyInfo: GetCompanyInfo,
    private val companyInfoDomainToUiModelMapper: CompanyInfoDomainToUiModelMapper
) : DashboardViewModel() {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }

    init {
        companyInfo()
    }

    override fun setInitialState(): DashboardContract.State =
        DashboardContract.State(
            companyInfo = CompanyInfo("", "", "", "", -1, -1L),
            isLoading = true,
            isError = false
        )

    override fun handleEvents(event: DashboardContract.Event) {}

    override fun companyInfo() {
        viewModelScope.launch(errorHandler) {
            try {
                getCompanyInfo.execute()
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect { companyInfoDomainModel ->
                        companyInfoDomainToUiModelMapper.toUiModel(companyInfoDomainModel)
                            .let { companyInfo ->
                                setState {
                                    copy(
                                        companyInfo = companyInfo,
                                        isLoading = false
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun handleExceptions(throwable: Throwable) {
        Timber.e(throwable)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }
}