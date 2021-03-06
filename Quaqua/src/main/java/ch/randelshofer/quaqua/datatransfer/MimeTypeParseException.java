/*
 * @(#)MimeTypeParseException.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.datatransfer;

/**
 * A class to encapsulate MimeType parsing related exceptions
 * <p>
 * Implementation taken from java.awt.datatransfer.TypeParseException.java 1.10 01/12/03
 *
 * @author Werner Randelshofer
 * @version $Id$
 * @serial exclude
 */
public class MimeTypeParseException extends Exception {

    // use serialVersionUID from JDK 1.2.2 for interoperability
    private static final long serialVersionUID = -5604407764691570741L;

    /**
     * Constructs a MimeTypeParseException with no specified detail message.
     */
    public MimeTypeParseException() {
        super();
    }

    /**
     * Constructs a MimeTypeParseException with the specified detail message.
     *
     * @param s the detail message.
     */
    public MimeTypeParseException(String s) {
        super(s);
    }
} // class MimeTypeParseException
