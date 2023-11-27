# Java Lab Experiments

This repository contains hands-on instructions for students to complete two cool graphical projects.

# Learning Objectives

- Familiarizing with a window API like SDL.
- Learning to execute complex ideas using seemingly simple API.
- Learning concepts such as main loop, and event handling.

# Guide For Using This Document.

1. Learn the API you are going to use.
2. Understand the program requirments.
3. Think about how you going to make the program using provided API.
4. Read through the **To Do list** and complete each task one by one.

# Tools
- Java obviously.
- [libsdl4j](https://github.com/libsdl4j/libsdl4j) (Which is a Java binding to SDL C library)
- [Window.java](src/Window.java) (A class that wraps SDL functional SDL interface into an object oriented one, and hides unnecessary details.)
- [JNA](https://github.com/java-native-access/jna) (Java Native Access) is a library that Window.java depends on.

> [!NOTE]  
> All the above is available in this repository so you just have to clone this repository and start from there.

# Setup

1. Clone This repository
2. Make two copies
3. Create a new window
4. Add MainLoop
5. Make window closable


# Project 1 - Explosion Simulation

1. Color one pixel
2. Color multiple pixels
3. Implement fill() method
4. Oscillating single color
5. Oscillating multiple colors
6. Create class Particle
```
Particle{
    x , y
}
```
7. Create multiple particles
8. Make particles move around
```
Particle{
    ....
    speedX , speedY
    move(duration)
}
```
9. Make particles start from origin

# Project 2 - Snake Game

1. Handle Keyboard events
2. Create Snake class
```
Snake{
    x , y , speed , direction
    move()
}
```
3. Create Target class
```
Target{
    x , y
}
```
4. Make snake direction controllable by keyboard.
5. Relocate target when eaten.
6. Add a tail to the snake.
```
Snake{
    ....
    tailLength
}
```
7. Draw Snake tail. How?
```
    HINT:
    Snake{
        ....
        buffer[][]
        draw()
        modify move()
    }
```
8. Write score on window title.
8. Detect collisions.
9. Stop game on collision.
10. Prevent going back. 



