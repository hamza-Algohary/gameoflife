# Java Lab Experiments

This repository contains hands-on instructions for students to complete two cool graphical projects.

- **First Project:** Explosion simulation.
- **Second Project:** Snake game.

# Requiremnets
- Basic knowledge of Java (variables, IO, conditionals, loops)
- Basic knowledge of OOP (Creating a class and filling it with members and instantaing it.)

# Learning Objectives

- Familiarizing with a windowing API like SDL.
- Learning to execute complex ideas using seemingly simple API.
- Learning concepts such as main loop, and event handling.

# Guide For Using This Document.

1. Learn the API you are going to use.
2. Understand the program description and requirments.
3. Think about how you are going to make the program using provided API.
4. Read through the **To Do list** and complete each task one by one.
5. When you meet a problem try thinking about it before viewing the answer
6. Do the assignment at the end of each project.

# Tools and Dependencies
- Java obviously.
- [libsdl4j](https://github.com/libsdl4j/libsdl4j) (Which is a Java binding to SDL C library)
- [Window.java](src/Window.java) (A class that wraps SDL functional SDL interface into an object oriented one, and hides unnecessary details.)
- [JNA](https://github.com/java-native-access/jna) (Java Native Access) is a library that Window.java depends on.

> [!NOTE]  
> All the above is available in this repository so you just have to clone this repository and start from there.

To clone this repository:
```bash
git clone https://github.com/hamza-Algohary/LabExperiment
```

# Learn the API 
### The Window class

    class Window{
        Window(title , width , height)
        setPixel(x , y , red , green , blue)
        update()
        close()
        closeClicked()
        setTitle()
    }

Each one of these functions is documented in the comments in [Window.java](src/Window.java) file.

### Event handling

> [!NOTE]
> Only read this section when you are starting the second project.

An event might be a mouse click, keyboard input, close button clicked and other things.

To know if an event happend call SDL_PollEvent() method.

```java
int SDL_PollEvent(SDL_Event event)
```
    
If there are pending events, it puts the next event in the **event** object and returns 1.

If there are no pending events it returns 0 and assigns **event** to null.

Where **SDL_Event** is a type reprensting an event. 
We are going to use the two following fields:

    SDL_Event{
        type           // Type of event. possible values: SDL_QUIT, SDL_KEYDOWN
        key.keysym.sym // The keyboard button that was pressed if any. Possible values: SDLK_UP, SDLK_DOWN, SDLK_RIGHT, SDLK_LEFT. 
    }

Look at closeClicked() implementation in [Window.java](src/Window.java) to see how quit event is handled using the above API.

To get keyboard input, check if the event type is **SDL_KEYDOWN**, If so then you will find what key was pressed in the **key.keysym.sym** field

# Try To Answer The Following

### Question 1: How are colors represented in computer memory?

<details>

<summary>
View answer.
</summary>

Colors are usually represented using RGB format which stands for Red Green Blue. Where every color is represented using four bytes (red, green, blue, alpha).
Alpha is transparency. Since it needs four bytes we can use **int** to represent a color
</details>

### Question 2: How is Window pixel buffer represented in memory.

<details>

<summary>
View answer.
</summary>

It's simply represented as an array of integers whose length is WIDTH*HEIGHT. where each integer represents the color of a pixel. 
</details>

# Project 1 - Explosion Simulation

### Description

Make a graphical scene that resembles an explosion, by making alot of randomly moving particles changing their colors.

Think about how are you going to implement this program using provided API, then proceed to steps.

### Using the above API try doing the following:

### 1. Create a new window.

> [!IMPORTANT] 
> Problem 1: The program exits immediately.
> 
> <details>
> <summary>Solution</summary>
>     Make a loop.
> </details>

> [!IMPORTANT] 
> Problem 2: Now window does not close.
> 
> <details>
> <summary>Solution</summary>
>     Make the loop exit if close button is closed.
> </details>

### 2. Color the Screen

- Color one pixel.
- Color multiple pixels.
- Color the entire screen.
- Implement fill() method inside Window class.

```
Window{
    fill(red , green , blue)
    // A method to color the entire screen
}
```

> [!IMPORTANT] 
> Problem 3: How can you make the following effect? (Hint: use sin() as a function in t)
> <details>
> <summary>Solution</summary>
>     When setting window color set red and blue to zero, and make green maximum multiplied by an oscillating function such as sin()
> </details>

Make the window's color change gradually from green to black then gradually to green again and so on.
![Green Transition](screenshots/demo1.gif)

> [!IMPORTANT] 
> Problem 4: Make the following effect:
> <details>
> <summary>Solution</summary>
>     Do as you did with green in last step, but also in red and green and blue, each with different frequency.
> </details>

Make the window switch between different colors gradually.
![Color Transition](screenshots/colors-smooth.gif)

### 3. Create Particle class (A class to represent a point on the screen)
```
Particle{
    x , y
}
```
- Assign a random value to both x and y.
- Create multiple particles (make an array of particle objects)
- Color the particles only instead of the entire screen.
- Make particles move around with random speed and direction.
```
Particle{
    ....
    speedX , speedY
    move(duration)
}
```
> [!IMPORTANT] 
> Problem 5: How are you going to make particles move?
> <details>
> <summary>Solution</summary>
>     Implement move() method in the Particle class. This method takes as an argument the amount of time between each frame. It the update the position in x and y according to speed and time elapsed.
>
> Remeber: displacement = speed*duration.
> </details>

- Make particles change their direction when they reach the edge of the window. (This is a modification in move() method)

- Make particles start from middle of the screen instead of a random position.
- Move particles again to middle of screen when they meet the edge of the window.

### Assignment (Bonus)
- Convert the particles coordinates into polar coordinates inorder to make them rotate.

- Make the particle's coordinates range from [-1:1] and scale while you are drawing. (Not necessary and has its drawbacks so you might skip it.)

# Project 2 - Snake Game

### Revise the Event Handling section before proceeding.

### Building on the same logic used in Particle class in the previous experiment.

- Handle Keyboard events and test that it's working

Example:
When any of the arrows keys is pressed print to console what was pressed.

- Create Snake class
```
Snake{
    x , y , speed , direction
    move()
}
```
- Create Target class
```
Target{
    x , y
}
```
> [!NOTE] 
> Both Snake and Target class are going to behave like class Particle that was created in the previous experiment. The snake will start from the middle of the screen and move to a specific direction, and the target will be put at a random location and will not move.

- Make Objects from both Snake and Target class and color them on screen (different colors)
- Make snake direction controllable by keyboard.
- Change the location of target when it's eaten.

### Adding a tail to the snake
- Add the field tailLength to the snake class.
```
Snake{
    ....
    tailLength
}
```
- Draw Snake tail. How?

> [!IMPORTANT] 
> Problem 6: How are you going to implement the tail of the snake.
> 
> <details>
> <summary>Solution (Not a very good one) </summary>
> <br/>
> Make another buffer (2D int array) for the snake.
> When head of the snake reaches a certain coordinate, put the length of the tail in that coordinate. And on each iteration decrease the entire array by one except if its value is zero. Then when drawing the snake color the pixels of the screen which correspond to non zero in the snake buffer.
> 
> <br/>
> Problems with that solution: Too high speed will make the snake non continuous. Too low speed will make the snake contract in length.
>
> ```
> Snake{
>     ....
>     buffer[][]
>     draw()
>     modify move()
> }
> ``` 
>
> </details>


- Write score on window title. (You might make the score equal to current snake length.)

### Detecting when the snake collides with itself.

> [!IMPORTANT] 
> Problem 5: When does a collision happen?
> <details>
> <summary>Answer</summary>
> A collision happens when the next location of the head is already occupied by the body (ie: It's a non zero value in snake buffer)
> </details>

- Add the following field to snake class
```
Snake{
    ...
    boolean collided
}
```
- In move method if a collision happens make collided = true

- Stop game on collision and change the window title to "You Lost".

### Assignment

- Prevent the snake from going back. 
- **(BONUS)** Make the snake and the target bigger. (Hint: Make snake buffer dimensions less than that of the screen, and when drawing snake and target don't draw one pixel but instead a square of pixels, 9 pixels for example)





