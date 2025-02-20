package banquemisr.core.domain

sealed class UIComponent {
    data class Dialog(
        val title: String,
        val description: String? = null,
    ) : UIComponent()
    data class None(
        val message: String
    ) : UIComponent()
}

