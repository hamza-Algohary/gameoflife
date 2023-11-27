# Java Lab Experiments

This repository contains hands-on instructions for students to complete two cool graphical projects.

- **First Project:** Explosion simulation.
- **Second Project:** Snake game.

# Learning Objectives

- Familiarizing with a windowing API like SDL.
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

# API 
The API we are going to use in both projects is the Window class.

    class Window{
        Window(title , width , height)
        setPixel(x , y , red , green , blue)
        update()
        close()
        closeClicked()
        setTitle()
    }

Each one of these functions is documented in the comments in Window.java

We are going to use two additional things in the second project.

```java
    int SDL_PollEvent(SDL_Event event)

    /*
     This function polls one event from the event queue and puts it in the event argument. It returns the number of events still not polled (ie: the length of event queue)  
    */
```

Where **SDL_Event** is a type reprensting an event. 
We are going to use the two following fields:

    SDL_Event{
        type           // Possible values: SDL_QUIT, SDL_KEYDOWN
        key.keysym.sym // Possible values: SDLK_UP, SDLK_DOWN, SDLK_RIGHT, SDLK_LEFT. 
    }


### Question 1: How are colors represented in computer memory?

<details>

<summary>
View answer.
</summary>

Colors are usually represented using RGB format which stands for Red Green Blue. Where every color is represented using four bytes (red, green, blue, alpha).
Alpha is transparency. Since it needs four bytes we can use int to represent a color
</details>

### Question 2: How is Window pixel buffer represented in memory.

<details>

<summary>
View answer.
</summary>

It's simply represented as an array of integers whose length is WIDTH*HEIGHT. where each integer represents the color of a pixel. 
</details>

# Project 1 - Explosion Simulation

#### Task One

Using the above API try doing the following:

1. Create a new window.

Problem 1: The program exits immediately.

<details>
<summary>Solution</summary>
    Make a loop.
</details>

Problem 2: Now window does not close.

<details>
<summary>Solution</summary>
    Make the loop exit if close button is closed.
</details>

2. Color the Screen

    - Color one pixel.
    - Color multiple pixels.
    - Color the entire screen.
    - Implement fill() method inside Window class.

Problem 3: How can you make the following effect?

[Green Transition](screenshots/demo1.gif)

Hint: Use sin() as a function in t.

Problem 4: Make the following effect

[Color Transition](screenshots/colors-smooth.gif)

3. Create Particle class
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

0. Revise the API used for handling events, and keyboard input.

1. Handle Keyboard events and test that it's working

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

12. **BONUS: ** Make the snake and the target bigger.



