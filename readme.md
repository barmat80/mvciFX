# mvciFX

**mvciFX** is a Java implementation of the MVCI framework, inspired by the Kotlin implementation 
in the WidgetsFX library by Dave Barrett, aka PragmaticCoding.
This library aims to provide a lightweight and flexible framework for building JavaFX applications
using the MVCI framework.

## Credits

All credits for the original MVCI framework concept go to Dave.
For detailed explanations and insights into the MVCI framework, please visit 
[Dave Barrett's blog](https://www.pragmaticcoding.ca).

## Some Basic MVCI Concepts

### Model

The Model represents the data and business logic of the application.

### View

The View is responsible for displaying the data to the user and capturing user input. 

### Controller

The Controller is responsible for instantiating the View, Model, and Interactor components and coordinating interactions
between them.
It acts as an intermediary between the View and the Model.

### Interactor

The Interactor contains the business logic and acts as a mediator between the Controller and data sources, 
handling all data operations and business rules.

## Overview

This library provides the following components:

- **`Controller`**: 
- **`Interactor`**: an interface that defines the business logic layer in the MVCI framework. 
It defines three common methods that need to be implemented: `getView`, `lookup` and `load`
- **`Model`**: an abstract class which provides properties for tracking error states and requested operations 
made by the user, such as deletions, savings and quitting
- **`ViewBuilder`**: an abstract class that implements `Builder<Region>` provide built-in support for model listener 
setup and property binding
- **`AbstractController`**: A base class for controllers, providing common functionality, such as the constructor 
receiving Model, Interactor and ViewBuilder and the `getView` method
- **`DefaultViewBuilder`**: a utility for building views with a `setupModelListeners` method that provides 
an implementation for operations such deletions, savings and quitting.
- **Custom Exception**: `MVCIException`, an abstract exception class for handling specific errors in the framework.

## Examples

Here are some examples of how to use the mvciFX library in your JavaFX applications:

### Example 1: Basic Setup

```java
// Your example code here
```

### Example 2: Custom Controller

```java
// Your example code here
```

### Example 3: Using DefaultViewBuilder

```java
// Your example code here
```

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue.