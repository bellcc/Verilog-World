package edu.miamioh.verilogEditor;

public interface AutoCompleteListener {
    public void willAutoComplete(AutoBracket filter);

    public void didAutoComplete(AutoBracket filter);
}
