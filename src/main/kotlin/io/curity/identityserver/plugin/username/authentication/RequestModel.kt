package io.curity.identityserver.plugin.phonenumber.authentication

import org.hibernate.validator.constraints.NotBlank
import se.curity.identityserver.sdk.service.OriginalQueryExtractor
import se.curity.identityserver.sdk.service.UserPreferenceManager
import se.curity.identityserver.sdk.web.Request
import javax.validation.Valid

class RequestModel(request: Request, userPreferenceManager: UserPreferenceManager)
{
    @Valid
    val postRequestModel: Post? = if (request.isPostRequest) Post(request) else null

    @Valid
    val getRequestModel: Get? = if (request.isGetRequest) Get(userPreferenceManager) else null
}

class Post(request: Request)
{
    @NotBlank(message = "validation.error.phonenumber.required")
    val phonenumber: String = request.getFormParameterValueOrError("phonenumber")
}

class Get(userPreferenceManager: UserPreferenceManager)
{
    val preferredUserName: String? = userPreferenceManager.username
}
