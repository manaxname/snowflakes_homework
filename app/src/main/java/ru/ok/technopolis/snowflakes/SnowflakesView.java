package ru.ok.technopolis.snowflakes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * TODO: Написать код, рисующий падающие снежинки
 */
public class SnowflakesView extends View {

    public SnowflakesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        random = new Random();
        snows = new Snow[700];
    }

    private Snow[] snows;
    private static int maxWidth;
    private static int maxHegth;
    private final Paint paint;
    private final Random random;
    private Bitmap bitmap;
    private Canvas tmpCanvas;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        maxWidth = w;
        maxHegth = h;
        bitmap = Bitmap.createBitmap(maxWidth, maxHegth, Bitmap.Config.ARGB_8888);
        tmpCanvas = new Canvas(bitmap);
        for (int i = 0; i < snows.length; i++) {
            Snow snow = new Snow();
            reGenerateSnow(random, snow);
            snows[i] = snow;
        }
        paint.setColor(Color.WHITE & 0xafffffff);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        bitmap.eraseColor(Color.TRANSPARENT);
        for (Snow snow : snows) {
            snow.invokePosition();
            if (snow.x < 0 || snow.x > maxWidth || snow.y > maxHegth) {
                reGenerateSnow(random, snow);
            }
            tmpCanvas.drawCircle(snow.x, snow.y, snow.sSize, paint);
        }
        canvas.drawBitmap(bitmap, 0, 0, paint);
        invalidate();
    }

    private static void reGenerateSnow(final Random random, final Snow snow) {
        snow.Reinit(random.nextInt(maxWidth), random.nextInt(maxHegth/10), randInt(random,2, 10), randInt(random,-2, 2), random.nextInt(15));
    }

    private static int randInt(final Random random, final int min, final int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}