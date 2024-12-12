import static io.github.libsdl4j.api.event.SDL_EventType.*;
import static io.github.libsdl4j.api.keycode.SDL_Keycode.SDLK_SPACE;

public class Main {
    private static final long startTime = System.currentTimeMillis();
    static long elapsed() {
        return System.currentTimeMillis() - startTime;
    }
    public static void main(String[] args) {
        final int WIDTH = 50 , HEIGHT = 50 , CELL_SIZE = 10;
        Window window = new Window("Conway's Game of Life", WIDTH*CELL_SIZE, HEIGHT*CELL_SIZE);
        GameOfLife game = new GameOfLife(WIDTH, HEIGHT);

        int xStart = 25 , yStart = 25;
        game.toggleCell(xStart   , yStart-1);
        game.toggleCell(xStart   , yStart);
        game.toggleCell(xStart-1 , yStart);
        game.toggleCell(xStart   , yStart+1);
        game.toggleCell(xStart+1 , yStart+1);
        // game.toggleCell(xStart+1, yStart);
        // game.toggleCell(xStart-1, yStart);
        // game.toggleCell(xStart+1, yStart+1);
        // System.out.println("Cell = " + game.getCell(xStart+1, yStart));

        
        boolean running = true;
        boolean paused = true;
        long steps = 0;
        while (running) {
            while(window.eventOccurred()) {
                var event = window.nextEvent();
                if (event.type == SDL_QUIT) {
                    running = false;
                    break;
                } else if (event.type == SDL_MOUSEBUTTONUP && paused) {
                    game.toggleCell(event.button.x/CELL_SIZE , event.button.y/CELL_SIZE);
                } else if (event.type == SDL_KEYDOWN && event.key.keysym.sym == SDLK_SPACE) {
                    paused = !paused;
                }
            }

            int red   = (int) (50 + 100*(1+Math.sin(elapsed()*0.01)));
            int green = (int) (50 + 100*(1+Math.sin(elapsed()*0.02)));
            int blue  = (int) (50 + 100*(1+Math.sin(elapsed()*0.03)));


            for(int x=0 ; x<WIDTH ; x++) {
                for (int y=0 ; y<HEIGHT ; y++) {
                    if(game.getCell(x, y)) {
                        window.colorSquare(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, red, green, blue);
                    } else {
                        window.colorSquare(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, 0, 0, 0);
                    }
                }
            }

            window.update();

            if(!paused && steps % 2 == 0) {
                game.update();
            }
            steps++;
        }
    }
}