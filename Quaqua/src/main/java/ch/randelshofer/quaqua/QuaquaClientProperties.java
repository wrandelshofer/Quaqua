/*
 * @(#)QuaquaClientProperties.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua;

/**
 * This interface defines all public client properties supported by
 * the Quaqua look and feel.
 */
public interface QuaquaClientProperties {

    /**
     * If a component is used as a cell renderer, this property can be used to
     * specify the JComponent which contains the cels. For example, set this to
     * a JTable, a JTree or a JList object.
     * <p>
     * This has the following effect:
     * <ul>
     * <li>The cell renderer can draw itself differently, so that it fits
     * visually into a cell of the specified JComponent.</li>
     * <li>The cell renderer can determine its selection color depending on the
     * focus state of the specified JComponent rather than on its own focus state.</li>
     * </ul>
     * <p>
     * This property can be set on any JComponent.
     * <p>
     * Value: {@value #QUAQUA_COMPONENT_CELL_RENDERER_FOR_CLIENT_PROPERTY}.
     */
    String QUAQUA_COMPONENT_CELL_RENDERER_FOR_CLIENT_PROPERTY = "Quaqua.Component.cellRendererFor";
    /**
     * If set to a java.awt.Insets value, specifies the margin around the visually perceived borders of a component and its clip bounds.
     * If no value is specified, then the UIManager property Component.visualMargin is used.
     * <p>
     * This property is honoured only by ComponentUI's that have been implemented in Quaqua. Thus the following components ignore currently the visual margin:
     * <ul>
     * <li>JSlider</li>
     * <li>JSpinner</li>
     * <li>JTabbedPane with Panther design</li>
     * </ul>
     * <p>
     * This property can be set on any JComponent.
     * <p>
     * Value: {@value #QUAQUA_COMPONENT_VISUAL_MARGIN_CLIENT_PROPERTY}.
     */
    String QUAQUA_COMPONENT_VISUAL_MARGIN_CLIENT_PROPERTY = "Quaqua.Component.visualMargin";
    /**
     * If set to a java.awt.Insets value, overrides the insets of the border
     * on the component.
     * <p>
     * This property can be set on JButton.
     * <p>
     * Value: {@value #QUAQUA_BORDER_INSETS_CLIENT_PROPERTY}.
     */
    String QUAQUA_BORDER_INSETS_CLIENT_PROPERTY = "Quaqua.Border.insets";
    /**
     * Changes the style of a JLabel or of the label portion of a JCheckBox.
     * <p>
     * Can be set to: "plain", "shadow" or "emboss".
     * If no value is specified, the plain style is used.
     * <p>
     * This property can be set on JLabel, and JCheckBox.
     * <p>
     * Value: {@value #QUAQUA_LABEL_STYLE_CLIENT_PROPERTY}.
     */
    String QUAQUA_LABEL_STYLE_CLIENT_PROPERTY = "Quaqua.Label.style";
    /**
     * Changes the style of a JList, if set to: "striped", "plain", or
     * "comboPopup".
     * If no value is specified, the plain style is used.
     * <p>
     * This property can be set on JList.
     * <p>
     * Value: {@value #QUAQUA_LIST_STYLE_CLIENT_PROPERTY}.
     */
    String QUAQUA_LIST_STYLE_CLIENT_PROPERTY = "Quaqua.List.style";
    /**
     * Changes the style of a JSplitPane, if set to: "thumb",  or
     * "bar".
     * If no value is specified, the "thumb" style is used.
     * <p>
     * This property can be set on JSplitPane.
     * <p>
     * Value: {@value #QUAQUA_LIST_STYLE_CLIENT_PROPERTY}.
     */
    String QUAQUA_SPLIT_PANE_STYLE_CLIENT_PROPERTY = "Quaqua.SplitPane.style";
    /**
     * Changes the style of a JTable, if set to: "striped" or to "plain".
     * If no value is specified, the plain style is used.
     * <p>
     * This property can be set on JTable.
     * <p>
     * Value: {@value #QUAQUA_TABLE_STYLE_CLIENT_PROPERTY}.
     */
    String QUAQUA_TABLE_STYLE_CLIENT_PROPERTY = "Quaqua.Table.style";
    /**
     * Places the buttons of the scroll bar together or apart.
     * Supported values are Boolean.TRUE and Boolean.FALSE.
     * <p>
     * If no value is specified, the UIManager property ScrollBar.placeButtonsTogether is used.
     * <p>
     * This property can be set on JScrollBar.
     * <p>
     * Value: {@value #QUAQUA_SCROLL_BAR_PLACE_BUTTONS_TOGETHER_CLIENT_PROPERTY}.
     */
    String QUAQUA_SCROLL_BAR_PLACE_BUTTONS_TOGETHER_CLIENT_PROPERTY = "Quaqua.ScrollBar.placeButtonsTogether";
    /**
     * Changes the style of a list, if set to: "plain" or to "bar".
     * <p>
     * If no value is specified, the plain style is used.
     * <p>
     * This property can be set on JScrollPane.
     * <p>
     * Value: {@value #QUAQUA_SCROLL_PANE_STYLE_CLIENT_PROPERTY}.
     */
    String QUAQUA_SCROLL_PANE_STYLE_CLIENT_PROPERTY = "Quaqua.ScrollPane.style";
    /**
     * Enables or disables focus border drawing on a JScrollPane if
     * set to a Boolean.
     * <p>
     * If no value is specified, a focus border is drawn.
     * <p>
     * This property can be set on JScrollPane.
     * <p>
     * Value: {@value #QUAQUA_SCROLL_PANE_STYLE_CLIENT_PROPERTY}.
     */
    String QUAQUA_DRAW_FOCUS_BORDER_CLIENT_PROPERTY = "Quaqua.drawFocusBorder";
    /**
     * Changes the size variant of a component, if set to regular, small or mini.
     * The default value is regular.
     * <p>
     * Note: Setting this property may cause an UI delegate, to change the font
     * of the component.
     * <p>
     * This property can be set on any JComponent.
     * <p>
     * Value: {@value #JCOMPONENT_SIZE_VARIANT_CLIENT_PROPERTY}.
     */
    String JCOMPONENT_SIZE_VARIANT_CLIENT_PROPERTY = "JComponent.sizeVariant";
    /**
     * If set to Boolean.TRUE causes the JComboBox to render without a border,
     * so that it fits visually into a JTable cell.
     * <p>
     * Alternatively to this, {@link #QUAQUA_COMPONENT_CELL_RENDERER_FOR_CLIENT_PROPERTY}
     * can be used.
     * <p>
     * This property can be set on JComboBox.
     * <p>
     * Value: {@value #JCOMBO_BOX_IS_TABLE_CELL_EDITOR_CLIENT_PROPERTY}.
     */
    String JCOMBO_BOX_IS_TABLE_CELL_EDITOR_CLIENT_PROPERTY = "JComboBox.isTableCellEditor";

