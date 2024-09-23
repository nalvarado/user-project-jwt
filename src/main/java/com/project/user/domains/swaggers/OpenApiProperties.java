package com.project.user.domains.swaggers;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "swagger.info")
public record OpenApiProperties(
        String version,
        String name,
        String description,
        OpenApiContactProperties contact) {
}