# Solar System Editor - Assignment 4

## Project Overview

The **Solar System Editor** project allows users to manage and manipulate solar systems, stars, planets, and moons via a console-based menu interface. The registry of solar systems is loaded from a file upon application start and saved back to the file when the application ends.

This project is implemented in Java, using object-oriented principles to model heavenly bodies and their relationships. It demonstrates proficiency in OOP concepts such as associations, inheritance, and encapsulation, while also adhering to best practices like low coupling, high cohesion, and reusable code.

## Getting Started

### Setup

To set up and run the project:

1. Clone the repository:

   ```bash
   git clone <repository-url>
   cd <cloned-folder>
   ```

2. Build the project:

   ```bash
   ./gradlew build
   ```

3. Run the application:

   ```bash
   ./gradlew run -q --console=plain
   ```

   The application will display a welcome message and present a menu for managing the solar systems.

4. Open the project in an IDE like VSCode to begin exploring or modifying the code.

## Features

### Solar System Management

- **Create Solar System**: Create a new solar system by specifying a star, planets, and their moons.
- **Add Planets or Moons**: Add planets orbiting a star, or moons orbiting planets, with specific name and radius attributes.
- **Delete Members**: Remove stars, planets, or moons from the solar system. Deleting a star will delete the entire system, while deleting a planet will also remove its moons.
- **View Solar Systems**: List all solar systems in hierarchical order (stars, planets, moons).
- **Order Solar Systems**: Sort systems by size or orbital radius.
- **File Handling**: Automatically loads the solar system data from a file at startup and saves it back when exiting.

### File Format for Solar Systems

Solar systems are stored in a simple, hierarchical format in `solarsystems.data`:

```
Name:radius
-Name:radius:orbitradius
--Name:radius:orbitradius
```

Example:

```
Sun:696340
-Tellus:6371:14960000000
--Moon:1737:385000
```

The file structure reflects the star, planets, and moons in a solar system, with each new star representing a new system.

## Object-Oriented Design

The project uses a object-oriented design, as represented in the class diagram. The main entities include:

- **SolarSystem**: Represents a solar system containing a central star and associated planets.
- **Star, Planet, Moon**: Inherits from a base class `HeavenlyBody` which holds common attributes like name and radius.
- **FileHandler**: Handles reading and writing solar system data from/to a file.
- **Editor**: Manages the user interface and logic for adding, deleting, and modifying celestial bodies within the system.
- **AddingEditor, DeletingEditor, OrderingEditor**: Specialized editors for handling specific operations like adding or removing celestial bodies and ordering systems.

The following class diagram gives an overview of the structure and relationships between the main classes:

![Class Diagram](app/src/main/java/assignment4/classdiagram.JPG)

## Radius and Orbit Radius Limits

- Stars must have a radius of at least **16,700 km**.
- Planets must have a radius between **2,000 km** and **200,000 km**.
- Moons must have a radius between **6 km** and **10,000 km**, but cannot exceed half the radius of the planet they orbit.
- Planets must orbit at a minimum distance of **18,000 km**.
- Moons must orbit at a minimum distance of **60 km**.

All orbits are measured from the surface of the body they are orbiting.

## Suppressed Warnings

In the code, mutable ArrayLists are passed as arguments to methods. This design choice is intentional, as it allows for efficient modification (e.g., adding or removing celestial bodies). Any warnings regarding this have been suppressed in Gradle.

## Quality

The project includes:
- **Checkstyle**: Enforces the Google Java standard, with adjustments for slightly longer lines and numeric package names.
- **Findbugs**: A static analysis tool to detect potential bugs and performance issues.

Both Checkstyle and Findbugs are run automatically during the build process, and any issues are reported as test case failures.


## Author's Comments

Some challenges arose during this project, especially regarding time constraints. Although the assignment was intensive, it provided valuable insights into object-oriented programming and design patterns.
