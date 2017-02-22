package tech.libin.rahul.ideaproject.homescreen.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tech.libin.rahul.ideaproject.R;

/**
 * Created by libin on 22/02/17.
 */

public class HomeTabAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[];
    private Context context;

    public HomeTabAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        String newActivity = context.getString(R.string.new_activities);
        String activity = context.getString(R.string.activities);
        String reminder = context.getString(R.string.reminders);
        tabTitles = new String[]{newActivity, activity, reminder};
    }

    @Override
    public Fragment getItem(int position) {
        return new Fragment();
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
