package edu.miamioh.schematicRenderer;

import edu.miamioh.simulator.WireRoleType;

/**
 * Created by bdshaffer73.
 */
enum GateType {

    INPUT(WireRoleType.INPUT), OUTPUT(WireRoleType.OUTPUT), REG(null), WIRE
            (null), AND(null), OR(null), NOT(null), XOR(null), BLANK(null);

    GateType(WireRoleType wrt) {
    }
}