    /**
     * If set to a java.awt.Component, specifies the component used for
     * rendering the file preview in the last column of the column view in the
     * file browser.
     * <p>
     * If this property is not set, or if its value is null, the file chooser
     * renders the preview using its own renderer.
     * <p>
     * This property can be set on JFileChooser.
     * <p>
     * Value: {@value #QUAQUA_FILE_CHOOSER_PREVIEW_CLIENT_PROPERTY}.
     */
    String QUAQUA_FILE_CHOOSER_PREVIEW_CLIENT_PROPERTY = "Quaqua.FileChooser.preview";
    /**
     * Changes the style of a button, if set to one of the following String
     * values:
     * bevel, square, push, colorWell, toggle, toggleWest, toggleCenter,
     * toggleEast, toolBar, toolBarRollover, toolBarTab, help.
     * <p>
     * If no value is specified, the push style is used on JButton,
     * the toggle style is used on JToggleButton.
     * <p>
     * This property overrides the {@link #JBUTTON_BUTTON_TYPE_PROPERTY} property.
     * <p>
     * This property can be set on JButton and JToggleButton.
     * <p>
     * Value: {@value #QUAQUA_BUTTON_STYLE_CLIENT_PROPERTY}.
     */
    String QUAQUA_BUTTON_STYLE_CLIENT_PROPERTY = "Quaqua.Button.style";
    /**
     * Changes the style of a button, if set to one of the following String
     * values: icon, toolbar, text.
     * If no value is specified, the text style is used.
     * <p>
     * This property is here for backwards compatibility with Apple's Aqua Look
     * and Feel only. It is overriden, if the
     * {@link #QUAQUA_BUTTON_STYLE_CLIENT_PROPERTY} property is set.
     * <p>
     * This property can be set on JButton.
     * <p>
     * Value: {@value #JBUTTON_BUTTON_TYPE_PROPERTY}.
     */
    String JBUTTON_BUTTON_TYPE_PROPERTY = "JButton.buttonType";
    /**
     * Sets the position of a segmented button, if set to one of the following
     * String values: "first",
     * "middle", "last", "only".
     * <p>
     * This property can be set on JButton and JToggleButton.
     * <p>
     * Value: {@value #JBUTTON_BUTTON_TYPE_PROPERTY}.
     */
    String JBUTTON_SEGMENT_POSITION_TYPE_PROPERTY = "JButton.segmentPosition";

