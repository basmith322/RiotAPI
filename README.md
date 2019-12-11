# RiotAPI

The following is an application that uses the Orianna Java Library https://github.com/meraki-analytics/orianna to
display information about summoners, servers status and free champions and the Picasso Libary https://github.com/square/picasso
to handle the loading of images throughout the app.

The application is free to use and all rights for any libaries or software used belong to the developers responsible for them.

The files included are as follows

Adapter Classes: Classes that provide various adapters to populate recycler views throughout the application
  Each adapter is used to inflate the layout combined with its relative holder class which provides callback to the 
  relevant views with the relevant Information

UI Classes: The ui classes provide the functionality behind each Activity the user will see
  
  ChampInfoActivity - Provides information about champions retrieved when using the free champion rotation lookup
  
  ChampRotationActivity - Shows the free available champions
  
  LinkedSummonerActivity - Provides the ability for users to input their summoner name and region during registration
                           to be used with their summoner profile
  
  LoginActivity - Provides login functionality
  
  LookupSummonerActivity - Allows a user to look up any summoner in any region
  
  MatchHistoryDetails - Provides the in depth details of a match that is selected from MatchHistoryList
  
  MatchHistoryList - Shows a list of 20 most recent matches
  
  MenuActivity - Acts as a menu to access each area of the application
  
  RankedLadderActivity - Shows the top 20 players in each region
  
  RegisterActivity - Provides registration functionality
  
  ServerActivity - Shows server status of each region
  
  SummonerDetailsActivity - Shows the summoner information that follows after the lookup in LookupSummonerActivity
  
  SummonerProfileActivity - Uses the user details stored from LinkedSummonerActivity to load the users profile without
                            having to search for it.

Utilities: These are additional classes that provide certain utility functions
  
  CloseKeyboard: A simple function that will minimize the keyboard after any submission function has been called 
                (E.g. Login, lookup)
  
  SetCurrentRegion: A utility function that can be called to set a region during lookup. Used with spinners in various places
                    to ensure the search is made against the correct region
  
  ShakeMethod: A class to handle shake listner functions that can be called in any activity to provide shake to refresh
               functionality
               
Views: 
activity_champ_info - shows champion info from the free rotation
activity_champ_rotation - shows the free champion rotation
activity_linked_summoner_activity - provides user input for summoner after registration
activity_login - user login page
activity_lookup_summoner - allows the user to lookup a summoner
activity_match_history_details - shows details of the match history
activity_match_history_list - shows a list of match historyu
activity_menu - provides a menu
activity_no_network - used when no network is detected
activity_ranked_ladder - shows top 20 ranked players
activity_register - user register page
activity_server - shows server status
activity_summoner_details - shows summoner details after lookup
activity_summoner_profile - similar to summoner lookup but for the user profile instead
image_item - used with a layout inflater to populate free champion rotation
text_item - used with match history and ladder inflaters to populate their respective views
               
Application Developed Using Android Studio 3.5.
