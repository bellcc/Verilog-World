package edu.miamioh.verilogEditor;

import edu.miamioh.simulator.Parse;
import edu.miamioh.simulator.RootModuleSimulator;
import edu.miamioh.verilogWorld.VerilogWorldController;

public class RunEditor implements Runnable {
	
		private String fileName;
		private RootModuleSimulator sim;
		private Parse compiler;

		public RunEditor(RootModuleSimulator sim, Parse compiler, String fileName) {
			this.fileName = fileName;
			this.sim = sim;
			this.compiler = compiler;
		}

		@Override
		public void run() {
			String rootPath = VerilogWorldController.getController().getRootPath();
			String filePath = rootPath + "core/assets/modules/" + fileName;

			new VerilogEditor(sim, compiler, rootPath, filePath);
		}
}