    /**
     * Specifies the index of the destructive option on the JOptionPane.
     * <p>
     * If no value is specified, all option buttons are aligned to the lower
     * right corner of the option pane.
     * <p>
     * If an Integer value is specified, all option buttons with a lower index
     * than the Integer value are aligned to the lower right corner,
     * the other option buttons are left aligned with the message text.
     * <p>
     * This property can be set on JOptionPane.
     * <p>
     * Value: {@value #QUAQUA_OPTION_PANE_DESTRUCTIVE_OPTION_CLIENT_PROPERTY}.
     */
    String QUAQUA_OPTION_PANE_DESTRUCTIVE_OPTION_CLIENT_PROPERTY = "Quaqua.OptionPane.destructiveOption";
    /**
     * A float value which specifies the alpha transparency of the popup window.
     * If no value or null is specified, a system dependent transparency value
     * is chosen.
     * <p>
     * This property can be set on JPopupMenu.
     * <p>
     * Value: {@value #QUAQUA_POPUP_MENU_WINDOW_ALPHA_CLIENT_PROPERTY}.
     */
    String QUAQUA_POPUP_MENU_WINDOW_ALPHA_CLIENT_PROPERTY = "Quaqua.PopupMenu.windowAlpha";
    /**
     * If set to Boolean.TRUE, turns a JDialog or a JFrame into a palette window.
     * The title bar of a palette window will always render in activated state.
     * It is your responsibility to hide the palette window, when none of your application has focus.
     * <p>
     * This property will only take into effect, when the JRootPane decorates the window.
     * <p>
     * This property can be set on JRootPane.
     * <p>
     * Value: {@value #QUAQUA_ROOT_PANE_IS_PALETTE_CLIENT_PROPERTY}.
     */
    String QUAQUA_ROOT_PANE_IS_PALETTE_CLIENT_PROPERTY = "Quaqua.RootPane.isPalette";
    /**
     * If set to Boolean.TRUE, renders the title bar of a JDialog or a JFrame
     * on the left instead of on the top.
     * <p>
     * This property will only take into effect, when the JRootPane decorates the window.
     * <p>
     * This property can be set on JRootPane.
     * <p>
     * Value: {@value #QUAQUA_ROOT_PANE_IS_VERTICAL_CLIENT_PROPERTY}.
     */
    String QUAQUA_ROOT_PANE_IS_VERTICAL_CLIENT_PROPERTY = "Quaqua.RootPane.isVertical";
    /**
     * If set to Boolean.TRUE, fuses the title bar of the JFrame with the
     * JToolBar at the top of the window, and fuses the border with the JToolBar
     * at the bottom of the window, if the appropriate style of the JToolBar is set.
     * <p>
     * This property can be set on JRootPane.
     * <p>
     * Value: {@value #APPLE_AWT_BRUSH_METAL_LOOK_CLIENT_PROPERTY}.
     */
    String APPLE_AWT_BRUSH_METAL_LOOK_CLIENT_PROPERTY = "apple.awt.brushMetalLook";
    /**
     * If set to Boolean.TRUE, the red close button in the title bar visually
     * indicates that the document contained in the window has changed.
     * See Apple's Technical Q&A QA1146.
     * <p>
     * This property can be set on JRootPane.
     * <p>
     * This property is overriden by {@link #WINDOW_DOCUMENT_MODIFIED_CLIENT_PROPERTY}.
     * <p>
     * Value: {@value #WINDOW_MODIFIED_CLIENT_PROPERTY}.
     */
    String WINDOW_MODIFIED_CLIENT_PROPERTY = "windowModified";
    /**
     * If set to Boolean.TRUE, the red close button in the title bar visually
     * indicates that the document contained in the window has changed.
     * See Apple's Technical Q&A QA1146.
     * <p>
     * This property can be set on JRootPane.
     * <p>
     * This property overrides {@link #WINDOW_MODIFIED_CLIENT_PROPERTY}.
     * <p>
     * Value: {@value #WINDOW_MODIFIED_CLIENT_PROPERTY}.
     */
    String WINDOW_DOCUMENT_MODIFIED_CLIENT_PROPERTY = "Window.documentModified";
    /**
     * If set to a float, sets the window alpha transparency.
     * <p>
     * This property can be set on JRootPane.
     * <p>
     * Value: {@value #WINDOW_ALPHA_CLIENT_PROPERTY}.
     */
    String WINDOW_ALPHA_CLIENT_PROPERTY = "Window.alpha";

