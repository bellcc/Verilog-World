package edu.miamioh.schematicRenderer;

/**
 * Created by pheonix on 6/27/16.
 */
public class Element {

    private Gate gate;
    private Port port;

    Element(Gate gate){
        this.gate = gate;
    }

    Element(Port port){
        this.port = port;
    }

    GateType getType(){
        if(gate == null)
            return port.getType();
        if(port == null)
            return gate.getType();
        return null;
    }

    Gate getGate(){
        return this.gate;
    }

    Port getPort(){
        return this.port;
    }
}
