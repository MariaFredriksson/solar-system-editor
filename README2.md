# Assignment 4 - The Solar System Editor
In short this assignment is about creating registry of solar systems. The application should be runnable as a console application, with a menu and the information in the registry should be loaded and saved to a file.

## General
Remember that the course is about _object oriented programming_ and you need to show that you know the concepts and how to properly work with classes and objects.

The following is a list of common best practices that you should follow:

1. Connect objects using oo-relation (associations/dependencies) and not with keys/ids/numbers/strings etc.
2. Classes have high cohesion and are not too large or have too much responsibility.
3. Classes have low coupling and are not too connected to other entities.
4. Do not use static variables and operations (the main method is of course an exception, and also constants can be an exception).
5. Do not use magic constants - eg. hard coded numbers/strings etc. Constants and enumerations can be a help here.
6. Work with encapsulation - it should be easy to do the correct thing from a programming perspective and not easy to mess things up.
7. Let reality insprie the design, concepts from reality should be used as classes etc.
8. Name classes, methods, attributes, parameters etc as well as possible. Try to use naming that explains what the method/variable/method is used for and avoid just repeating the type.
9. Add comments for parts that are hard to understand. 
10. The application should be runnable using command `./gradlew run -q --console=plain`
11. The application should be buildable using command `./gradlew build` and there should max 5 quality errors.
12. You should regularely commit and push to gitlab. Think one feature = one commit.
13. Use the readme to explain your application any problems, strangeness or things that does not work properly, or is not that well made. Show that you know about things that are lacking. 
14. Avoid duplicated code, if you copy paste then you should think twice... refactor common code into methods or base classes if appropriate.




## Application Requirements

You may reuse parts of assignment 2 if you feel that this is approprate. 


These requirements are used to assess the assignment passing grades E-C.
1. You should be able to create a new solar system with one and only one central star, planets orbiting the star and moons orbiting the planets.
   1. All should have a neame and an average radius.
      1. There are max an min limits for the radii depending on the type of heavenly body. You decide these limits, document in your README.md.
   1. The planets and the moons have an average orbiting radius.
      1. The planet and moons have max and min limits on the radius. E.g. the moon cannot be so close so it collides with the planet. You decide these limits, document your README.md
2. You should be able to list all solar systems.
3. You should be able to select a particular solar system see detailed information about it in a hierarical way. I.e. the star on top then the planet with the respective moons. You should then be able to:
   1. delete a member of the solar system. 
      1.  If the sun is deleted everything is deleted, if a planet is deleted the moons of the planet are deleted.
   2. Add a planet or moon to the solar system. The rules should of course apply.
   3. You may handle this hierarically, e.g. selecting the solar system, then selecting a planet, then selecting a moon and performing the above operations.
   4. There should be at least 2 ways of ordering the solar system information.
      1. By size
      2. By orbital radius (closer firs)
4. You should be able to quit the application
5. The registry information should be loaded from a file `solarsystems.data` **when the application starts**. The format is specified below.
6. The registry information should be saved to a file `solarsystems.data` **when the application exits**. The format is specified below.
7. There should be at least one class diagram showing the application structure with all classes and correct relations between the classes. You do **not** need to add every operation or attribute in the class diagram.
8. Basic error handling, i.e. it should not crash. No need for user friendly error messages.

### File Loading/Saving Format
The requirement is that the data of the application should be loaded from a file when the application starts, and saved to a file when the application ends. The idea is to load the whole file, convert it to nice to work with objects. When the user quits, the whole file is overwritten with new data. Trying to load/save continously or manipulate the file incrementally will only make things more complicated.

The format of the file is as follows:

```
Name:radius
-Name:radius:orbitradius
--Name:radius:orbitradius
```

For example:

```
Sun:696340
-Tellus:6371:14960000000
--Moon:1737:385000
```
Note the hierarichal nature of the file structure, (top level for stars, then planets and moons respectively), and that there may be more than one star in the file and that each star denotes a new solar system.


## Extended Requirements
These requirements are used to assess the assignment higher grades C-A. You do not need to complete all of them to be able to get a higher grade.

1. Change information of the star, planets or moons. All rules should of course apply.
2. Move a moon to another planet. All rules should of course apply.
3. Use the _strategy design pattern_ to design and implement an extendible and flexible way of searching for heavenly bodies. For example searching for a certain name, a size larger than something, or a size smaller than something. This could possibly extend for searching for a planet with a moon with a certain name etc.
4. Extend the basic way of searching with the _composite design pattern_ to enable boolean expression type searcing. E.g. Planets with a certain name and more then tree moons or a radii larger than some value `(name=tellus and mooncount>1) or radii=10000`. For this requirements it is sufficient that you design and implement some hard coded examples (for example as test cases), there is no need for adding a user interface for this.
5. Unit testing - add a few automatic unit tests to the codebase. It should be someting more than just simple getters and setters.
6. The application should handle input-errors well with user friendly error messages.

## Approaching
Start small! Begin by trying to get an idea of the structure of the application by beginning to draw a class diagram. Try to find out the classes needed to fulfil the requirements, but also have in mind that you might need to revise them later. Try to find what relations the different classes have, what might be dependent on what and so on.

When coding, do it in small, small steps. Do not add too much functionality at once, but rather try to add one or two methods at a time and see to that it works (don't forget to commit). If it does not add too much overhead for you, try to add tests as early as possible in the process. 

Reading and parsing (as well as saving) the file is something we have not covered extensively in the course, so put some time to try to figure that out. It is always a good idea to try out parts that you are unfamiliar with in a separate project without the overhead of all the other parts of the program.

Be prepared to change the class diagram as you go along with the coding. This happens in real life as well, but perhaps more so when we are this early in the educational programme. That said, see the class diagram as a help to structure your thoughts. Add other diagrams _as you see fit_ if they help you.

Do not forget to build and run using gradle often. This is how we will try to run your application and if that does not work... things are not looking good.

## Handing In the Assignment
When done with the tasks you issue a merge request to the release branch.

Follow the instructions for the hand in process.
