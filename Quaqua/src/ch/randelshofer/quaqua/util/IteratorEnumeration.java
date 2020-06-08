/*
 * @(#)IteratorEnumeration.java
 * Quaqua Look and Feel. Copyright 2020 © Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.util;

import java.util.*;
/**
 * This Enumeration is a wrapper over an Iterator.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class IteratorEnumeration implements Enumeration {
    private Iterator iterator;

    /** Creates a new instance. */
    public IteratorEnumeration(Iterator iterator) {
        this.iterator = iterator;
    }

    public boolean hasMoreElements() {
        return iterator.hasNext();
    }

    public Object nextElement() {
        return iterator.next();
    }

}