    /**
     * Switches rendering of the content border on and off. Set this to
     * Boolean.FALSE to not draw the content border of the tabbed pane.
     * <p>
     * This property can be set on JTabbedPane.
     * <p>
     * Value: {@value #QUAQUA_TABBED_PANE_CONTENT_BORDER_PAINTED_CLIENT_PROPERTY}.
     */
    String QUAQUA_TABBED_PANE_CONTENT_BORDER_PAINTED_CLIENT_PROPERTY = "Quaqua.TabbedPane.contentBorderPainted";
    /**
     * Changes the alignment of the tabs in a tabbed pane if set to
     * {@link javax.swing.SwingConstants#LEADING} or
     * {@link javax.swing.SwingConstants#CENTER}.
     * <p>
     * This property can be set on JTabbedPane.
     * <p>
     * Value: {@value #QUAQUA_TABBED_PANE_TAB_ALIGNMENT_CLIENT_PROPERTY}.
     */
    String QUAQUA_TABBED_PANE_TAB_ALIGNMENT_CLIENT_PROPERTY = "Quaqua.TabbedPane.tabAlignment";
    /**
     * For use with tabbed panes with scroll layout policy. Set this to
     * Boolean.FALSE to prevent tabs from being shortened.
     * <p>
     * This property can be set on JTabbedPane.
     * <p>
     * Value: {@value #QUAQUA_TABBED_PANE_SHORTEN_TABS_CLIENT_PROPERTY}.
     */
    String QUAQUA_TABBED_PANE_SHORTEN_TABS_CLIENT_PROPERTY = "Quaqua.TabbedPane.shortenTabs";
    /**
     * Specifies the background color of the content area.
     * <p>
     * This property needs to be set on a child of a JTabbedPane. This allows to
     * have individual background colors for each child of the tabbed pane.
     * <p>
     * Value: {@value #QUAQUA_TABBED_PANE_CHILD_CONTENT_BACKGROUND_CLIENT_PROPERTY}.
     */
    String QUAQUA_TABBED_PANE_CHILD_CONTENT_BACKGROUND_CLIENT_PROPERTY = "Quaqua.TabbedPaneChild.contentBackground";
    /**
     * Specifies the insets of the content area.
     * <p>
     * This property needs to be set on a child of a JTabbedPane.
     * This allows to have individual insets for each child of the tabbed pane.
     * <p>
     * Value: {@value #QUAQUA_TABBED_PANE_CHILD_CONTENT_INSETS_CLIENT_PROPERTY}.
     */
    String QUAQUA_TABBED_PANE_CHILD_CONTENT_INSETS_CLIENT_PROPERTY = "Quaqua.TabbedPaneChild.contentInsets";

