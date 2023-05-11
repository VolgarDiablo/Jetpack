package com.example.jetpack.scenes;

import android.graphics.Color;

import com.example.jetpack.R;
import com.example.jetpack.interfaces.TaskCompleteListener;
import com.example.jetpack.tasks.LoaderTask;
import com.example.my_framework.core.CoreGame;
import com.example.my_framework.core.SceneGame;

public class LoaderResourceScene extends SceneGame implements TaskCompleteListener {

    private static int mProgressLoader;

    public LoaderResourceScene(CoreGame coreGame) {
        super(coreGame);
        mProgressLoader = 0;
        LoaderTask loaderTask = new LoaderTask(coreGame, this);
        loaderTask.execute();

    }

    @Override
    protected void update() {

    }

    @Override
    protected void drawing() {
        pCoreGame.getGraphicsFW().clearScene(Color.BLACK);
        pCoreGame.getGraphicsFW().drawText(pCoreGame.getString(R.string.loading)
                ,100,100,Color.GREEN,50,null);
        pCoreGame.getGraphicsFW().drawLine(0,500, mProgressLoader,500,Color.GREEN);

    }

    @Override
    protected void pause() {

    }

    @Override
    protected void resume() {

    }

    @Override
    protected void dispose() {

    }

    @Override
    public void onComplete() {
        pCoreGame.setScene(new MainMenuScene(pCoreGame));
    }

    public static void setProgressLoader(int progressLoader) {
        LoaderResourceScene.mProgressLoader = progressLoader;
    }
}
