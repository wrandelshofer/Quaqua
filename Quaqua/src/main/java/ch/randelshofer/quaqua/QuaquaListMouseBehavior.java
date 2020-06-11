/*
 * @(#)QuaquaListMouseBehavior.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua;

import javax.swing.JList;

/**
 * Mouse behavior for JList. This class simulates Mavericks NSTableView behavior.
 */
public class QuaquaListMouseBehavior extends QuaquaGenericListMouseBehavior {

    public QuaquaListMouseBehavior(JList list) {
        super(new JListModel(list));
    }

    public QuaquaListMouseBehavior(GenericList list) {
        super(list);
    }
}
