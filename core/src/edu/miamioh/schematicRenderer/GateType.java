package edu.miamioh.schematicRenderer;

import edu.miamioh.simulator.WireRoleType;

/**
 * Created by bdshaffer73.
 */
enum GateType {

    INPUT(WireRoleType.INPUT), OUTPUT(WireRoleType.OUTPUT), AND(null), OR
            (null), NOT(null), XOR(null), INVALID_ASSIGNMENT(null);

    GateType(WireRoleType wrt) {
    }
}
