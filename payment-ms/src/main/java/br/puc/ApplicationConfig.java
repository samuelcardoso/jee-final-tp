package br.puc;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/rest")
public class ApplicationConfig extends Application {}
//Payment p = new Payment((double) v.nextInt(5000), randomType(), randomMode(), randomStatus());