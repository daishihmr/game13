package test;

import java.io.File;

import javax.script.ScriptEngineManager;

import jp.dev7.game13.actor.ai.AI;

public class ScriptDrive {

    public static void main(String[] args) throws Exception {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        AI ai = new AI(engineManager, new File(
                "src/test/resources/scriptDrive.js"));
        ai.onload();
    }

}
