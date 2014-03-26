/*
 * @(#)Quaqua16MountainLionLookAndFeel.java
 *
 * Copyright (c) 2011-2013 Werner Randelshofer, Switzerland.
 * All rights reserved.
 *
 * You may not use, copy or modify this file, except in compliance with the
 * license agreement you entered into with Werner Randelshofer.
 * For details see accompanying license terms.
 */
package ch.randelshofer.quaqua.mountainlion;

import javax.swing.UIDefaults;
import javax.swing.plaf.ColorUIResource;

import ch.randelshofer.quaqua.QuaquaManager;
import ch.randelshofer.quaqua.color.InactivatableColorUIResource;
import ch.randelshofer.quaqua.lion.Quaqua16LionLookAndFeel;

/**
 * {@code Quaqua16MountainLionLookAndFeel}.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class Quaqua16MountainLionLookAndFeel extends Quaqua16LionLookAndFeel {

	public Quaqua16MountainLionLookAndFeel() {
		super();
	}

	protected Quaqua16MountainLionLookAndFeel(String className) {
		super(className);
	}

    @Override
    public String getDescription() {
        return "The Quaqua Mountain Lion Look and Feel "
                + QuaquaManager.getVersion()
                + " for J2SE 6 and above";
    }

    /**
     * Return a short string that identifies this look and feel, e.g.
     * "CDE/Motif".  This string should be appropriate for a menu item.
     * Distinct look and feels should have different names, e.g.
     * a subclass of MotifLookAndFeel that changes the way a few components
     * are rendered should be called "CDE/Motif My Way"; something
     * that would be useful to a user trying to select a L&amp;F from a list
     * of names.
     */
    @Override
    public String getName() {
        return "Quaqua Mountain Lion";
    }

	@Override
	protected void initDesignDefaults(UIDefaults table) {
		super.initDesignDefaults(table);

        Object toolBarTitleBackground;

        final String javaVersion = QuaquaManager.getProperty("java.version", "");
        if (javaVersion.startsWith("1.5") || javaVersion.startsWith("1.6")) {
            toolBarTitleBackground = table.get("control");

        BorderUIResource.CompoundBorderUIResource browserCellBorder = new BorderUIResource.CompoundBorderUIResource(
                new BorderUIResource.MatteBorderUIResource(0, 0, 1, 0, new ColorUIResource(0xffffff)),
                new BorderUIResource.EmptyBorderUIResource(0, 4, 1, 0));
        /*
        Object scrollPaneBorder = new UIDefaults.ProxyLazyValue(
                "ch.randelshofer.quaqua.QuaquaScrollPaneBorder$UIResource",
                new Object[]{lionDir + "ScrollPane.borders.png", lionDir + "TextField.borders.png"});
         * 
         */
        Object scrollPaneBorder = new UIDefaults.ProxyLazyValue(
                "ch.randelshofer.quaqua.QuaquaNativeScrollPaneBorder"
                ); 
        
        /*
        String sideBarIconsStart = "/System/Library/CoreServices/CoreTypes.bundle/Contents/Resources/Toolbar";
        String sideBarIconsEnd = "FolderIcon.icns";
         */
        String sideBarIconsPrefix = "/System/Library/CoreServices/CoreTypes.bundle/Contents/Resources/Sidebar";
        ColorUIResource sideBarIconColor = new ColorUIResource(125,134,147);
        ColorUIResource sideBarIconSelectionColor = new ColorUIResource(0xffffff);
        
        //Object nativeScrollBar=new UIDefaults.ProxyLazyValue(
        //        "ch.randelshofer.quaqua.lion.QuaquaLionNativeScrollBarBorder",            
        //        new Object[]{OSXAquaPainter.Widget.scrollBar});
        Object scrollBarThumb=new UIDefaults.ProxyLazyValue(
                "ch.randelshofer.quaqua.lion.QuaquaLionScrollBarThumbBorder");
        Object scrollBarTrack=new UIDefaults.ProxyLazyValue(
                "ch.randelshofer.quaqua.lion.QuaquaLionScrollBarTrackBorder");
        
        Object[] uiDefaults = {
        	"control", toolBarTitleBackground,
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
            // CheckBox icon needs to be shifted to the left
            "CheckBox.icon", makeNativeButtonStateIcon(OSXAquaPainter.Widget.buttonCheckBox, -1,0,16,20,true),
            "CheckBox.smallIcon", makeNativeButtonStateIcon(OSXAquaPainter.Widget.buttonCheckBox, -1,0,14,16,true),
            "CheckBox.miniIcon", makeNativeButtonStateIcon(OSXAquaPainter.Widget.buttonCheckBox, 0,-1,10,15,true),
            //
            "ComboBox.selectionBackground", new GradientColor(new ColorUIResource(0x3875d7), new ColorUIResource(0x5170f6), new ColorUIResource(0x1a43f3)),
            "ComboBox.selectionForeground", menuSelectionForeground,
            "ComboBox.popupBorder",//
            new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.leopard.QuaquaLeopardComboBoxPopupBorder"),
            "ComboBox.maximumRowCount",10,
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
            /*
            "FileChooser.sideBarIcon.Applications", makeNativeIcon(sideBarIconsStart + "Apps" + sideBarIconsEnd, 16),
            "FileChooser.sideBarIcon.Desktop", makeNativeIcon(sideBarIconsStart + "Desktop" + sideBarIconsEnd, 16),
            "FileChooser.sideBarIcon.Documents", makeNativeIcon(sideBarIconsStart + "Documents" + sideBarIconsEnd, 16),
            "FileChooser.sideBarIcon.Downloads", makeNativeIcon(sideBarIconsStart + "Downloads" + sideBarIconsEnd, 16),
            "FileChooser.sideBarIcon.Library", makeNativeIcon(sideBarIconsStart + "Library" + sideBarIconsEnd, 16),
            "FileChooser.sideBarIcon.Movies", makeNativeIcon(sideBarIconsStart + "Movie" + sideBarIconsEnd, 16), // Note: no "s" in "Movie"
            "FileChooser.sideBarIcon.Music", makeNativeIcon(sideBarIconsStart + "Music" + sideBarIconsEnd, 16),
            "FileChooser.sideBarIcon.Pictures", makeNativeIcon(sideBarIconsStart + "Pictures" + sideBarIconsEnd, 16),
            "FileChooser.sideBarIcon.Public", makeNativeIcon(sideBarIconsStart + "Public" + sideBarIconsEnd, 16),
            "FileChooser.sideBarIcon.Sites", makeNativeIcon(sideBarIconsStart + "Sites" + sideBarIconsEnd, 16),
            "FileChooser.sideBarIcon.Utilities", makeNativeIcon(sideBarIconsStart + "Utilities" + sideBarIconsEnd, 16),
            */
            "FileChooser.sideBarIcon.Applications", makeNativeSidebarIcon(sideBarIconsPrefix + "ApplicationsFolder.icns", 16, sideBarIconColor, sideBarIconSelectionColor),
            "FileChooser.sideBarIcon.Desktop", makeNativeSidebarIcon(sideBarIconsPrefix + "DesktopFolder.icns", 16, sideBarIconColor, sideBarIconSelectionColor),
            "FileChooser.sideBarIcon.Documents", makeNativeSidebarIcon(sideBarIconsPrefix + "DocumentsFolder.icns", 16, sideBarIconColor, sideBarIconSelectionColor),
            "FileChooser.sideBarIcon.Downloads", makeNativeSidebarIcon(sideBarIconsPrefix + "DownloadsFolder.icns", 16, sideBarIconColor, sideBarIconSelectionColor),
            "FileChooser.sideBarIcon.Home", makeNativeSidebarIcon(sideBarIconsPrefix + "HomeFolder.icns", 16, sideBarIconColor, sideBarIconSelectionColor),
            "FileChooser.sideBarIcon.Library", makeNativeSidebarIcon(sideBarIconsPrefix + "GenericFolder.icns", 16, sideBarIconColor, sideBarIconSelectionColor),
            "FileChooser.sideBarIcon.Movies", makeNativeSidebarIcon(sideBarIconsPrefix + "MoviesFolder.icns", 16, sideBarIconColor, sideBarIconSelectionColor), // Note: no "s" in "Movie"
            "FileChooser.sideBarIcon.Music", makeNativeSidebarIcon(sideBarIconsPrefix + "MusicFolder.icns", 16, sideBarIconColor, sideBarIconSelectionColor),
            "FileChooser.sideBarIcon.Pictures", makeNativeSidebarIcon(sideBarIconsPrefix + "PicturesFolder.icns", 16, sideBarIconColor, sideBarIconSelectionColor),
            "FileChooser.sideBarIcon.Public", makeNativeSidebarIcon(sideBarIconsPrefix + "DropBoxFolder.icns", 16, sideBarIconColor, sideBarIconSelectionColor),
            "FileChooser.sideBarIcon.Shared", makeNativeSidebarIcon(sideBarIconsPrefix + "GenericFolder.icns", 16, sideBarIconColor, sideBarIconSelectionColor),
            "FileChooser.sideBarIcon.Sites", makeNativeSidebarIcon(sideBarIconsPrefix + "GenericFolder.icns", 16, sideBarIconColor, sideBarIconSelectionColor),
            "FileChooser.sideBarIcon.Utilities", makeNativeSidebarIcon(sideBarIconsPrefix + "UtilitiesFolder.icns", 16, sideBarIconColor, sideBarIconSelectionColor),
            "FileChooser.sideBarIcon.GenericFolder", makeNativeSidebarIcon(sideBarIconsPrefix + "GenericFolder.icns", 16, sideBarIconColor, sideBarIconSelectionColor),
            "FileChooser.sideBarIcon.GenericFile", makeNativeSidebarIcon(sideBarIconsPrefix + "GenericFile.icns", 16, sideBarIconColor, sideBarIconSelectionColor),
            "FileChooser.sideBarIcon.GenericVolume", makeNativeSidebarIcon(sideBarIconsPrefix + "InternalDisk.icns", 16, sideBarIconColor, sideBarIconSelectionColor),
             
            //
            "FileView.computerIcon", makeIcon(getClass(), snowLeopardDir + "FileView.computerIcon.png"),
            "FileView.fileIcon", makeIcon(getClass(), snowLeopardDir + "FileView.fileIcon.png"),
            "FileView.directoryIcon", makeIcon(getClass(), snowLeopardDir + "FileView.directoryIcon.png"),
            "FileView.hardDriveIcon", makeIcon(getClass(), snowLeopardDir + "FileView.hardDriveIcon.png"),
            "FileView.floppyDriveIcon", makeIcon(getClass(), snowLeopardDir + "FileView.floppyDriveIcon.png"),
            //
//            "FormattedTextField.border", textFieldBorder,
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
            "List.cellNoFocusBorder", new BorderUIResource.EmptyBorderUIResource(1,3,1,3),
            "List.focusSelectedCellHighlightBorder", new BorderUIResource.EmptyBorderUIResource(1,3,1,3),
            "List.focusCellHighlightBorder", new BorderUIResource.EmptyBorderUIResource(1,3,1,3),
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
//            "PasswordField.border", textFieldBorder,
            //
            "PopupMenu.border", //
            new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.leopard.QuaquaLeopardMenuBorder"),
            //
            "RootPane.background", panelBackground,
            //
            "ScrollBar.placeButtonsTogether",new UIDefaults.ProxyLazyValue(
                "ch.randelshofer.quaqua.osx.OSXPreferences","isStringEqualTo",            
                new Object[]{OSXPreferences.GLOBAL_PREFERENCES, "AppleScrollBarVariant", "DoubleMax","DoubleMax"}),
            "ScrollBar.supportsAbsolutePositioning",new UIDefaults.ProxyLazyValue(
                "ch.randelshofer.quaqua.osx.OSXPreferences","isStringEqualTo",   
                new Object[]{OSXPreferences.GLOBAL_PREFERENCES, "AppleScrollerPagingBehavior", "false","true"}),
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
            "ScrollBar.trackInsets.tog", new Insets(0,0,0,0),
            "ScrollBar.trackInsets.tog.small", new Insets(0,0,0,0),
            "ScrollBar.trackInsets.tog.mini", new Insets(0,0,0,0),
            "ScrollBar.preferredSize", new Dimension(15,15),
            "ScrollBar.preferredSize.small", new Dimension(13,13),
            "ScrollBar.preferredSize.mini", new Dimension(9,9),
            "ScrollBar.focusable", Boolean.FALSE,
            "ScrollBar.thumbInsets", new Insets(2,4,2,3),
            "ScrollBar.thumbInsets.small", new Insets(2,3,2,3),
            "ScrollBar.thumbInsets.mini", new Insets(2,3,2,2),
            //
            "ScrollPane.border", scrollPaneBorder,
            //
            "Separator.highlight", new ColorUIResource(0xe3e3e3),
            "Separator.foreground", new ColorUIResource(0xd4d4d4),
            "Separator.shadow", new AlphaColorUIResource(0x0),
            "Separator.border", new VisualMarginBorder(),
            //
            "TabbedPane.disabledForeground", disabledForeground,
            "TabbedPane.tabInsets", new InsetsUIResource(1, 10, 4, 9),
            "TabbedPane.selectedTabPadInsets", new InsetsUIResource(2, 2, 2, 1),
            "TabbedPane.tabAreaInsets", new InsetsUIResource(4, 16, 0, 16),
            "TabbedPane.contentBorderInsets", new InsetsUIResource(5, 6, 6, 6),
            //"TabbedPane.background", (isBrushedMetal) ? table.get("TabbedPane.background") : makeTextureColor(0xf4f4f4, pantherDir+"Panel.texture.png"),
            "TabbedPane.tabLayoutPolicy", (isJaguarTabbedPane()) ? JTabbedPane.WRAP_TAB_LAYOUT : JTabbedPane.SCROLL_TAB_LAYOUT,
            "TabbedPane.wrap.disabledForeground", disabledForeground,
            "TabbedPane.wrap.tabInsets", new InsetsUIResource(1, 10, 4, 9),
            "TabbedPane.wrap.selectedTabPadInsets", new InsetsUIResource(2, 2, 2, 1),
            "TabbedPane.wrap.tabAreaInsets", new InsetsUIResource(4, 16, 0, 16),
            "TabbedPane.wrap.contentBorderInsets", new InsetsUIResource(2, 3, 3, 3),
            //"TabbedPane.wrap.background", (isBrushedMetal) ? table.get("TabbedPane.background") : makeTextureColor(0xf4f4f4, pantherDir+"Panel.texture.png"),
            "TabbedPane.scroll.selectedTabPadInsets", new InsetsUIResource(0, 0, 0, 0),
            "TabbedPane.scroll.tabRunOverlay", 0,
            "TabbedPane.scroll.tabInsets", new InsetsUIResource(1, 7, 2, 7),
            "TabbedPane.scroll.smallTabInsets", new InsetsUIResource(1, 5, 2, 5),
            "TabbedPane.scroll.outerTabInsets", new InsetsUIResource(1, 11, 2, 11),
            "TabbedPane.scroll.smallOuterTabInsets", new InsetsUIResource(1, 9, 2, 9),
            "TabbedPane.scroll.contentBorderInsets", new InsetsUIResource(2, 2, 2, 2),
            "TabbedPane.scroll.tabAreaInsets", new InsetsUIResource(-2, 16, 1, 16),
            "TabbedPane.scroll.contentBorder", makeNativeImageBevelBorder(
            OSXAquaPainter.Widget.frameGroupBox,new Insets(0,0,0,0), new Insets(7, 7, 7, 7),new Insets(7, 7, 7, 7), true),
            "TabbedPane.scroll.emptyContentBorder", makeNativeImageBevelBorder(
            OSXAquaPainter.Widget.frameGroupBox,new Insets(0,0,0,0), new Insets(7, 7, 7, 7),new Insets(7, 7, 7, 7), true),
            "TabbedPane.scroll.tabBorders", makeImageBevelBorders(commonDir + "Toggle.borders.png",
            new Insets(8, 10, 15, 10), 10, true),
            "TabbedPane.scroll.tabFocusRing", makeImageBevelBorder(commonDir + "Toggle.focusRing.png",
            new Insets(8, 10, 15, 10), true),
            "TabbedPane.scroll.eastTabBorders", makeImageBevelBorders(commonDir + "Toggle.east.borders.png",
            new Insets(8, 1, 15, 10), 10, true),
            "TabbedPane.scroll.eastTabFocusRing", makeImageBevelBorder(commonDir + "Toggle.east.focusRing.png",
            new Insets(8, 4, 15, 10), true),
            "TabbedPane.scroll.centerTabBorders", makeImageBevelBorders(commonDir + "Toggle.center.borders.png",
            new Insets(8, 0, 15, 1), 10, true),
            "TabbedPane.scroll.centerTabFocusRing", makeImageBevelBorder(commonDir + "Toggle.center.focusRing.png",
            new Insets(8, 4, 15, 4), false),
            "TabbedPane.scroll.westTabBorders", makeImageBevelBorders(commonDir + "Toggle.west.borders.png",
            new Insets(8, 10, 15, 1), 10, true),
            "TabbedPane.scroll.westTabFocusRing", makeImageBevelBorder(commonDir + "Toggle.west.focusRing.png",
            new Insets(8, 10, 15, 4), true),
            //
            "Table.ascendingSortIcon", makeIcon(getClass(), snowLeopardDir + "Table.ascendingSortIcon.png"),
            "Table.descendingSortIcon", makeIcon(getClass(), snowLeopardDir + "Table.descendingSortIcon.png"),
            "Table.scrollPaneBorder", scrollPaneBorder,
            //
//            "TextField.border", textFieldBorder,
            "TextField.borderInsets", new InsetsUIResource(3,6,3,6),
            "TextField.smallBorderInsets", new InsetsUIResource(3, 5, 2, 5),
            "TextField.miniBorderInsets", new InsetsUIResource(3, 5, 2, 5),
            "TextField.searchBorderInsets", new InsetsUIResource(6,12,5,12),
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
            "ToolBar.title.background", toolBarTitleBackground,
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
            "Tree.openIcon", makeIcon(getClass(), leopardDir + "Tree.openIcon.png"),
            "Tree.closedIcon", makeIcon(getClass(), leopardDir + "Tree.closedIcon.png"),
            "Tree.sideBar.background", new InactivatableColorUIResource(//
            new GradientColor(0xe8ecf1, 0xe8ecf1, 0xdadfe6),//
            new GradientColor(0xf7f7f7, 0xf7f7f7, 0xeeeeee)),
            "Tree.sideBar.selectionBorder", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.lion.QuaquaLionSideBarSelectionBorder"),
            "Tree.leftChildIndent", 8, // 7
            "Tree.rightChildIndent", 12, // 13
            "Tree.icons", makeIcons(lionDir + "Tree.icons.png", 15, true),
            "Tree.sideBar.icons", makeIcons(lionDir + "Tree.sideBar.icons.png", 15, true),
            "Tree.sideBarCategory.foreground", new InactivatableColorUIResource(0x707e8b, 0x868b92),
            "Tree.sideBarCategory.selectionForeground", new InactivatableColorUIResource(0xffffff, 0xffffff),
            "Tree.sideBarCategory.font", new FontUIResource("Lucida Grande", Font.BOLD, 11),
            "Tree.sideBarCategory.selectionFont", new FontUIResource("Lucida Grande", Font.BOLD, 11),
            "Tree.sideBar.foreground", new InactivatableColorUIResource(0x000000, 0x000000),
            "Tree.sideBar.selectionForeground", new InactivatableColorUIResource(0xffffff, 0xffffff),
            "Tree.sideBar.font", new FontUIResource("Lucida Grande", Font.PLAIN, 11),
            "Tree.sideBar.selectionFont", new FontUIResource("Lucida Grande", Font.BOLD, 11),
            "Tree.sideBarCategory.selectionForeground", new InactivatableColorUIResource(0xffffff, 0xffffff),//
            "Tree.rendererMargins", new InsetsUIResource(0,0,0,0),
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
            toolBarTitleBackground = new InactivatableColorUIResource(new ColorUIResource(222, 222, 222), new ColorUIResource(246, 246, 246));
        }

        Object[] uiDefaults = new Object[] {
                "control", toolBarTitleBackground,
                "ToolBar.title.background", toolBarTitleBackground,
        };

        putDefaults(table, uiDefaults);
    }
}
