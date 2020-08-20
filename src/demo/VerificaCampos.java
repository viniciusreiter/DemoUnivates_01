
package demo;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Vinicius
 */
public class VerificaCampos extends PlainDocument {
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
    super.insertString(offs, str.replaceAll("[^0-9]", ""), a);
    }
    
}
