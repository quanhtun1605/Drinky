package com.example.minhd.drinky;


public class TabMessage {
    public static String get(int menuItemId, boolean isReselection) {
        String message = "Content for ";

        switch (menuItemId) {
            case R.id.tab_home:
                message += "home";
                break;
            case R.id.tab_nav:
                message += "nav";
                break;
            case R.id.tab_star:
                message += "star";
                break;
            case R.id.tab_user:
                message += "user";
                break;
            default:
        }

        if (isReselection) {
            message += " WAS RESELECTED! YAY!";
        }

        return message;
    }

    public static int getPos(int menuItemId, boolean isReselection) {
        int pos = 0;

        switch (menuItemId) {
            case R.id.tab_home:
                pos = 1;
                break;
            case R.id.tab_nav:
                pos = 2;

                break;
            case R.id.tab_star:
                pos = 3;

                break;
            case R.id.tab_user:
                pos = 4;

                break;
            default:
        }

        return pos;
    }

}
