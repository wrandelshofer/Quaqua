/*
 * @(#)ActivatableColorUIResource.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.color;

/**
 * ActivatableUIResource is a resource, that can be rendered in an
 * an active state and an inactive state.
 * <p>
 * The active state is typically the state, in which a (sub-)component
 * can be interacted with.
 * <p>
 * If the component supports click-through, then this is the enabled
 * state of the (sub-)component.
 * <p>
 * If the component supports focus-request, then it is the enabled state of the
 * (sub-)component and the focused state of the window.
 * <p>
 * If the component does not support any of the above, then it is the enabled
 * state of the (sub-)component and the focused state of the component.
 */
public interface ActivatableUIResource {
    void setActive(boolean newValue);
}
