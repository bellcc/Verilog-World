package edu.miamioh.verilogEditor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


public class AutoBracket extends DocumentFilter {


    private List<AutoCompleteListener> listeners;

    public AutoBracket() {
        listeners = new ArrayList<AutoCompleteListener>(25);
    }

    public void addAutoCompleteListener(AutoCompleteListener listener) {
        listeners.add(listener);
    }

    public void removeAutoCompleteListener(AutoCompleteListener listener) {
        listeners.add(listener);
    }

    protected void fireWillAutoComplete() {
        for (AutoCompleteListener listener : listeners) {
            listener.willAutoComplete(this);
        }
    }

    protected void fireDidAutoComplete() {
        for (AutoCompleteListener listener : listeners) {
            listener.didAutoComplete(this);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        super.replace(fb, offset, length, text, attrs); //To change body of generated methods, choose Tools | Templates.
        if (text.endsWith("{")) {
            fireWillAutoComplete();
            fb.insertString(fb.getDocument().getLength(), "}", attrs);
            fireDidAutoComplete();
        }
    }

}

