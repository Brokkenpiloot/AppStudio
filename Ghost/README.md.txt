# Ghost

Ghost is a two player word game where players take turns providing a letter, until a word is made or until a word can no longer be made.
Either way, the last person to have provided a letter is the loser.

1. Users can fill in their names.
2. Users can play Ghost by providing letters one by one.
3. The winner (or not-loser) is given cake! (This cake may or may not be a lie.)
4. A scoreboard will increment a player's score after each of his or her wins.
5. A menu will give users the following options: Reset game (without or with name change), change language, and show scoreboard.
6. Game progress will be recorded and will auto-loaded after each new start-up.

![Alt text](C:\Users\startklaar\Documents\GitHub\Ghost\20150226_163115.jpg)

The initial plan was to let players fill in their names in the same activity as where they could play the game.
This turned out to be a lot more complicated than anticipated, since the submit button (added in place of 'enter' submission)
needed to check way too many conditions. This turned out to be very bug-prone. I there fore chose to implement a home screen:

![Alt text](C:\Users\startklaar\Documents\GitHub\Ghost\Screenshot_2015-03-23-10-09-33.jpg)

This homescreen is an easy way for a user to destinguish the setup phase from the game phase of the app.
Apart from filling in names, it also let's users change the language, or read up on the rules of the game.
The function of changing the language in game was removed due to it being bug-prone, and due to its 
diminishing importance after the implementation of the homescreen. 
One important feature that is missing in the homescreen is autocompletion in the player name EditTexts.
Getting access to the names stored in SharedPreferences and working out a dropdown menu that would trigger based on user input
(if a user fills in an 'a' the menu should show all previously entered names starting with an a) turned out to be harder than anticipated.
After filling in two names clicking the 'start game!' button opens up the mainactivity.

![Alt text](C:\Users\startklaar\Documents\GitHub\Ghost\20150226_163121.jpg)
![Alt text](C:\Users\startklaar\Documents\GitHub\Ghost\Screenshot_2015-03-23-10-10-17.jpg)

This stage of the design was largely unaltered in the final release. The tab functionality turned out to be hard to implement and without merit
and was therefore replaced by two TextViews with altering typefaces depending on whose turn it is.
'Enter' submission was replaced by a submit button for user comfort.
A custom 'Dictionary' object is made at the creation of the main activity. The language of the dictionary depends on the intent passed to it.
After each user input, a filter method goes through the dictionary to filter out any words that are not prefixed by the onscreen word fragment.
The game ends depending on how many words are left in the dictionary. 0 words signifies end of game, 1 word signifies end of game if the condition
is met that the onscreen word fragment is identical to the word in the dictionary (peafow will therefore not end the game, but peafowl will).
More than 1 word will not trigger end of game.
An example of the game ending:

![Alt text](C:\Users\startklaar\Documents\GitHub\Ghost\Screenshot_2015-03-23-10-10-29.jpg)
![Alt text](C:\Users\startklaar\Documents\GitHub\Ghost\Screenshot_2015-03-23-10-10-32.jpg)

After recognizing end of play via a dictionary counter, user scores are updated or added into SharedPreferences 
(loser = loser, winner = winner++) and the victory screen is launched.
The user that provided that last input loses, and his or her competitor will therefore be given (an ImageView) of some lovely cake.

![Alt text](C:\Users\startklaar\Documents\GitHub\Ghost\20150226_163158.jpg)
![Alt text](C:\Users\startklaar\Documents\GitHub\Ghost\Screenshot_2015-03-23-10-10-42.jpg)

The clickable imageview was abandoned due to its limited merit. Users were faced with a lack of choice.
As a replacement a buttonbar was added giving the user the options of restarting with the same names, going back to the homescreen,
or going to the scoreboard.

![Alt text](C:\Users\startklaar\Documents\GitHub\Ghost\Screenshot_2015-03-23-10-10-56.jpg)

The scoreboard was implemented using a textview that was concated to by a loop.
For each key value pair found, a '\n', the key, and the value were concated to the string in the textview.
Sorting this list turned out to be harder than expected, and the list is therefore suboptimal.
Furthermore, if the amount of key value pairs gets to big, the textview will fall offscreen, dragging button, which is relatively below it, with it.
This bug was intentionally left in the release, since fixing it would first require a sortable list of key value pairs.
After being sorted, the loop could for example only concat the first 10 highscores found.

![Alt text](C:\Users\startklaar\Documents\GitHub\Ghost\20150226_163209.jpg)

Lastly, the actionbar menu was deliberately kept very simple. All 4 activities have different menus which could possibly 
negatively effect user flow, but the activities all called for different functions. An alternative which was consider was to use
the same menu for all 4 activities, but making the irrelevant options unclickable. Implementing this turned out to be harder than expected,
so the easier alternative was chosen.