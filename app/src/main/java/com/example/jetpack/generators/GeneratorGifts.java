package com.example.jetpack.generators;

import com.example.jetpack.objects.MainPlayer;
import com.example.jetpack.objects.Protector;
import com.example.my_framework.core.GraphicsGame;
import com.example.my_framework.utilits.UtilTimerDelayGame;

public class GeneratorGifts {

    //region Fields
    private Protector mProtector;
    private UtilTimerDelayGame mTimerProtector;
    private int mMaxScreenY;
    private int mMaxScreenX;
    private int mMinScreenY;
    private int mMinScreenX;
    //endregion

    //region Main methods
    public GeneratorGifts(int sceneWidth, int sceneHeight, int minScreenY) {
        init(sceneWidth, sceneHeight, minScreenY);
    }

    private void init(int sceneWidth, int sceneHeight, int minScreenY) {
        this.mMaxScreenX = sceneWidth;
        this.mMaxScreenY = sceneHeight;
        this.mMinScreenY = minScreenY;
        this.mMinScreenX = 0;
        mProtector = new Protector(mMaxScreenX, mMaxScreenY, minScreenY);
        mTimerProtector = new UtilTimerDelayGame();
        mTimerProtector.startTimer();
    }

    public void update(double speedPlayer) {
        if (mTimerProtector.timerDelay(8) && (!MainPlayer.isShieldsOn())) {
            mProtector.update(speedPlayer);
            if (mProtector.getX() < mMinScreenX) {
                mProtector = null;
                mProtector = new Protector(mMaxScreenX, mMaxScreenY, mMinScreenY);
                mTimerProtector.startTimer();
            }
        }
    }

    public void drawing(GraphicsGame graphicsGame) {
        mProtector.drawing(graphicsGame);
    }
    //endregion

    //region Methods
    public void hitProtectorWithPlayer() {
        mProtector = null;
        mProtector = new Protector(mMaxScreenX, mMaxScreenY, mMinScreenY);
        mTimerProtector.startTimer();
    }
    //endregion

    //region Get&Set
    public Protector getProtector() {
        return mProtector;
    }
    //endregion

}
