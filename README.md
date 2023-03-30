# Rest Api Validation

spring boot sample rest api running on java 17 and 3.0.4 springboot framework. The application covers the below areas:-

1. Rest endpoint validations with jakarta validation api -> create user @valid
2. Rest api custom exception handling -> handling api validation with handleMethodArgumentNotValid, EntityNotFoundException and AuthenticationException. Also all other exceptions are handled with INTERNAL_SERVER_ERROR.
3. Rest api filtering json responses with @JsonFilter.
4. Java streams and Predicate to filter data -> AuthService.
5. Java records in place of models as data carriers.
