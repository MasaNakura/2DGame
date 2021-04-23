package com.example.a2dgame;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Background {
    int x = 0,y = 0;
    Bitmap background;
    Background(int screenX, int screenY, Resources res) {
        // instantiate the background
        background = BitmapFactory.decodeResource(res, R.drawable.ic_launcher_background);
        // scale background to screen
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);


    }
}
