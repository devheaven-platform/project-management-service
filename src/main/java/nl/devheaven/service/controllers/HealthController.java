package nl.devheaven.service.controllers;

import nl.devheaven.service.models.Health;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller handles the health check for the application.
 * It listens on the /health route.
 */
@RestController
@RequestMapping()
@Api(tags = "Health")
public class HealthController {

    /**
     * Handles the /health route. This route returns information about
     * the service status
     *
     * @return Health containing data about the health check.
     */
    @GetMapping("/health")
    @ApiOperation(value = "Information about the user who authorized the request", response = Health.class)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Health health() {
        return new Health("Service is running");
    }

}

