package com.example.smoothe.data.rules

object Validator {
    fun ValidateFirstName(firstName: String): ValidationResult{
        return ValidationResult(
            (!firstName.isNullOrEmpty()&& firstName.length>=2)
        )
    }
    fun ValidateLastName(lastName: String): ValidationResult{
        return ValidationResult(
            (!lastName.isNullOrEmpty()&& lastName.length>=2)
        )

    }
    fun ValidateEmail(email: String): ValidationResult{
        return ValidationResult(
            (!email.isNullOrEmpty()&& email.contains("@"))
        )

    }
    fun ValidatePassword(password: String):ValidationResult{
        return ValidationResult(
            (!password.isNullOrEmpty()&& password.length>=4)
        )
    }
    fun ValidatePrivacyPolicyAcceptance(statusvalue : Boolean):ValidationResult{
        return ValidationResult(
            statusvalue
        )
    }

}

data class ValidationResult(
    val status : Boolean = false
)