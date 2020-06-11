/*
 * @(#)ActivatableColorUIResource.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.color;

/**
 * ActivatableUIResource is a resource, that can be rendered in
 * a focused state and in a non-focused state.
 */
public interface FocusableUIResource {
    void setFocused(boolean newValue);
}
