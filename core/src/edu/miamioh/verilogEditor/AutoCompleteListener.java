package edu.miamioh.verilogEditor;

public interface AutoCompleteListener {
    void willAutoComplete(AutoBracket filter);

    void didAutoComplete(AutoBracket filter);
}
