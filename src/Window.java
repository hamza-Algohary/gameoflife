import io.github.libsdl4j.api.render.SDL_Renderer;
import io.github.libsdl4j.api.render.SDL_Texture;
import io.github.libsdl4j.api.video.SDL_Window;
import io.github.libsdl4j.api.mouse.SDL_ButtonMask;
import io.github.libsdl4j.api.mouse.*;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.*;

import static io.github.libsdl4j.api.Sdl.SDL_Init;
import static io.github.libsdl4j.api.Sdl.SDL_Quit;
import static io.github.libsdl4j.api.SdlSubSystemConst.SDL_INIT_EVERYTHING;
import static io.github.libsdl4j.api.error.SdlError.SDL_GetError;
import static io.github.libsdl4j.api.event.SDL_EventType.*;
import static io.github.libsdl4j.api.event.SdlEvents.SDL_PollEvent;
import static io.github.libsdl4j.api.keycode.SDL_Keycode.SDLK_SPACE;

import static io.github.libsdl4j.api.render.SdlRender.*;
import static io.github.libsdl4j.api.render.SDL_RendererFlags.SDL_RENDERER_ACCELERATED;
import static io.github.libsdl4j.api.render.SDL_RendererFlags.SDL_RENDERER_PRESENTVSYNC;

import static io.github.libsdl4j.api.render.SDL_TextureAccess.*;
import static io.github.libsdl4j.api.pixels.SDL_PixelFormatEnum.*;

import static io.github.libsdl4j.api.video.SdlVideo.SDL_DestroyWindow;
import static io.github.libsdl4j.api.video.SDL_WindowFlags.SDL_WINDOW_RESIZABLE;
import static io.github.libsdl4j.api.video.SDL_WindowFlags.SDL_WINDOW_SHOWN;
import static io.github.libsdl4j.api.video.SdlVideo.SDL_CreateWindow;
import static io.github.libsdl4j.api.video.SdlVideo.SDL_SetWindowTitle;
import static io.github.libsdl4j.api.video.SdlVideoConst.SDL_WINDOWPOS_CENTERED;

import java.util.LinkedList;
import java.util.Queue;

import io.github.libsdl4j.api.event.SDL_Event;
import static io.github.libsdl4j.api.event.SDL_EventType.*;
import static io.github.libsdl4j.api.event.SdlEvents.SDL_PollEvent;
import static io.github.libsdl4j.api.keycode.SDL_Keycode.SDLK_UP;
import static io.github.libsdl4j.api.keycode.SDL_Keycode.SDLK_DOWN;
import static io.github.libsdl4j.api.keycode.SDL_Keycode.SDLK_RIGHT;
import static io.github.libsdl4j.api.keycode.SDL_Keycode.SDLK_LEFT;
import static io.github.libsdl4j.api.keycode.SDL_Keycode.SDLK_SPACE;



public class Window{
    SDL_Window window;
    SDL_Renderer renderer;
    SDL_Texture texture;
    Pointer pixels;
    public final int WIDTH , HEIGHT;
    public final int PIXEL_SIZE = 4;
    public final Queue<SDL_Event> eventsQueue = new LinkedList<SDL_Event>();

    public int numberOfPixels(){
        return WIDTH*HEIGHT;
    }

    public int numberOfBytes(){
        return WIDTH*HEIGHT*PIXEL_SIZE;
    }

    public Window(String title , int width , int height){
        this.WIDTH = width;
        this.HEIGHT = height;

        if(SDL_Init(SDL_INIT_EVERYTHING)!=0){System.out.println("Error Initializing SDL.");throw new RuntimeException("Error Initializing SDL.");}

        window = SDL_CreateWindow(title , SDL_WINDOWPOS_CENTERED , SDL_WINDOWPOS_CENTERED , WIDTH , HEIGHT , SDL_WINDOW_SHOWN);
        checkPointer(window, "window");

        renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_PRESENTVSYNC);
        checkPointer(renderer, "renderer");

        texture = SDL_CreateTexture(renderer, SDL_PIXELFORMAT_RGBA32, SDL_TEXTUREACCESS_STATIC, WIDTH, HEIGHT);
        checkPointer(texture, "texture");

        pixels = new Memory(numberOfBytes());

    }

    // Sets the color of one pixel inside the window
    public void setPixel(int x , int y , int red, int green, int blue){
        setPixel(x, y,  (byte) red, (byte) green, (byte) blue);
    }

    // The same as the above.
    public void setPixel(int x , int y , byte red , byte green , byte blue){
        int index = (y*WIDTH + x)*PIXEL_SIZE;
        pixels.setByte(index, red);
        pixels.setByte(index+1, green);
        pixels.setByte(index+2, blue);
        pixels.setByte(index+3, (byte)0xFF);
    }

    // Display the changes made using setPixel.
    public void update(){
        SDL_UpdateTexture(texture, null, pixels, WIDTH*4);
        SDL_RenderClear(renderer);
        SDL_RenderCopy(renderer, texture, null, null);
        SDL_RenderPresent(renderer);
    }

    private void checkPointer(Object pointer , String name){
        if(pointer == null){
            String error = "Error Initializing " + name;
            System.out.println(error);
            throw new RuntimeException(error);
        }
    }

    // This method must be called at the end of the program
    public void close(){
        SDL_DestroyRenderer(renderer);
        SDL_DestroyTexture(texture);
        SDL_DestroyWindow(window);
        SDL_Quit();
    }

    // Changes the title
    public void setTitle(String title){
        SDL_SetWindowTitle(window , title);
    }

    // Checks if the close button was closed.
    public boolean closeClicked(){
        SDL_Event event = new SDL_Event();
        while (SDL_PollEvent(event) != 0) {
            if(event.type == SDL_QUIT)
                return true;
        }
        return false;
    }

    private void pollAllEvents() {
        while (true) {
            SDL_Event event = new SDL_Event(); 
            if(SDL_PollEvent(event) == 0 )
                break;
            eventsQueue.add(event);
        }
    }

    public boolean eventOccurred() {
        pollAllEvents();
        return !eventsQueue.isEmpty();
    }
    
    public SDL_Event nextEvent() {
        return eventsQueue.poll();
    }

    //---------------------Extra Methods------------------
    public void colorSquare(int x , int y , int dimension , int red , int green , int blue) {
        for (int i = x ; i<x+dimension ; i++) {
            for (int j=y ; j<y+dimension ; j++) {
                if(i<0 || j<0 || i>=WIDTH || j>=HEIGHT){
                    continue;
                }
                setPixel(i, j, red, green, blue);
            }
        }
    }

    public class Point {
        int x;
        int y;
    }
}