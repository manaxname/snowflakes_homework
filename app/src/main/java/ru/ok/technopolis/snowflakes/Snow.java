package ru.ok.technopolis.snowflakes;

final class Snow {
    int sSize;

    int x;
    int y;
    private int deltaX;
    private int deltaY;

    void Reinit(int xStart, int yStart, int moveDown, int moveX, int size){
        y = yStart;
        x = xStart;
        deltaX = moveX;
        deltaY = moveDown;
        sSize = size;

    }

    void invokePosition(){
        x += deltaX;
        y += deltaY;
    }
}
