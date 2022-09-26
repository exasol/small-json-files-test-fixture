# Small Json Files Test Fixture 1.0.0, released 2022-09-26

Code name: Fix Security Issues

## Summary

In this release we replaced the YAML file format for the configuration with the properties format to reduce dependencies.

⚠️ This is a breaking change. File `test_config.yml` must be replaced with `test_config.properties` ⚠️

## Features

* #14: Fixed security issue in dependency

## Dependency Updates

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:2.6.2` to `2.8.0`
* Removed `org.projectlombok:lombok-maven-plugin:1.18.20.0`
