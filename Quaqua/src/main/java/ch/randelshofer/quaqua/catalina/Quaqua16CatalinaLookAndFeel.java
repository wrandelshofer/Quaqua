/*
 * @(#)Quaqua16CatalinaLookAndFeel.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.catalina;

import ch.randelshofer.quaqua.BasicQuaquaNativeLookAndFeel;
import ch.randelshofer.quaqua.QuaquaManager;
import ch.randelshofer.quaqua.border.VisualMarginBorder;
import ch.randelshofer.quaqua.color.ActivatableColorUIResource;
import ch.randelshofer.quaqua.color.AlphaColorUIResource;
import ch.randelshofer.quaqua.color.GradientColor;
import ch.randelshofer.quaqua.mavericks.Quaqua16MavericksLookAndFeel;
import ch.randelshofer.quaqua.osx.OSXAquaPainter;
import ch.randelshofer.quaqua.osx.OSXPreferences;
import ch.randelshofer.quaqua.util.GroupBox;
import ch.randelshofer.quaqua.yosemite.QuaquaYosemiteSideBarSelectionBorder;

import javax.swing.UIDefaults;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.InsetsUIResource;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;

public class Quaqua16CatalinaLookAndFeel extends BasicQuaquaNativeLookAndFeel {
    /**
     * Creates a new instance.
     */
    public Quaqua16CatalinaLookAndFeel() {
        // Our target look and feel is Apple's AquaLookAndFeel.
        super("apple.laf.AquaLookAndFeel");
    }

    /**
     * Creates a new instance.
     */
    protected Quaqua16CatalinaLookAndFeel(String className) {
        super(className);
    }
    @Override
    public String getDescription() {
        return "The Quaqua Catalina Look and Feel "
                + QuaquaManager.getVersion();
    }

    @Override
    public String getName() {
        return "Quaqua Catalina";
    }


    protected Object toolBarTitleBackground(UIDefaults table) {
        final String javaVersion = QuaquaManager.getProperty("java.version", "");
        if (javaVersion.startsWith("1.5") || javaVersion.startsWith("1.6")) {
            return table.get("control");
        } else {
            return new ActivatableColorUIResource(new ColorUIResource(211, 211, 211), new ColorUIResource(246, 246, 246));
        }
    }

    /**
     * Returns the base font for which system fonts are derived.
     *
     * @return Helvetica, Plain, 13.
     */
    protected Font getBaseSystemFont() {
        return new FontUIResource("San Francisco", Font.PLAIN, 13);
    }

    @Override
    protected void initClassDefaults(UIDefaults table) {
        String basicPrefix = "javax.swing.plaf.basic.Basic";
        String quaquaPrefix = "ch.randelshofer.quaqua.Quaqua";
        String quaquaJaguarPrefix = "ch.randelshofer.quaqua.jaguar.QuaquaJaguar";
        String quaquaPantherPrefix = "ch.randelshofer.quaqua.panther.QuaquaPanther";
        String quaquaLeopardPrefix = "ch.randelshofer.quaqua.leopard.QuaquaLeopard";
        String quaquaLionPrefix = "ch.randelshofer.quaqua.lion.QuaquaLion";
        String quaquaMavericksPrefix = "ch.randelshofer.quaqua.mavericks.QuaquaMavericks";

        // NOTE: Change code below, to override different
        // UI classes of the target look and feel.
        Object[] uiDefaults = {
                "BrowserUI", quaquaPrefix + "BrowserUI",
                "ButtonUI", quaquaPrefix + "ButtonUI",
                "CheckBoxUI", quaquaPrefix + "CheckBoxUI",
                "ColorChooserUI", quaquaPrefix + "ColorChooserUI",
                "FileChooserUI", quaquaMavericksPrefix + "FileChooserUI",
                "FormattedTextFieldUI", quaquaPrefix + "FormattedTextFieldUI",
                "RadioButtonUI", quaquaPrefix + "RadioButtonUI",
                "ToggleButtonUI", quaquaPrefix + "ToggleButtonUI",
                "SeparatorUI", quaquaPantherPrefix + "SeparatorUI",
                //"MenuSeparatorUI", quaquaPantherPrefix + "SeparatorUI",
                //"ProgressBarUI", basicPrefix + "ProgressBarUI",
                "ScrollBarUI", quaquaPrefix + "ScrollBarUI",
                "ScrollPaneUI", quaquaPrefix + "ScrollPaneUI",
                "SplitPaneUI", quaquaPrefix + "SplitPaneUI",
                //"SliderUI", quaquaPrefix + "SliderUI",
                //"SpinnerUI", quaquaPrefix + "SpinnerUI",
                "ToolBarSeparatorUI", quaquaPrefix + "ToolBarSeparatorUI",
                //"PopupMenuSeparatorUI", quaquaPantherPrefix + "SeparatorUI",
                "TextAreaUI", quaquaPrefix + "TextAreaUI",
                "TextFieldUI", quaquaPrefix + "TextFieldUI",
                "PasswordFieldUI", quaquaPrefix + "PasswordFieldUI",
                "TextPaneUI", quaquaPrefix + "TextPaneUI",
                "EditorPaneUI", quaquaPrefix + "EditorPaneUI",
                "TreeUI", quaquaPrefix + "TreeUI",
                "LabelUI", quaquaPrefix + "LabelUI",
                "ListUI", quaquaPrefix + "ListUI",
                // FIXME We use the TabbedPaneUI in apple.laf.AquaLookAndFeel for now.
                //"TabbedPaneUI", quaquaPantherPrefix + "ScrollTabbedPaneUI",
                "ToolBarUI", quaquaPrefix + "ToolBarUI",
                //"ToolTipUI", basicPrefix + "ToolTipUI",
                "ComboBoxUI", quaquaPrefix + "ComboBoxUI",
                "TableUI", quaquaPrefix + "TableUI",
                //"TableHeaderUI", quaquaPrefix + "TableHeaderUI",
                // "InternalFrameUI", basicPrefix + "InternalFrameUI",
                //"DesktopPaneUI", quaquaPrefix + "DesktopPaneUI",
                //"DesktopIconUI", basicPrefix + "DesktopIconUI",
                "OptionPaneUI", quaquaPrefix + "OptionPaneUI",
                "PanelUI", quaquaPrefix + "PanelUI",
                "ViewportUI", quaquaPrefix + "ViewportUI",
                // Do not create a RootPaneUI on our own, unless we also
                // create our own ButtonUI. Aqua's RootPaneUI is responsible
                // for updating the border of the ButtonUI, when it is the default,
                // and for propagating window activation/dectivation events to
                // all the child components of a window.
                "RootPaneUI", quaquaPrefix + "RootPaneUI",};
        putDefaults(table, uiDefaults);

        /*
        // Popup menu fix only works fully when we have all AWT event permission
        SecurityManager security = System.getSecurityManager();
        try {
        if (security != null) {
        security.checkPermission(sun.security.util.SecurityConstants.ALL_AWT_EVENTS_PERMISSION);
        }
        uiDefaults = new Object[] {
        "PopupMenuUI", quaquaPrefix + "PopupMenuUI",
        };
        } catch (SecurityException e) {
        // do nothing
        }*/
        uiDefaults = new Object[]{
                "PopupMenuUI", quaquaPrefix + "PopupMenuUI",};
        putDefaults(table, uiDefaults);


        // FIXME Menu related workarounds work only if useScreenMenuBar is off.
        if (!isUseScreenMenuBar()) {
            uiDefaults = new Object[]{
                    "MenuBarUI", quaquaPrefix + "MenuBarUI",
                    "MenuUI", quaquaPrefix + "MenuUI",
                    "MenuItemUI", quaquaPrefix + "MenuItemUI",
                    "CheckBoxMenuItemUI", quaquaPrefix + "MenuItemUI",
                    "RadioButtonMenuItemUI", quaquaPrefix + "MenuItemUI"
            };
            putDefaults(table, uiDefaults);
        }
    }

    @Override
    protected void initDesignDefaults(UIDefaults table) {
        final String baseSystemFontName = getBaseSystemFont().getName();
        final String sidebarFontName = "San Francisco";

        ColorUIResource disabledForeground = new ColorUIResource(128, 128, 128);
        Object menuBackground = new ColorUIResource(0xffffff);
        ColorUIResource menuSelectionForeground = new ColorUIResource(0xffffff);
        Object toolBarBackground = toolBarTitleBackground(table);
        Object panelBackground = new ColorUIResource(0xededed);
        ColorUIResource listAlternateBackground = new ColorUIResource(0xf3f6fa);

        BorderUIResource.CompoundBorderUIResource browserCellBorder = new BorderUIResource.CompoundBorderUIResource(
                new BorderUIResource.MatteBorderUIResource(0, 0, 1, 0, new ColorUIResource(0xffffff)),
                new BorderUIResource.EmptyBorderUIResource(0, 4, 1, 0));
        Object scrollPaneBorder = new UIDefaults.ProxyLazyValue(
                "ch.randelshofer.quaqua.QuaquaNativeScrollPaneBorder",
                new Object[]{new Insets(1,1,1,1)}
        );
        Object textFieldBorder = new UIDefaults.ProxyLazyValue(
                "ch.randelshofer.quaqua.QuaquaNativeTextFieldBorder$UIResource",
                new Object[]{new Insets(-3, 0, 4, 0), new Insets(6, 8, 6, 8), true});

        Color sidebarListSelectionBackground = (Color) table.get("listHighlight");


        String sideBarIconsPrefix = "/System/Library/CoreServices/CoreTypes.bundle/Contents/Resources/Sidebar";
        ColorUIResource sideBarIconColor = new ColorUIResource(125, 134, 147);
        ColorUIResource sideBarIconSelectionColor = new ColorUIResource(0xffffff);
        Object scrollBarThumb = new UIDefaults.ProxyLazyValue(
                "ch.randelshofer.quaqua.lion.QuaquaLionScrollBarThumbBorder");
        Object scrollBarTrack = new UIDefaults.ProxyLazyValue(
                "ch.randelshofer.quaqua.lion.QuaquaLionScrollBarTrackBorder");

        String iconPrefix = "/System/Library/CoreServices/CoreTypes.bundle/Contents/Resources/";

        Object[] uiDefaults = {
                "Browser.expandedIcon", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
                new Object[]{lionDir + "Browser.disclosureIcons.png", 6, Boolean.TRUE, 0}),
                "Browser.expandingIcon", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
                new Object[]{lionDir + "Browser.disclosureIcons.png", 6, Boolean.TRUE, 1}),
                "Browser.focusedSelectedExpandedIcon", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
                new Object[]{lionDir + "Browser.disclosureIcons.png", 6, Boolean.TRUE, 2}),
                "Browser.focusedSelectedExpandingIcon", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
                new Object[]{lionDir + "Browser.disclosureIcons.png", 6, Boolean.TRUE, 3}),
                "Browser.selectedExpandedIcon", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
                new Object[]{lionDir + "Browser.disclosureIcons.png", 6, Boolean.TRUE, 4}),
                "Browser.selectedExpandingIcon", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
                new Object[]{lionDir + "Browser.disclosureIcons.png", 6, Boolean.TRUE, 5}),
                //
                "Browser.selectionBackground", new ColorUIResource(56, 117, 215),
                "Browser.selectionForeground", new ColorUIResource(255, 255, 255),
                "Browser.inactiveSelectionBackground", new ColorUIResource(208, 208, 208),
                "Browser.inactiveSelectionForeground", new ColorUIResource(0, 0, 0),
                "Browser.sizeHandleIcon", makeIcon(getClass(), lionDir + "Browser.sizeHandleIcon.png"),
                //
                "ColorChooser.unifiedTitleBar", Boolean.TRUE,
                //
                "ComboBox.selectionBackground", new GradientColor(new ColorUIResource(0x4455f0), new ColorUIResource(0x5a7eeb), new ColorUIResource(0x2560f3)),
                "ComboBox.selectionForeground", menuSelectionForeground,
                "ComboBox.popupBorder",//
                new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.leopard.QuaquaLeopardComboBoxPopupBorder"),
                "ComboBox.maximumRowCount", 10,
                "ComboBox.button.insets", new InsetsUIResource(0, 0, -2, 0),
                "ComboBox.editorBorder", textFieldBorder,
                //
                "FileChooser.autovalidate", Boolean.TRUE,
                "FileChooser.enforceQuaquaTreeUI", Boolean.TRUE,
                //
                "FileChooser.previewLabelForeground", new ColorUIResource(0x6d6d6d),
                "FileChooser.previewValueForeground", new ColorUIResource(0x000000),
                "FileChooser.previewLabelInsets", new InsetsUIResource(1, 0, 0, 4),
                "FileChooser.previewLabelDelimiter", "",
                "FileChooser.cellTipOrigin", new Point(18, 1),
                "FileChooser.splitPaneDividerSize", 1,
                "FileChooser.splitPaneBackground", new ColorUIResource(0xa5a5a5),
                "FileChooser.browserCellFocusBorder", browserCellBorder,
                "FileChooser.browserCellFocusBorderGrayed", browserCellBorder,
                "FileChooser.browserCellBorder", browserCellBorder,
                "FileChooser.browserUseUnselectedExpandIconForLabeledFile", Boolean.FALSE,
                "FileChooser.browserCellColorLabelInsets", new InsetsUIResource(0, 1, -1, 1),
                "FileChooser.browserCellSelectedColorLabelInsets", new InsetsUIResource(1, 0, 0, 0),
                "FileChooser.browserCellTextIconGap", 6,
                "FileChooser.browserCellTextArrowIconGap", 5,
                "FileChooser.browserUseUnselectedExpandIconForLabeledFile", Boolean.TRUE,
                "FileChooser.sheetErrorFont", new FontUIResource(baseSystemFontName, Font.PLAIN, 10),
                "FileChooser.sideBarIcon.Applications", makeNativeSidebarIcon(sideBarIconsPrefix + "ApplicationsFolder.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.Desktop", makeNativeSidebarIcon(sideBarIconsPrefix + "DesktopFolder.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.Documents", makeNativeSidebarIcon(sideBarIconsPrefix + "DocumentsFolder.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.Downloads", makeNativeSidebarIcon(sideBarIconsPrefix + "DownloadsFolder.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.Dropbox", makeNativeSidebarIcon(sideBarIconsPrefix + "DropBoxFolder.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.Home", makeNativeSidebarIcon(sideBarIconsPrefix + "HomeFolder.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.Library", makeNativeSidebarIcon(sideBarIconsPrefix + "GenericFolder.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.Movies", makeNativeSidebarIcon(sideBarIconsPrefix + "MoviesFolder.icns", 18, sideBarIconColor, sideBarIconSelectionColor), // Note: no "s" in "Movie"
                "FileChooser.sideBarIcon.Music", makeNativeSidebarIcon(sideBarIconsPrefix + "MusicFolder.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.Network", makeNativeSidebarIcon(sideBarIconsPrefix + "Network.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.Pictures", makeNativeSidebarIcon(sideBarIconsPrefix + "PicturesFolder.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.Public", makeNativeSidebarIcon(sideBarIconsPrefix + "DropBoxFolder.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.Shared", makeNativeSidebarIcon(sideBarIconsPrefix + "GenericFolder.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.Sites", makeNativeSidebarIcon(sideBarIconsPrefix + "GenericFolder.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.Utilities", makeNativeSidebarIcon(sideBarIconsPrefix + "UtilitiesFolder.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.GenericFolder", makeNativeSidebarIcon(sideBarIconsPrefix + "GenericFolder.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.GenericFile", makeNativeSidebarIcon(sideBarIconsPrefix + "GenericFile.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.GenericVolume", makeNativeSidebarIcon(sideBarIconsPrefix + "InternalDisk.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.SmartFolder", makeNativeSidebarIcon(sideBarIconsPrefix + "SmartFolder.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.TimeMachineVolume", makeNativeSidebarIcon(sideBarIconsPrefix + "TimeMachine.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                //
                "FileChooser.sideBarIcon.Imac", makeNativeSidebarIcon(sideBarIconsPrefix + "iMac.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.MacPro", makeNativeSidebarIcon(sideBarIconsPrefix + "MacPro.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.MacMini", makeNativeSidebarIcon(sideBarIconsPrefix + "MacMini.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                "FileChooser.sideBarIcon.Laptop", makeNativeSidebarIcon(sideBarIconsPrefix + "Laptop.icns", 18, sideBarIconColor, sideBarIconSelectionColor),
                //
                "FileChooser.sideBarRowHeight", 24,
                //
                "FileView.computerIcon", makeIcon(getClass(), snowLeopardDir + "FileView.computerIcon.png"),
                "FileView.fileIcon", makeNativeIcon(iconPrefix + "GenericDocumentIcon.icns", 16),
                "FileView.directoryIcon", makeNativeIcon(iconPrefix + "GenericFolderIcon.icns", 16),
                "FileView.hardDriveIcon", makeIcon(getClass(), snowLeopardDir + "FileView.hardDriveIcon.png"),
                "FileView.floppyDriveIcon", makeIcon(getClass(), snowLeopardDir + "FileView.floppyDriveIcon.png"),
                "FileView.aliasBadgeIcon", makeNativeIcon(iconPrefix + "AliasBadgeIcon.icns", 16),
                "FileView.networkIcon", makeNativeIcon(iconPrefix + "GenericNetworkIcon.icns", 16),
                //
                "FileView.macbookIcon", makeNativeIcon(iconPrefix + "com.apple.macbook-white.icns", 16),
                "FileView.macbookAirIcon", makeNativeIcon(iconPrefix + "com.apple.macbookair-13-unibody.icns", 16),
                "FileView.macbookProIcon", makeNativeIcon(iconPrefix + "com.apple.macbookpro-13-unibody.icns", 16),
                "FileView.macproIcon", makeNativeIcon(iconPrefix + "com.apple.macpro.icns", 16),
                "FileView.macminiIcon", makeNativeIcon(iconPrefix + "com.apple.macmini-unibody-no-optical.icns", 16),
                "FileView.imacIcon", makeNativeIcon(iconPrefix + "com.apple.imac-unibody-21.icns", 16),
                //
                "FormattedTextField.border", textFieldBorder,
                "FormattedTextField.borderInsets", new InsetsUIResource(3, 6, 4, 6),
                "FormattedTextField.smallBorderInsets", new InsetsUIResource(3, 5, 4, 5),
                "FormattedTextField.miniBorderInsets", new InsetsUIResource(3, 5, 4, 5),
                //
                "Frame.titlePaneBorders", makeImageBevelBorders(snowLeopardDir + "Frame.titlePaneBorders.png", new Insets(0, 0, 22, 0), 2, true),
                "Frame.titlePaneBorders.small", makeImageBevelBorders(snowLeopardDir + "Frame.titlePaneBorders.small.png", new Insets(0, 0, 16, 0), 2, true),
                "Frame.titlePaneBorders.mini", makeImageBevelBorders(snowLeopardDir + "Frame.titlePaneBorders.mini.png", new Insets(0, 0, 12, 0), 2, true),
                "Frame.titlePaneBorders.vertical", makeImageBevelBorders(snowLeopardDir + "Frame.titlePaneBorders.vertical.png", new Insets(0, 0, 0, 22), 2, false),
                "Frame.titlePaneBorders.vertical.small", makeImageBevelBorders(snowLeopardDir + "Frame.titlePaneBorders.vertical.small.png", new Insets(0, 0, 0, 16), 2, false),
                "Frame.titlePaneBorders.vertical.mini", makeImageBevelBorders(snowLeopardDir + "Frame.titlePaneBorders.vertical.mini.png", new Insets(0, 0, 0, 12), 2, false),
                "Frame.titlePaneEmbossForeground", new AlphaColorUIResource(0x7effffff),
                //
                "InternalFrame.titlePaneBorders", makeImageBevelBorders(snowLeopardDir + "Frame.titlePaneBorders.png", new Insets(0, 0, 22, 0), 2, true),
                "InternalFrame.titlePaneBorders.small", makeImageBevelBorders(snowLeopardDir + "Frame.titlePaneBorders.small.png", new Insets(0, 0, 16, 0), 2, true),
                "InternalFrame.titlePaneBorders.mini", makeImageBevelBorders(snowLeopardDir + "Frame.titlePaneBorders.mini.png", new Insets(0, 0, 10, 0), 2, true),
                "InternalFrame.closeIcon", makeFrameButtonStateIcon(snowLeopardDir + "Frame.closeIcons.png", 12),
                "InternalFrame.maximizeIcon", makeFrameButtonStateIcon(snowLeopardDir + "Frame.maximizeIcons.png", 12),
                "InternalFrame.iconifyIcon", makeFrameButtonStateIcon(snowLeopardDir + "Frame.iconifyIcons.png", 12),
                "InternalFrame.closeIcon.small", makeFrameButtonStateIcon(snowLeopardDir + "Frame.closeIcons.small.png", 12),
                "InternalFrame.maximizeIcon.small", makeFrameButtonStateIcon(snowLeopardDir + "Frame.maximizeIcons.small.png", 12),
                "InternalFrame.iconifyIcon.small", makeFrameButtonStateIcon(snowLeopardDir + "Frame.iconifyIcons.small.png", 12),
                "InternalFrame.resizeIcon", makeIcon(getClass(), leopardDir + "Frame.resize.png"),
                //
                "Label.embossForeground", new AlphaColorUIResource(0x7effffff),
                "Label.shadowForeground", new AlphaColorUIResource(0x7e000000),
                //
                "List.alternateBackground.0", listAlternateBackground,
                "List.cellNoFocusBorder", new BorderUIResource.EmptyBorderUIResource(1, 3, 1, 3),
                "List.focusSelectedCellHighlightBorder", new BorderUIResource.EmptyBorderUIResource(1, 3, 1, 3),
                "List.focusCellHighlightBorder", new BorderUIResource.EmptyBorderUIResource(1, 3, 1, 3),
                //
                "Menu.background", menuBackground,
                "MenuItem.background", menuBackground,
                "CheckBoxMenuItem.background", menuBackground,
                "RadioButtonMenuItem.background", menuBackground,
                //
                "OptionPane.errorIconResource", leopardDir + "OptionPane.errorIcon.png",
                "OptionPane.warningIconResource", leopardDir + "OptionPane.warningIcon.png",
                //
                "PopupMenu.background", menuBackground,
                //
                "Panel.background", panelBackground,
                //
                "PasswordField.border", textFieldBorder,
                "PasswordField.borderInsets", new InsetsUIResource(3, 6, 4, 6),
                "PasswordField.smallBorderInsets", new InsetsUIResource(3, 5, 4, 5),
                "PasswordField.miniBorderInsets", new InsetsUIResource(3, 5, 4, 5),
                //
                "PopupMenu.border", //
                new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.leopard.QuaquaLeopardMenuBorder"),
                //
                "RootPane.background", panelBackground,
                //
                "ScrollBar.placeButtonsTogether", new UIDefaults.ProxyLazyValue(
                "ch.randelshofer.quaqua.osx.OSXPreferences", "isStringEqualTo",
                new Object[]{OSXPreferences.GLOBAL_PREFERENCES, "AppleScrollBarVariant", "DoubleMax", "DoubleMax"}),
                "ScrollBar.supportsAbsolutePositioning", new UIDefaults.ProxyLazyValue(
                "ch.randelshofer.quaqua.osx.OSXPreferences", "isStringEqualTo",
                new Object[]{OSXPreferences.GLOBAL_PREFERENCES, "AppleScrollerPagingBehavior", "false", "true"}),
                "ScrollBar.minimumThumbSize", new DimensionUIResource(18, 18),
                "ScrollBar.minimumThumbSize.small", new DimensionUIResource(14, 14),
                "ScrollBar.maximumThumbSize", new DimensionUIResource(Integer.MAX_VALUE, Integer.MAX_VALUE),
                "ScrollBar.thumb.hMiddle", null,
                "ScrollBar.thumb.hFirst", null,
                "ScrollBar.thumb.hLast", null,
                "ScrollBar.track.h", scrollBarTrack,
                "ScrollBar.thumb.h", scrollBarThumb,
                "ScrollBar.thumb.h.small", scrollBarThumb,
                "ScrollBar.thumb.hInactive", scrollBarThumb,
                "ScrollBar.buttons.hSep", null,
                "ScrollBar.buttons.hTog", null,
                "ScrollBar.thumb.vMiddle", null,
                "ScrollBar.thumb.vFirst", null,
                "ScrollBar.thumb.vLast", null,
                "ScrollBar.track.v", scrollBarTrack,
                "ScrollBar.thumb.v", scrollBarThumb,
                "ScrollBar.thumb.v.small", scrollBarThumb,
                "ScrollBar.thumb.vInactive", scrollBarThumb,
                "ScrollBar.buttons.vSep", null,
                "ScrollBar.buttons.vTog", null,
                "ScrollBar.thumb.hMiddle.small", null,
                "ScrollBar.thumb.hFirst.small", null,
                "ScrollBar.thumb.hLast.small", null,
                "ScrollBar.track.h.small", scrollBarTrack,
                "ScrollBar.thumb.hInactive.small", scrollBarThumb,
                "ScrollBar.buttons.hSep.small", null,
                "ScrollBar.buttons.hTog.small", null,
                "ScrollBar.thumb.vMiddle.small", null,
                "ScrollBar.thumb.vLast.small", null,
                "ScrollBar.track.v.small", scrollBarTrack,
                "ScrollBar.thumb.vInactive.small", scrollBarThumb,
                "ScrollBar.buttons.vSep.small", null,
                "ScrollBar.buttons.vTog.small", null,
                "ScrollBar.buttonHeight", 0,
                "ScrollBar.buttonHeight.small", 0,
                "ScrollBar.trackInsets.tog", new Insets(0, 0, 0, 0),
                "ScrollBar.trackInsets.tog.small", new Insets(0, 0, 0, 0),
                "ScrollBar.trackInsets.tog.mini", new Insets(0, 0, 0, 0),
                "ScrollBar.preferredSize", new Dimension(15, 15),
                "ScrollBar.preferredSize.small", new Dimension(13, 13),
                "ScrollBar.preferredSize.mini", new Dimension(9, 9),
                "ScrollBar.focusable", Boolean.FALSE,
                "ScrollBar.thumbInsets", new Insets(2, 4, 2, 3),
                "ScrollBar.thumbInsets.small", new Insets(2, 3, 2, 3),
                "ScrollBar.thumbInsets.mini", new Insets(2, 3, 2, 2),
                //
                "ScrollPane.border", scrollPaneBorder,
                "ScrollPane.border.imageInsets", new InsetsUIResource(-3, 0, 3, 0),
                //
                "Separator.highlight", new ColorUIResource(0xe3e3e3),
                "Separator.foreground", new ColorUIResource(0xd4d4d4),
                "Separator.shadow", new AlphaColorUIResource(0x0),
                "Separator.border", new VisualMarginBorder(),
                //
                // FIXME We use the TabbedPaneUI in apple.laf.AquaLookAndFeel for now.
                /*
                "TabbedPane.disabledForeground", disabledForeground,
                "TabbedPane.selectedTabPadInsets", new InsetsUIResource(0, 0, 0, 0),
                "TabbedPane.tabRunOverlay", 0,
                "TabbedPane.tabInsets", new InsetsUIResource(1, 7, 2, 7),
                "TabbedPane.smallTabInsets", new InsetsUIResource(1, 5, 2, 5),
                "TabbedPane.outerTabInsets", new InsetsUIResource(1, 11, 2, 11),
                "TabbedPane.smallOuterTabInsets", new InsetsUIResource(1, 9, 2, 9),
                "TabbedPane.contentBorderInsets", new InsetsUIResource(0, 2, 2, 2),
                "TabbedPane.tabAreaInsets", new InsetsUIResource(-2, 16, 1, 16),
                "TabbedPane.contentBorder", makeNativeImageBevelBorder(
                OSXAquaPainter.Widget.frameGroupBox, new Insets(0, 0, 0, 0), new Insets(7, 7, 7, 7), new Insets(7, 7, 7, 7), true),
                "TabbedPane.emptyContentBorder", makeNativeImageBevelBorder(
                OSXAquaPainter.Widget.frameGroupBox, new Insets(0, 0, 0, 0), new Insets(7, 7, 7, 7), new Insets(7, 7, 7, 7), true),
                "TabbedPane.tabBorders", makeImageBevelBorders(commonDir + "Toggle.borders.png",
                new Insets(8, 10, 15, 10), 10, true),
                "TabbedPane.tabFocusRing", makeImageBevelBorder(commonDir + "Toggle.focusRing.png",
                new Insets(8, 10, 15, 10), true),
                "TabbedPane.eastTabBorders", makeImageBevelBorders(commonDir + "Toggle.east.borders.png",
                new Insets(8, 1, 15, 10), 10, true),
                "TabbedPane.eastTabFocusRing", makeImageBevelBorder(commonDir + "Toggle.east.focusRing.png",
                new Insets(8, 4, 15, 10), true),
                "TabbedPane.centerTabBorders", makeImageBevelBorders(commonDir + "Toggle.center.borders.png",
                new Insets(8, 0, 15, 1), 10, true),
                "TabbedPane.centerTabFocusRing", makeImageBevelBorder(commonDir + "Toggle.center.focusRing.png",
                new Insets(8, 4, 15, 4), false),
                "TabbedPane.westTabBorders", makeImageBevelBorders(commonDir + "Toggle.west.borders.png",
                new Insets(8, 10, 15, 1), 10, true),
                "TabbedPane.westTabFocusRing", makeImageBevelBorder(commonDir + "Toggle.west.focusRing.png",
                new Insets(8, 10, 15, 4), true),
                */
                //
                "Table.ascendingSortIcon", makeIcon(getClass(), snowLeopardDir + "Table.ascendingSortIcon.png"),
                "Table.descendingSortIcon", makeIcon(getClass(), snowLeopardDir + "Table.descendingSortIcon.png"),
                "Table.scrollPaneBorder", scrollPaneBorder,
                "Table.alternateBackground.0", listAlternateBackground,
                //
                "TextField.border", textFieldBorder,
                "TextField.borderInsets", new InsetsUIResource(3, 6, 4, 6),
                "TextField.smallBorderInsets", new InsetsUIResource(3, 5, 4, 5),
                "TextField.miniBorderInsets", new InsetsUIResource(3, 5, 4, 5),
                //
                "TextField.searchBorderInsets", new InsetsUIResource(3, 10, 4, 10),
                //
                //"TableHeader.cellBorder", new UIDefaults.ProxyLazyValue(
                //"ch.randelshofer.quaqua.QuaquaTableHeaderBorder$UIResource",
                //new Object[]{snowLeopardDir + "TableHeader.borders.png", new Insets(6, 1, 9, 1)}),
                //
                "TitledBorder.border", new GroupBox(),
                "TitledBorder.titleColor", new ColorUIResource(0x303030),
                //
                "ToolBar.background", panelBackground,
                "ToolBar.borderBright", new AlphaColorUIResource(0x999999),
                "ToolBar.borderDark", new ColorUIResource(0x8c8c8c),
                "ToolBar.borderDivider", new ColorUIResource(0x9f9f9f),
                "ToolBar.borderDividerInactive", new ColorUIResource(0x9f9f9f),
                "ToolBar.title.borderDivider", new ColorUIResource(0x515151),
                "ToolBar.title.borderDividerInactive", new ColorUIResource(0x999999),
                "ToolBar.title.background", toolBarBackground,
                "ToolBar.bottom.borderDivider", new ColorUIResource(0x515151),
                "ToolBar.bottom.borderDividerInactive", new ColorUIResource(0x999999),
                "ToolBar.bottom.gradient", new Color[]{new Color(0xcbcbcb), new Color(0xa7a7a7)},
                "ToolBar.bottom.gradientInactive", new Color[]{new Color(0xeaeaea), new Color(0xd8d8d8)},
                "ToolBar.gradient.borderDivider", new ColorUIResource(0xd4d4d4),
                "ToolBar.gradient.borderDividerInactive", new ColorUIResource(0xd4d4d4),
                "ToolBar.textured.dragMovesWindow", Boolean.TRUE,
                //
                "ToolBarSeparator.foreground", new ColorUIResource(0x808080),
                //
                //"Tree.collapsedIcon", makeIcon(getClass(), leopardDir + "Tree.collapsedIcon.png"),
                //"Tree.expandedIcon", makeIcon(getClass(), leopardDir + "Tree.expandedIcon.png"),
                //"Tree.leafIcon", makeIcon(getClass(), leopardDir + "Tree.leafIcon.png"),
                "Tree.alternateBackground.0", listAlternateBackground,
                "Tree.clickThrough", Boolean.FALSE,
                "Tree.openIcon", makeIcon(getClass(), leopardDir + "Tree.openIcon.png"),
                "Tree.closedIcon", makeIcon(getClass(), leopardDir + "Tree.closedIcon.png"),
                "Tree.sideBar.background", new ActivatableColorUIResource(0xebebeb, 0xf6f6f6),
                "Tree.sideBar.selectionBorder",
                new QuaquaYosemiteSideBarSelectionBorder(
                        new ActivatableColorUIResource(sidebarListSelectionBackground, new Color(0xcccccc), new Color(0xcccccc),
                                new Color(0xcccccc))),
                "Tree.leftChildIndent", 8, // 7
                "Tree.rightChildIndent", 12, // 13
                "Tree.icons", makeIcons(lionDir + "Tree.icons.png", 15, true),
                "Tree.sideBar.icons", makeIcons(lionDir + "Tree.sideBar.icons.png", 15, true),
                "Tree.sideBarCategory.foreground", new ActivatableColorUIResource(0x383838, 0x777777),
                "Tree.sideBarCategory.selectionForeground", new ActivatableColorUIResource(0xffffff, 0x777777, 0x1f1f1f, 0x535353),
                "Tree.sideBarCategory.font", new FontUIResource(sidebarFontName, Font.PLAIN, 11),
                "Tree.sideBarCategory.selectionFont", new FontUIResource(sidebarFontName, Font.PLAIN, 11),
                "Tree.sideBar.foreground", new ActivatableColorUIResource(0x383838, 0x777777),
                "Tree.sideBar.selectionForeground", new ActivatableColorUIResource(0xffffff, 0x777777, 0x1f1f1f, 0x535353),
                "Tree.sideBar.font", new FontUIResource(sidebarFontName, Font.PLAIN, 11),
                "Tree.sideBar.selectionFont", new FontUIResource(sidebarFontName, Font.PLAIN, 11),
                "Tree.rendererMargins", new InsetsUIResource(0, 0, 0, 0),
                "Tree.sideBarCategory.style", "plain",
                "Tree.sideBarCategory.selectionStyle", "plain",
                "Tree.sideBar.style", "plain",
                "Tree.sideBar.selectionStyle", "plain",
        };


        putDefaults(table, uiDefaults);

        // FIXME Implement a screen menu bar by myself. We lose too many features here.
        if (isUseScreenMenuBar()) {
            uiDefaults = new Object[]{
                    "CheckBoxMenuItem.checkIcon", makeButtonStateIcon(snowLeopardDir + "CheckBoxMenuItem.icons.png", 6, new Rectangle(1, 0, 12, 12)),
                    "CheckBoxMenuItem.border", new BorderUIResource.EmptyBorderUIResource(0, 5, 2, 0),
                    "CheckBoxMenuItem.margin", new InsetsUIResource(0, 8, 0, 8),
                    "Menu.checkIcon", makeIcon(getClass(), commonDir + "MenuItem.checkIcon.png", new Point(1, 0)),
                    "Menu.arrowIcon", makeButtonStateIcon(commonDir + "MenuItem.arrowIcons.png", 2, new Rectangle(-6, 1, 6, 12)),
                    "Menu.border", new BorderUIResource.EmptyBorderUIResource(0, 5, 2, 4),
                    "Menu.margin", new InsetsUIResource(0, 8, 0, 8),
                    "Menu.menuPopupOffsetX", 0,
                    "Menu.menuPopupOffsetY", 1,
                    "Menu.submenuPopupOffsetX", 0,
                    "Menu.submenuPopupOffsetY", -4,
                    "MenuItem.checkIcon", makeIcon(getClass(), commonDir + "MenuItem.checkIcon.png", new Point(1, 0)),
                    "MenuItem.border", new BorderUIResource.EmptyBorderUIResource(0, 5, 2, 0),
                    "RadioButtonMenuItem.checkIcon", makeButtonStateIcon(snowLeopardDir + "RadioButtonMenuItem.icons.png", 6, new Rectangle(0, 0, 12, 12)),
                    "RadioButtonMenuItem.border", new BorderUIResource.EmptyBorderUIResource(0, 5, 2, 0),
                    "RadioButtonMenuItem.margin", new InsetsUIResource(0, 8, 0, 8), //
            };
        } else {
            Border menuBorder = new BorderUIResource.EmptyBorderUIResource(1, 1, 1, 1);
            GradientColor.UIResource menuSelectionBackground = new GradientColor.UIResource(0x3875d7, 0x5170f6, 0x1a43f3);
            uiDefaults = new Object[]{
                    "CheckBoxMenuItem.checkIcon", makeButtonStateIcon(commonDir + "CheckBoxMenuItem.icons.png", 6, new Point(0, 1)),
                    "CheckBoxMenuItem.border", menuBorder,
                    "CheckBoxMenuItem.selectionBackground", menuSelectionBackground,
                    "Menu.checkIcon", makeIcon(getClass(), commonDir + "MenuItem.checkIcon.png"),
                    "Menu.arrowIcon", makeButtonStateIcon(commonDir + "MenuItem.arrowIcons.png", 2, new Point(0, 1)),
                    "Menu.margin", new InsetsUIResource(0, 5, 0, 8),
                    "Menu.menuPopupOffsetX", 0,
                    "Menu.menuPopupOffsetY", 0,
                    "Menu.submenuPopupOffsetX", 0,
                    "Menu.submenuPopupOffsetY", -4,
                    "Menu.useMenuBarBackgroundForTopLevel", Boolean.TRUE,
                    "Menu.border", menuBorder,
                    "Menu.selectionBackground", menuSelectionBackground,
                    //"MenuBar.background", new TextureColorUIResource(0xf4f4f4, getClass().getResource(pantherDir+"MenuBar.texture.png")),
                    //"MenuBar.border", new BorderUIResource.MatteBorderUIResource(0,0,1,0,new Color(128,128,128)),
                    "MenuBar.border", makeImageBevelBackgroundBorder(snowLeopardDir + "MenuBar.border.png", new Insets(18, 0, 2, 0), new Insets(0, 0, 0, 0), true),
                    "MenuBar.selectedBorder", makeImageBevelBackgroundBorder(snowLeopardDir + "MenuBar.selectedBorder.png", new Insets(18, 0, 2, 0), new Insets(0, 0, 0, 0), true),
                    "MenuBar.margin", new InsetsUIResource(1, 8, 2, 8),
                    "MenuBar.shadow", null,
                    "MenuItem.acceleratorSelectionForeground", menuSelectionForeground,
                    "MenuItem.checkIcon", makeIcon(getClass(), commonDir + "MenuItem.checkIcon.png"),
                    "MenuItem.border", menuBorder,
                    "MenuItem.selectionBackground", menuSelectionBackground,
                    "RadioButtonMenuItem.checkIcon", makeButtonStateIcon(commonDir + "RadioButtonMenuItem.icons.png", 6),
                    "RadioButtonMenuItem.border", menuBorder,
                    "RadioButtonMenuItem.selectionBackground", menuSelectionBackground,
                    /* */
            };
        }
        putDefaults(table, uiDefaults);

    }

}
