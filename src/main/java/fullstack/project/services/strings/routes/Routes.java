package fullstack.project.services.strings.routes;

import org.springframework.stereotype.Component;

@Component
public class Routes {
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";

    public static final String TUTOR_BASE_URL = "/tutors";
    public static final String OPEN_API_DOC = "/v3/api-docs/**";
    public static final String STUDENT_BASE_URL = "/students";
    public static final String SWAGGER_UI_URL = "/swagger-ui/**";
    public static final String[] WHITE_LIST_URLS = {LOGIN, REGISTER, TUTOR_BASE_URL, OPEN_API_DOC, SWAGGER_UI_URL};
}
