# Java Lab Experiments

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



