# Tutorial: Conway's Game Of Life in Java 

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
        eventOccurred()
        nextEvent()
    }

Each one of these functions is documented in the comments in [Window.java](src/Window.java) file.

### Drawing

To draw something inside the window:
1. Use setPixel() to draw what you want.
2. Use update() to make what you have drawn visible on the screen.
3. Everytime you draw something don't forget to call update() method afterwards. Otherwise, it won't appear on the screen.

You have to call close() at the end of the program.

### Event handling

An event might be a mouse click, keyboard input, close button clicked and other things.

To know if an event happend call eventOccurred() method.

If there are pending events, it returns true, otherwise false.

To get next event use nextEvent()

Where **SDL_Event** is a type reprensting an event. 
We are going to use the two following fields:

    SDL_Event{
        type           // Type of event. possible values: SDL_QUIT, SDL_KEYDOWN
        key.keysym.sym // The keyboard button that was pressed if any. Possible values: SDLK_UP, SDLK_DOWN, SDLK_RIGHT, SDLK_LEFT. 
    }

To get keyboard input, check if the event type is **SDL_KEYDOWN**, If so then you will find what key was pressed in the **key.keysym.sym** field.


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
- Implement fill() method.
- Implement colorSquare() method inside Window class.

```
Window{
    fill(red , green , blue)
    colorSquare( x ,  y , dimension , red , green , blue)
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

