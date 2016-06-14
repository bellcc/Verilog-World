package edu.miamioh.verilogEditor;

public class RunEditor implements Runnable {
	
		private String fileName;

		public RunEditor(String fileName) {
			this.fileName = fileName;
		}

		@Override
		public void run() {
			String workingDir = System.getProperty("user.dir");
			String[] args = {workingDir + "\\..\\", fileName};
			VerilogEditor.main(args);
		}
}