    /**
     * Switches rendering of the content border on and off. Set this to
     * Boolean.FALSE to not draw the content border of the tabbed pane.
     * <p>
     * This property can be set on any JTextComponent (that is on JTextField,
     * JTextArea, JTextPane, JEditorPane, JPasswordField).
     * <p>
     * Value: {@value #QUAQUA_TEXT_COMPONENT_AUTO_SELECT_CLIENT_PROPERTY}.
     */
    String QUAQUA_TEXT_COMPONENT_AUTO_SELECT_CLIENT_PROPERTY = "Quaqua.TextComponent.autoSelect";
    /**
     * Set this to Boolean.FALSE to disable the text component popup menu.
     * <p>
     * This property can be set on any JTextComponent (that is on JTextField,
     * JTextArea, JTextPane, JEditorPane, JPasswordField).
     * <p>
     * Value: {@value #QUAQUA_TEXT_COMPONENT_SHOW_POPUP_CLIENT_PROPERTY}.
     */
    String QUAQUA_TEXT_COMPONENT_SHOW_POPUP_CLIENT_PROPERTY = "Quaqua.TextComponent.showPopup";
    /**
     * If set to "search", renders a rounded border like the border of Mac OS X
     * SpotLight search fields.
     * <p>
     * If set to "plain", renders a rectangular border like for regular text
     * fields. The default value is "plain".
     * <p>
     * This property can be set on JTextField.
     * <p>
     * This property overrides {@link #JTEXT_FIELD_VARIANT}.
     * <p>
     * Value: {@value #QUAQUA_TEXT_COMPONENT_SHOW_POPUP_CLIENT_PROPERTY}.
     */
    String QUAQUA_TEXT_FIELD_STYLE_CLIENT_PROPERTY = "Quaqua.TextField.style";
    /**
     * If set to "search", renders a rounded border like the border of Mac OS X
     * SpotLight search fields.
     * <p>
     * If set to "plain", renders a rectangular border like for regular text
     * fields. The default value is "plain".
     * <p>
     * This property can be set on JTextField.
     * <p>
     * This property is overriden by {@link #QUAQUA_TEXT_FIELD_STYLE_CLIENT_PROPERTY}.
     * <p>
     * Value: {@value #JTEXT_FIELD_VARIANT}.
     */
    String JTEXT_FIELD_VARIANT = "JTextField.variant";
    /**
     * Changes the style of a tool bar, if set to: plain, title, bottom or gradient.
     * If no value is specified, the plain style is used.
     * <p>
     * The "title" style requires that the client property
     * apple.awt.brushMetalLook is set on the JRootPane.
     * <p>
     * This property can be set on JTooLBar.
     * <p>
     * Value: {@value #QUAQUA_TOOL_BAR_STYLE_CLIENT_PROPERTY}.
     */
    String QUAQUA_TOOL_BAR_STYLE_CLIENT_PROPERTY = "Quaqua.ToolBar.style";
    /**
     * If set to Boolean.FALSE, turns the divider line between the toolbar and
     * the center area of its panel off.
     * <p>
     * This property can be set on JTooLBar.
     * <p>
     * Value: {@value #QUAQUA_TOOL_BAR_IS_DIVIDER_DRAWN_CLIENT_PROPERTY}.
     */
    String QUAQUA_TOOL_BAR_IS_DIVIDER_DRAWN_CLIENT_PROPERTY = "Quaqua.ToolBar.isDividerDrawn";
    /**
     * If set to Boolean.TRUE, turns a roll over effect for the buttons on the toolbar on.
     * This requires Java 1.4 to work.
     * <p>
     * This property can be set on JTooLBar.
     * <p>
     * Value: {@value #QUAQUA_TOOL_BAR_IS_ROLLOVER_CLIENT_PROPERTY}.
     */
    String QUAQUA_TOOL_BAR_IS_ROLLOVER_CLIENT_PROPERTY = "Quaqua.ToolBar.isRollover";
    /**
     * Changes the style of a tree, if set to: "plain", "striped" or "sourceList".
     * <p>
     * If no value is specified, the plain style is used.
     * <p>
     * This property can be set on JTree.
     * <p>
     * Value: {@value #QUAQUA_TREE_STYLE_CLIENT_PROPERTY}.
     */
    String QUAQUA_TREE_STYLE_CLIENT_PROPERTY = "Quaqua.Tree.style";
    /**
     * Changes the selection style of a tree if set to a boolean value.
     * <p>
     * If no value is specified, the cells are assumed to be transparent.
     * <p>
     * This property can be set on JTree.
     * <p>
     * Value: {@value #QUAQUA_TREE_IS_CELL_FILLED_CLIENT_PROPERTY}.
     */
    String QUAQUA_TREE_IS_CELL_FILLED_CLIENT_PROPERTY = "Quaqua.Tree.isCellFiled";
    /**
     * If set to Boolean.TRUE, turns a JInternalFrame into a palette window.
     * See Sun's Java Look And Feel Design Guidelines for Palettes.
     * <p>
     * This property can be set on JInternalFrame.
     * <p>
     * Value: {@value #QUAQUA_TREE_STYLE_CLIENT_PROPERTY}.
     */
    String JINTERNAL_FRAME_IS_PALETTE_CLIENT_PROPERTY = "JInternalFrame.isPalette";
}
