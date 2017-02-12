package com.cartracking.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public abstract class Config {

    @Autowired
    protected Environment env;

    protected String getProperty(String name) {
        return env.getProperty(name);
    }
}
