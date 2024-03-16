# Pacto-Test

Pacto-Test is an advanced Android application that demonstrates the implementation of modern development practices, software architecture, and user interface design. This project serves as a reference for developers looking to build robust, scalable, and maintainable Android applications while maintaining high compatibility and accessibility.

## Key Features

- **MVVM Architecture**: Clear separation between the UI logic and business logic using the Model-View-ViewModel pattern.
- **Data Management**: Integration of Room Database for efficient management of the SQLite database.
- **Network Communications**: Use of Retrofit and OkHttp to efficiently handle network communications.
- **Dependency Injection**: Implementation of Dagger-Hilt to simplify dependency management in the application.

## Libraries and Tools Used

- **Retrofit**: For network operations, facilitating communication with REST APIs.
- **OkHttp**: HTTP client that provides an additional layer of functionality over Retrofit.
- **Room Database**: Abstraction over SQLite to facilitate data access and persistence.
- **Dagger-Hilt**: Framework for dependency injection, simplifying the application's architecture.
- **MVVM**: Model-View-ViewModel design pattern for effective separation of responsibilities.

## Design and Development Decisions

The project has been designed with a minimum Android version of 5.0 (Lollipop) to leverage modern APIs, improve security, performance, and ensure broad market coverage. A development strategy that prioritizes compatibility and ease of maintenance has been chosen, selecting technologies and practices that are robust and well-supported.

### Omitted Tools and Features

To keep the application accessible and compatible with Java, tools such as Kotlin Coroutines, Data Binding, Kotlin Extensions, and Instant Features have been intentionally omitted. This decision is based on the desire to focus on fundamental development principles and ensure that the project is easily maintainable and understandable for developers with different levels of experience.

## Compilation Considerations

It is crucial to ensure that no module is in a different "build type." All modules must be aligned at the same compilation level to avoid compilation issues. This practice ensures consistency in the development environment and facilitates continuous integration and testing.

## Getting Started

To run this project, follow these steps:

1. Clone the repository:
git clone https://github.com/johnvazna/Pacto-Test.git

2. Open the project in Android Studio.
3. Run the application

## Contributing

Contributions are welcome. If you're interested in improving Pacto-Test, please review the contribution guidelines in `CONTRIBUTING.md`.
