package edu.miamioh.verilogEditor;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

class IntegerRangeDocumentFilter extends DocumentFilter {
	private MyTextPane				codeText;
	public IntegerRangeDocumentFilter(MyTextPane codeText) {
	  this.codeText = codeText;
  }
  
  @Override
  public void insertString(DocumentFilter.FilterBypass fb, int offset, String string,
      AttributeSet attr) throws BadLocationException {
	  
		    System.out.println("insert string");
		    System.out.println(offset);
		    super.remove(fb, 0, codeText.getText().length());
		    super.insertString(fb, offset, string, attr);
	  
  }

  @Override
  public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
      throws BadLocationException {
	  
		    System.out.println("remove");
		    System.out.println(offset);
		    super.remove(fb, offset, length);
  }

  @Override
  public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
      AttributeSet attrs) throws BadLocationException {
		    System.out.println("replace");
		    System.out.println(offset);
		    super.replace(fb, offset, length, text, attrs);
  }
  
  public void setStart(int start){
  }
}