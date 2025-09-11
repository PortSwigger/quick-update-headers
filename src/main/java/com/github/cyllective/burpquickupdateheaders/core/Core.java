package com.github.cyllective.burpquickupdateheaders.core;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import com.github.cyllective.burpquickupdateheaders.contextmenu.ContextMenuItemUpdateHeader;
import com.github.cyllective.burpquickupdateheaders.settingsmenu.SettingsMenuHeaderFilter;

public class Core implements BurpExtension {
    @Override
    public void initialize(MontoyaApi api) {
        api.extension().setName("Quick Update Headers");

        // Context menu
        api.userInterface().registerContextMenuItemsProvider(new ContextMenuItemUpdateHeader(api));

        // Settings tab
        SettingsMenuHeaderFilter headerFilter = SettingsMenuHeaderFilter.getInstance();
        api.userInterface().registerSettingsPanel(headerFilter.getSettingsPanel());
    }
}