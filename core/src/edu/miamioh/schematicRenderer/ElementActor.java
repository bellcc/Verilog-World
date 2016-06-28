package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Represents a schematic element on the Stage.
 */
class ElementActor extends Actor{

    private Element e;

    ElementActor(Element e){
        this.e = e;
    }

}
