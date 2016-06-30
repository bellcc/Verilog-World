package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

import static edu.miamioh.schematicRenderer.GateType.BLANK;
import static edu.miamioh.schematicRenderer.SchematicRendererController.gateSize;
import static edu.miamioh.schematicRenderer.SchematicRendererController.scaleFactor;

/**
 * Created by shaffebd.
 */
class Port {

    private GateType type;
    private ArrayList<String> inputs = new ArrayList<>();
    private String id = "";
    private int level = 0;
    private int cx = 0, cy = 0, rx = 0, ry = 0;
//    private float r, g, b, a;
    private Color color;

    /**
     * Port constructor. Takes a Port Type, Port ID, and Port Level and
     * creates a new Port. Port types can be found in
     * {@link edu.miamioh.simulator.WireRoleType}.
     *
     * @param type  The type of the Port.
     * @param id    The unique ID of the Port.
     * @param level The level of the Port, usually 0 or max Gate level + 1.
     */
    Port(GateType type, String id, int level) {

//        float r, g, b, a = 255;

        switch (type) {

            case INPUT:
                this.type = type;
                this.id = id;
//                r = 0;
//                g = 0;
//                b = 0;
                setColor(Color.GRAY);
                break;

            case OUTPUT:
                this.type = type;
                this.id = id;
                this.level = level;
//                r = 0;
//                g = 0;
//                b = 0;
                setColor(Color.GRAY);
                break;

            default:
                this.type = BLANK;
                this.id = "INVALID_ASSIGNMENT";
//                r = 255;
//                g = 255;
//                b = 255;
                setColor(Color.GRAY);
                break;

        }

    }

    private void setColor(Color color){
//        this.r = r;
//        this.g = g;
//        this.b = b;
//        this.a = a;
        this.color = color;
    }

    Color getColor(){
        return this.color;
    }

//    float getR(){
//        return this.r;
//    }
//
//    float getG(){
//        return this.g;
//    }
//
//    float getB(){
//        return this.b;
//    }
//
//    float getA(){
//        return this.a;
//    }

    /**
     * Gets the Type of the Port.
     *
     * @return The Type of the Port.
     */
    public GateType getType() {

        return this.type;

    }

    /**
     * Gets the ID of the Port.
     *
     * @return The ID of the Port.
     */
    String getID() {

        return this.id;

    }

    /**
     * Gets the Level of the Port.
     *
     * @return The Level of the Port.
     */
    public int getLevel() {

        return this.level;

    }

    public void setLevel(int newLevel){
        this.level = newLevel;
    }

    /**
     * Gets the Center X coordinate of the Port.
     *
     * @return The Center X coordinate of the Port.
     */
    int getCX() {

        return this.cx;

    }

    /**
     * Sets the Center X coordinate of the Port.
     *
     * @param cx An integer of the Center X coordinate of the Port.
     */
    void setCX(int cx) {

        this.cx = cx;
        this.setRX();

    }

    /**
     * Gets the Reference (bottom left) x value of this port.
     * @return
     */
    int getRX() {
        return this.rx;
    }

    /**
     * Sets the Reference x of this port.
     */
    private void setRX() {
        switch (this.getType()){
            case INPUT:
                this.rx = (int)(cx - gateSize * scaleFactor / 2);
                break;

            case OUTPUT:
                this.rx = (int)(cx - gateSize * scaleFactor / 4);
                break;

            default:
                this.rx = cx;
                break;
        }
    }

    /**
     * Gets the Center Y coordinate of the Port.
     *
     * @return The Center Y coordinate of the Port.
     */
    int getCY() {

        return this.cy;

    }

    /**
     * Sets the Center Y coordinate of the Port.
     *
     * @param cy An integer of the Center Y coordinate of the Port.
     */
    void setCY(int cy) {

        this.cy = cy;
        this.setRY();

    }

    /**
     * Gets the Reference y of this port.
     * @return
     */
    int getRY() {
        return this.ry;
    }

    /**
     * Sets the Reference y of this port.
     */
    private void setRY() {
        this.ry = (int)(cy - gateSize * scaleFactor / 4);
    }

    /**
     * Gets the X coordinate of the Port input/output port.
     *
     * @return The X coordinate of the Port port.
     */
    int getPortX() {

        switch (this.getType()) {

            case INPUT:
                return (int)(this.getCX() + gateSize * scaleFactor / 2);

            case OUTPUT:
                return (int)(this.getCX() - gateSize * scaleFactor / 2);

            default:
                return 0;

        }

    }

    /**
     * Gets the Y coordinate of the Port input/output port.
     *
     * @return The Y coordinate of the Port port.
     */
    int getPortY() {

        return this.getCY();

    }

    void addInput(String id) {

        inputs.add(id);

    }

    ArrayList<String> getInputs(){

        return this.inputs;

    }
}
