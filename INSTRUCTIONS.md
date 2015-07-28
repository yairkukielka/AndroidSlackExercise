Hi there!

This document describes the programming exercise for all Android candidates @ Slack. If anything is not clear or could use more explanation, please don't hesitate to ask! 

###The exercise
Create an app that displays a list of all users on a Slack team using the results of a call to `https://api.slack.com/methods/users.list`. Upon clicking on a user row, the app should drill into the user's profile. On the individual profile page, you should show the person's picture, username, real name, and title. Other profile fields are optional.

###Additional details
* The exercise should take a good afternoon's worth of work.
* The app should work offline from a fresh open (e.g. force close and opening the app in airplane mode should still work fine after one previous launch). The persistence implementation does not matter, but the app should ideally be written in such a way that you could swap out implementations at a later date.
* You need only support API 15 and above.
* You are encouraged to use any 3rd party libraries that you deem appropriate. Please provide a brief explanation of why you chose to use each of the libraries you end up using.
* Any design details are also up to you. You will not be intensely scrutinized for design choices (this is an engineering role!).
* The app should look and feel like something you are proud of. Feel free to have some fun :)

###Instructions for submitting the exercise
1. Create a new git repo and Android Studio project, committing with frequency and with the type of commit messages you would write on a typical project.
2. A README that gives and overview of the project is strongly encouraged 
3. Zip up the repo and attach to an email when you've completed the exercise and we will review it as soon as possible. If you would like to use a private hosted repo somewhere that is fine as well.
4. We'll build it your app directly from the repo you provide

###Credentials and Team Info
Use the following API method and the following API token to complete this task. We've created a team and added some members specifically for this exercise.

Documentation: https://api.slack.com/methods/users.list <br>
API Token: xoxp-5048173296-5048346304-5180362684-7b3865 <br>

Please let me know if you have any questions!
