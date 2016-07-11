package edu.miamioh.verilogEditor;

import edu.miamioh.simulator.Parse;
import edu.miamioh.simulator.RootModuleSimulator;
import edu.miamioh.worldEditor.WorldEditorController;

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

			String rootPath = WorldEditorController.getCurrentController().getCurrentLevel().getProject().getAbsolutePath();
			String filePath = rootPath + "/modules/" + fileName;

			new VerilogEditor(sim, compiler, rootPath, filePath, fileName);
		}
}
