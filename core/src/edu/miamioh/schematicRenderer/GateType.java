package edu.miamioh.schematicRenderer;

import edu.miamioh.simulator.WireRoleType;

/**
 * Created by bdshaffer73.
 */
enum GateType {

    INPUT(WireRoleType.INPUT), OUTPUT(WireRoleType.OUTPUT), REG(null), WIRE
            (null), AND(null), OR(null), XOR(null), XNOR(null), NAND(null), NOR
    (null), NOT(null), BLANK(null), MODULE(null);

    GateType(WireRoleType wrt) {
    }
}
