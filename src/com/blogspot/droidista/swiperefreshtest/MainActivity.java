package com.blogspot.droidista.swiperefreshtest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	/**
	 * The fragment that has the list view.
	 */
	public static class PlaceholderFragment extends ListFragment implements OnRefreshListener {

		private SwipeRefreshLayout mSwipeRefreshLayout;

		private static final int LIST_ITEM_COUNT = 5;
		private int mOffset = 0;
		
		private ArrayAdapter<String> mListAdapter;
		
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			// Configure the swipe refresh layout
			mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.container);
			mSwipeRefreshLayout.setOnRefreshListener(this);
			mSwipeRefreshLayout.setColorScheme(
	                R.color.swipe_color_1, R.color.swipe_color_2,
	                R.color.swipe_color_3, R.color.swipe_color_4);
			
			// Put the first batch of countries in the list
			mListAdapter = new ArrayAdapter<String>(
	                getActivity(),
	                android.R.layout.simple_list_item_1,
	                android.R.id.text1,
	                getCountries(mOffset));
			
			setListAdapter(mListAdapter);
			
			return rootView;
		}

		/**
		 * Gets the next batch of countries from the list
		 * 
		 * @param offset
		 * @return
		 */
	    private List<String> getCountries(int offset) {
			ArrayList<String> countriesList = new ArrayList<String>();
			for(int i=0; i<LIST_ITEM_COUNT;i++){
				countriesList.add(COUNTRIES[offset+i]);
			}
			
			mOffset = offset + LIST_ITEM_COUNT;
			return countriesList;
		}

		@Override
	    public void onRefresh() {
			// Start showing the refresh animation
			mSwipeRefreshLayout.setRefreshing(true);
			
			// Simulate a long running activity
        	new Handler().postDelayed(new Runnable() {
	            @Override
	            public void run() {
	               updateCountries();
	            }
	        }, 5000);
	    }
	    
	    
	    
	    private void updateCountries() {
	    	
	    	// Add the next batch of countries to the list
	    	mListAdapter.addAll(getCountries(mOffset));
	    	
	    	// Signify that we are done refreshing
	    	mSwipeRefreshLayout.setRefreshing(false);
		}



		private static final String[] COUNTRIES = {"Afghanistan",
	        "Albania", "Algeria", "American Samoa", "Andorra", "Angola",
	        "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina",
	        "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan",
	        "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus",
	        "Belgium", "Belize", "Benin", "Bermuda", "Bhutan",
	        "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil",
	        "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi",
	        "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands",
	        "Central African Republic", "Chad", "Chile", "China",
	        "Christmas Island", "Cocos (Keeling) Islands", "Colombia",
	        "Comoros", "Democratic Republic of the Congo (Kinshasa)",
	        "Congo, Republic of(Brazzaville)", "Cook Islands", "Costa Rica",
	        "Ivory Coast", "Croatia", "Cuba", "Cyprus", "Czech Republic",
	        "Denmark", "Djibouti", "Dominica", "Dominican Republic",
	        "East Timor (Timor-Leste)", "Ecuador", "Egypt",
	        "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia",
	        "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France",
	        "French Guiana", "French Polynesia", "French Southern Territories",
	        "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar",
	        "Great Britain", "Greece", "Greenland", "Grenada", "Guadeloupe",
	        "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana",
	        "Haiti", "Holy See", "Honduras", "Hong Kong", "Hungary",
	        "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)",
	        "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan",
	        "Jordan", "Kazakhstan", "Kenya", "Kiribati",
	        "Korea, Democratic People\'s Rep. (North Korea)",
	        "Korea, Republic of (South Korea)", "Kosovo", "Kuwait",
	        "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia",
	        "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein",
	        "Lithuania", "Luxembourg", "Macau", "Macedonia, Rep. of",
	        "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali",
	        "Malta", "Marshall Islands", "Martinique", "Mauritania",
	        "Mauritius", "Mayotte", "Mexico", "Micronesia, Federal States of",
	        "Moldova, Republic of", "Monaco", "Mongolia", "Montenegro",
	        "Montserrat", "Morocco", "Mozambique", "Myanmar, Burma",
	        "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles",
	        "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria",
	        "Niue", "Northern Mariana Islands", "Norway", "Oman",
	        "Pakistan", "Palau", "Palestinian territories", "Panama",
	        "Papua New Guinea", "Paraguay", "Peru", "Philippines",
	        "Pitcairn Island", "Poland", "Portugal", "Puerto Rico",
	        "Qatar", "Reunion Island", "Romania", "Russian Federation",
	        "Rwanda", "Saint Kitts and Nevis", "Saint Lucia",
	        "Saint Vincent and the Grenadines", "Samoa", "San Marino",
	        "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia",
	        "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)",
	        "Slovenia", "Solomon Islands", "Somalia", "South Africa",
	        "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname",
	        "Swaziland", "Sweden", "Switzerland", "Syria, Syrian Arab Republic",
	        "Taiwan (Republic of China)", "Tajikistan",
	        "Tanzania; officially the United Republic of Tanzania",
	        "Thailand", "Tibet", "Timor-Leste (East Timor)", "Togo",
	        "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia",
	        "Turkey", "Turkmenistan", "Turks and Caicos Islands",
	        "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates",
	        "United Kingdom", "United States", "Uruguay", "Uzbekistan",
	        "Vanuatu", "Vatican City State (Holy See)", "Venezuela",
	        "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)",
	        "Wallis and Futuna Islands", "Western Sahara", "Yemen",
	        "Zambia", "Zimbabwe" };
	}

}
