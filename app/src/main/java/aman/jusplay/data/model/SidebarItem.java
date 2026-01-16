package aman.jusplay.data.model;

public class SidebarItem {
    private String title;
    private int iconResId;

    public SidebarItem(String title, int iconResId) {
        this.title = title;
        this.iconResId = iconResId;
    }

    public String getTitle() {
        return title;
    }

    public int getIconResId() {
        return iconResId;
    }
}
