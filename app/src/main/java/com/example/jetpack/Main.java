package com.example.jetpack;

import com.example.jetpack.scenes.LoaderResourceScene;
import com.example.my_framework.core.CoreGame;
import com.example.my_framework.core.SceneGame;

public class Main extends CoreGame {

    public SceneGame getStartScene() {
        return new LoaderResourceScene(this);
    }

}