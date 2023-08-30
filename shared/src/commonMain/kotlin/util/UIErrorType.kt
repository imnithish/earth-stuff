package util

sealed class UIErrorType {
    data object Network : UIErrorType()
    data class Other(val error: String = "Something went wrong") : UIErrorType()
    data object Nothing : UIErrorType()

}

