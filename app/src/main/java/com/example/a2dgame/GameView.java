package com.example.a2dgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;
// basically our game loop
public class GameView extends SurfaceView implements Runnable{
    private Thread thread;
    private boolean isPlaying;
    private Background background1, background2;
    private int screenX, screenY;
    private Paint paint;
    private float screenRatioX, screenRatioY;
    // sets up the game view
    public GameView(Context context, int screenX, int screenY) {
        super(context);
        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f/screenX;
        screenRatioY = 1920f/screenY;
        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());
        background2.y = -screenY;
        paint = new Paint();
    }
    @Override
    // runs while the user is playing
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep();
        }
    }
    // updates the background to move
    private void update() {
        // screenratio makes it compatible to other display sizes.
        background1.y += 10 * screenRatioY;
        background2.y += 10 * screenRatioY;

        if (background1.y > background1.background.getHeight()) {
            background1.y = -screenY;
        }
        if (background2.y > background2.background.getHeight()) {
            background2.y = -screenY;
        }
    }

    // draw on canvas on the background
    private void draw () {
        if(getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);

            getHolder().unlockCanvasAndPost(canvas);
        }
    }
    // wait for 17 milliseconds to update, meaning in one second, we will have 60 updates per second.
    private void sleep () {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void resume () {
        thread = new Thread(this);
        thread.start();
    }
    // stop the thread
    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
