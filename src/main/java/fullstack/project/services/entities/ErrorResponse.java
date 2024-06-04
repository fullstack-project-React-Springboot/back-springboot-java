package fullstack.project.services.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ErrorResponse (
        @NotNull(message = "Http status should not be null")
        int httpStatus,
        @NotEmpty(message = "message should not be empty")
        String message
){}
