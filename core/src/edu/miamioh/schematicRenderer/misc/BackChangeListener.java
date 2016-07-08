package edu.miamioh.schematicRenderer.misc;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.Level.Level;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.verilogWorld.VerilogWorldMain;
import edu.miamioh.worldSimulator.WorldSimulatorController;

class BackChangeListener extends ChangeListener {

    @Override
    public void changed(ChangeEvent event, Actor actor) {

        System.out.println("Back Change Listener");
        
        Level level = VerilogWorldController.getController().getLevel();

        WorldSimulatorController.getController().resetMultiplexer();
        VerilogWorldMain.getVerilogWorldMain().setWorldEditorScreen(level);

    }

}