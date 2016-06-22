package edu.miamioh.schematicRenderer;

import edu.miamioh.simulator.WireRoleType;

/**
 * Created by bdshaffer73.
 */
enum GateType {

    INPUT(WireRoleType.INPUT), OUTPUT(WireRoleType.OUTPUT), REG(null), WIRE
            (null), AND(null), NAND(null), OR(null), NOR(null), XOR(null), XNOR
            (null), NOT(null), BLANK(null);

    GateType(WireRoleType wrt) {
    }
}
