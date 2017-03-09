package tech.libin.rahul.ideaproject.views.homescreen.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.views.homescreen.fragments.FOSActivityTab;

/**
 * Created by libin on 22/02/17.
 */

public class HomeTabAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[];
    private Context context;
    FOSActivityTab tab1 = new FOSActivityTab();
    FOSActivityTab tab2 = new FOSActivityTab();
    FOSActivityTab tab3 = new FOSActivityTab();

    private Fragment[] fragments;

    public HomeTabAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        String newActivity = context.getString(R.string.new_activities);
        String activity = context.getString(R.string.activities);
        String reminder = context.getString(R.string.reminders);
        tabTitles = new String[]{newActivity, activity, reminder};

        tab1.setActivityType(Constants.ActivityType.NEW_ACTIVITY);
        tab2.setActivityType(Constants.ActivityType.ACTIVITY);
        tab3.setActivityType(Constants.ActivityType.REMINDER);

        fragments = new Fragment[]{tab1, tab2, tab3};
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
