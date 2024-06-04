package fullstack.project.services.strings.routes;

import org.springframework.stereotype.Component;

@Component
public class Routes {

    public static final String API_VERSION_V1 = "/api/v1/";
    public static final String LOGIN = API_VERSION_V1 + "/login";
    public static final String TUTOR_BASE_URL = API_VERSION_V1 + "/tutors";
    public static final String STUDENT_BASE_URL = API_VERSION_V1 + "/students";

    public static final String OPEN_API_DOC = "/v3/api-docs/**";
    public static final String SWAGGER_UI_URL = "/swagger-ui/**";
    public static final String[] WHITE_LIST_URLS = {LOGIN, TUTOR_BASE_URL, OPEN_API_DOC, SWAGGER_UI_URL};
}
