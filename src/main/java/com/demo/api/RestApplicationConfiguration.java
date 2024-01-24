package com.demo.api;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/v1") // Pas besoin d'ajouter d'autres configurations
public class RestApplicationConfiguration extends Application {

}