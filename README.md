# ThreeTaps

This app is working fine in the emulator below API 29. This application is not working correctly in API 30 or above. When I tried to use this app in my 
Samsung Galaxy s10 (API 30), it is not working correctly.

First of all, MainActivity.java will execute, so I write a code to hide the app icon and name in the onCreate method followed by the finish() method to terminate
the first screen.

I've used BroadcastReceiver to receive broadcasts about volume change in the device and from there, I've started my service to listen to action regarding volume change. 
In MyService, I've tried to listen to changes in volume and recorded old and new volume levels to determine differences. If there is a difference in volume level, 
then I check if the icon is displaying or not. If the icon is showing, then I will hide it and if not displaying, then I will show it.

WHEN YOU INSTALL THIS APP, YOU WILL NOT BE ABLE TO SEE THE ICON, BUT IF YOU TRIED TO CHANGE VOLUME, THE APP WOULD BE VISIBLE IF YOU ARE STILL NOT ABLE TO SEE THE 
ICON GO TO YOUR APPS LIBRARY AND SEARCH FOR "THREE TAPS" NAME YOU WILL FIND IT.
