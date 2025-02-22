# mvciFX

**mvciFX** is a Java implementation of the MVCI framework, inspired by the Kotlin implementation 
in the WidgetsFX library by Dave Barrett, aka PragmaticCoding.
This library aims to provide a lightweight and flexible framework for building JavaFX applications
using the MVCI framework.

## Credits

All credits for the original MVCI framework concept go to Dave Barrett.
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

#### Core

- `Controller`: an interface that instantiates and coordinates interactions between Interactor, Model and View within the MVCI framework.
- `Interactor`: an interface that defines the business logic layer within the MVCI framework. 
- `Model`: an interface that is responsible for representing the data and state within the MVCI framework.
- `ViewBuilder`: an abstract class for building JavaFX UI components with model observation support.
- `MVCIException`: an abstract exception class for handling specific errors in the framework.

#### Specialized Controller interfaces

- `DataSourceController`: an interface that defines asynchronous data retrieval capabilities.
- `ParameterizedController`: an interface that enables controller initialization using constructor parameters. 
- `DualInitController`: an interface that combines both data source interaction and parameter-based initialization capabilities.

#### State-tracking implementation

This implementation provides built-in functionalities for tracking error states and requested operations made by the user, 
such as deletions, saving and quitting.

- `StateTrackingModel`: a `Model` that implements state tracking management through 
observable properties, that automatically reflect application state changes.
- `StateTrackingAbstractViewBuilder`: an abstract `ViewBuilder` with automated state observation.
- `StateTrackingDataSourceAbstractController`: an abstract `Controller` for data source operations with state tracking capabilities.
- `StateTrackingParameterizedAbstractController`: an abstract `Controller` for parameterized initialization with state tracking capabilities.

## Examples

You can find some examples of how to use the library in `test` directory.
Two scenarios are provided:
- Simple: the Controller is an implementation of `DataSourceController`, while the other classes implement/extend core components.
- StateTracking: it's an overall implementation of State Tracking capabilities.

## License


## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue.