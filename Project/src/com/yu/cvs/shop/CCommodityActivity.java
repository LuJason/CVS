package com.yu.cvs.shop;

import com.yu.cvs.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class CCommodityActivity extends ActionBarActivity implements
		CCommodityNavigationDrawerFragment.NavigationDrawerCallbacks {

	private static final String ListTag = "list";
	private static final String InfoTag = "info";

	private CCommodityNavigationDrawerFragment mNavigationDrawerFragment;
	
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.commodity_activity);

		mNavigationDrawerFragment = (CCommodityNavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,	(DrawerLayout) findViewById(R.id.drawer_layout));

//		ActionBar bar = getSupportActionBar();
//		int change = bar.getDisplayOptions() ^ ActionBar.DISPLAY_HOME_AS_UP;
//		bar.setDisplayOptions(change, ActionBar.DISPLAY_HOME_AS_UP);
		
		mTitle = getString(R.string.search_commodity);

		String action = getIntent().getAction();

		Fragment f = createFragment(ListTag);
		f.setArguments(new Bundle());
		getSupportFragmentManager().beginTransaction().replace(R.id.container, f, ListTag).commit();

	}

	private Fragment createFragment(String tag) {
		
		Fragment f = null;

		if (ListTag.equals(tag)) {
			f = Fragment.instantiate(this,
					CCommodityListFragment.class.getName());
		}

		if (InfoTag.equals(tag)) {
			f = Fragment.instantiate(this,
					CCommodityInfoFragment.class.getName());
		}


		return f;
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// TODO Auto-generated method stub
		FragmentManager fragmentManager = getSupportFragmentManager();
		
		CCommodityListFragment f = (CCommodityListFragment) fragmentManager.findFragmentByTag(ListTag);
		
		if(f == null){
			
			Bundle arg = new Bundle();
			arg.putString(CCommodityListFragment.ARG_KEY, "种类：" + position);

			f = (CCommodityListFragment) createFragment(ListTag);
			f.setArguments(arg);
			
			fragmentManager.beginTransaction().replace(R.id.container, f).commit();
			
			
		}else{
			
			Bundle arg = new Bundle();
			arg.putString(CCommodityListFragment.ARG_KEY, "种类：" + position);
			
			f.queryCommoditys(arg);
			
		}
		
		
	}
	
	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		}
	}
	
	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

//	/**
//	 * A placeholder fragment containing a simple view.
//	 */
//	public static class PlaceholderFragment extends Fragment {
//		/**
//		 * The fragment argument representing the section number for this
//		 * fragment.
//		 */
//		private static final String ARG_SECTION_NUMBER = "section_number";
//
//		/**
//		 * Returns a new instance of this fragment for the given section number.
//		 */
//		public static PlaceholderFragment newInstance(int sectionNumber) {
//			PlaceholderFragment fragment = new PlaceholderFragment();
//			Bundle args = new Bundle();
//			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//			fragment.setArguments(args);
//			return fragment;
//		}
//
//		public PlaceholderFragment() {
//		}
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(R.layout.fragment_main, container,	false);
//			return rootView;
//		}
//
//		@Override
//		public void onAttach(Activity activity) {
//			super.onAttach(activity);
//			((CCommodityActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
//		}
//	}

}
