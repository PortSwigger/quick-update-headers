package com.github.cyllective.burpquickupdateheaders.settingsmenu;


import burp.api.montoya.ui.settings.SettingsPanelBuilder;
import burp.api.montoya.ui.settings.SettingsPanelPersistence;
import burp.api.montoya.ui.settings.SettingsPanelSetting;
import burp.api.montoya.ui.settings.SettingsPanelWithData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SettingsMenuHeaderFilter {
    private static SettingsMenuHeaderFilter instance;
    private final SettingsPanelWithData settingsPanel;

    private SettingsMenuHeaderFilter() {
        this.settingsPanel = SettingsPanelBuilder.settingsPanel()
                .withPersistence(SettingsPanelPersistence.USER_SETTINGS)
                .withTitle("Settings")
                .withDescription("Configure the 'Quick Update Headers' extension")
                .withKeywords("Header", "Filter", "Settings", "Selection")
                .withSettings(
                        SettingsPanelSetting.stringSetting("Header List", "Authorization,Cookie")
                )
                .build();
    }

    public static synchronized SettingsMenuHeaderFilter getInstance() {
        if (instance == null) {
            instance = new SettingsMenuHeaderFilter();
        }
        return instance;
    }

    // Expose the settings panel for registration
    public SettingsPanelWithData getSettingsPanel() {
        return settingsPanel;
    }


    public static boolean isHeaderInSelection(String headerName) {
        return getInstance().containsHeader(headerName);
    }

    // To get the list of headers from the comma-separated string
    private List<String> getHeaderList() {
        String headerString = settingsPanel.getString("Header List");
        if (headerString == null || headerString.trim().isEmpty()) {
            return List.of(); // Return empty list if no headers configured
        }

        return Arrays.stream(headerString.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    // To check if a specific header is in the list
    private boolean containsHeader(String headerName) {
        if (headerName == null) {
            return false;
        }

        List<String> headers = getHeaderList();
        return headers.stream()
                .anyMatch(header -> header.equalsIgnoreCase(headerName.trim()));
    }
}