# Techtask - Multi-Module Android Project

A multi-module Android application with clean architecture and proper dependency hierarchy.

## Project Structure

### Core Modules
- **core:shared** - Shared utilities, ApiResult, UiText, ContentUiState, and common models
- **core:network** - Network layer with Retrofit, ApiResult adapter, interceptors, and network monitoring
- **core:pagination** - Pagination utilities with BasePagingSource, CustomPagingSource, and CursorBasePagingSource
- **core:presentation** - Presentation layer with Koin module and utilities
- **core:logger** - Logging utilities with Timber and Firebase Crashlytics

### Feature Modules
- **feature:users** - Users feature module
- **feature:root** - Root feature module

### App Module
- **app** - Main application module that depends on all core and feature modules

## Architecture

- **MVI + Decompose** - Using Decompose for navigation instead of Jetpack Compose Navigation
- **Koin** - Dependency injection
- **ApiResult** - Sealed interface for handling API responses
- **Pagination** - Custom pagination implementation with Paging 3

## Key Features

- Multi-module architecture with proper dependency hierarchy
- ApiResult adapter for Retrofit
- Custom pagination implementation
- Network monitoring
- Logging with Timber and Crashlytics

## Build

```bash
./gradlew build
```

## Dependencies

All dependencies are managed through `gradle/libs.versions.toml` version catalog.

