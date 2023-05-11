package com.example.jetpack.clases;

import com.example.jetpack.generators.GeneratorBackground;
import com.example.jetpack.generators.GeneratorEnemy;
import com.example.jetpack.generators.GeneratorGifts;
import com.example.jetpack.objects.HUD;
import com.example.jetpack.objects.MainPlayer;
import com.example.jetpack.utilits.ResourceGame;
import com.example.my_framework.core.CoreGame;
import com.example.my_framework.core.GraphicsGame;
import com.example.my_framework.utilits.UtilCollisionDetectGame;

public class GameManager {
    //region Fields
    public final static double SPEED_ANIMATION = 3;
    private int mPassedDistance;
    private int mCurrentSpeedPlayer;
    private int mCurrentShieldsPlayer;
    public static boolean gameOver;
    private MainPlayer mMainPlayer;
    private GeneratorBackground mGeneratorBackground;
    private GeneratorEnemy mGeneratorEnemy;
    private GeneratorGifts mGeneratorGifts;
    private HUD mHud;

    //endregion

    //region Main methods
    public GameManager(CoreGame coreGame, int sceneWidth, int sceneHeight) {
        init(coreGame, sceneWidth, sceneHeight);
    }
    public void update() {
        updateObjects();
        mPassedDistance += mMainPlayer.getSpeedPlayer();
        mCurrentSpeedPlayer = (int) mMainPlayer.getSpeedPlayer() * 60;
        mCurrentShieldsPlayer = mMainPlayer.getShieldsPlayer();
        checkHit();
    }
    public void drawing(GraphicsGame graphicsGame) {
        mMainPlayer.drawing(graphicsGame);
        mGeneratorBackground.drawing(graphicsGame);
        mGeneratorGifts.drawing(graphicsGame);
        mGeneratorEnemy.drawing(graphicsGame);
        mHud.drawing(graphicsGame);
    }
    //endregion

    //region Methods
    private void init(CoreGame coreGame, int sceneWidth, int sceneHeight) {
        //Инициализация
        mHud = new HUD(coreGame);
        int mMinScreenY = mHud.getHEIGHT_HUD();
        mMainPlayer = new MainPlayer(coreGame, sceneWidth, sceneHeight, mMinScreenY);
        mGeneratorBackground = new GeneratorBackground(sceneWidth, sceneHeight, mMinScreenY);
        mGeneratorGifts = new GeneratorGifts(sceneWidth, sceneHeight, mMinScreenY);
        mGeneratorEnemy = new GeneratorEnemy(sceneWidth, sceneHeight, mMinScreenY);
        gameOver = false;
    }

    private void updateObjects() {
        mGeneratorBackground.update(mMainPlayer.getSpeedPlayer());
        mMainPlayer.update();
        mGeneratorEnemy.update(mMainPlayer.getSpeedPlayer());
        mGeneratorGifts.update(mMainPlayer.getSpeedPlayer());
        mHud.update(mPassedDistance, mCurrentSpeedPlayer, mCurrentShieldsPlayer);
    }

    private void checkHit() {
        //Метод проверяет на столкновения
        for (int i = 0; i < mGeneratorEnemy.getEnemyArrayList().size(); i++) {
            if (UtilCollisionDetectGame.collisionDetect(mMainPlayer, mGeneratorEnemy
                    .getEnemyArrayList().get(i))) {
                ResourceGame.sSoundHit.play(1);
                mMainPlayer.hitEnemy();
                mGeneratorEnemy.hitPlayer(mGeneratorEnemy.getEnemyArrayList().get(i));
            }
        }
        if (UtilCollisionDetectGame.collisionDetect(mMainPlayer, mGeneratorGifts.getProtector())) {
            hitPlayerWithProtector();
        }
    }

    private void hitPlayerWithProtector() {
       /*Выполняется когда произошло столкновение игрока
       с включением защиты.
       */
        mMainPlayer.hitProtector();
        mGeneratorGifts.hitProtectorWithPlayer();
    }
    //endregion

    //region Get&Set
    public int getPassedDistance() {
        return mPassedDistance;
    }
    //endregion
}
