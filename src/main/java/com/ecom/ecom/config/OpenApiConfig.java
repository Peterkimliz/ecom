package com.ecom.ecom.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info =@Info(
         contact = @Contact(
            name = "ecom",
            email = "jilokim8@gmail.com"
            
         ),
         description = "Open Api documentation for ecom",
         title = "OpenApi specification-ecom",
         version ="1.0"
    ) 
    ,servers = {
        @Server(
            description = "Production ENV",
            url = "https://ecom-r9o5.onrender.com/"
        ),
        @Server(
            description = "Local ENV",
            url = "http://localhost:8080"
        )
        
    },security = {
       @SecurityRequirement(name = "bearerAuth")
    }
)
@SecurityScheme(
    name = "bearerAuth",
    description = "Jwt Auth description",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
    
}
