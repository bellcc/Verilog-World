package edu.miamioh.verilogEditor;

public class RunEditor implements Runnable {

		public RunEditor() {}

		@Override
		public void run() {
			String[] args = {"C:\\Users\\clark\\Documents\\Verilog-World\\", "MemTest.v"};
			VerilogEditor.main(args);
		}
}
