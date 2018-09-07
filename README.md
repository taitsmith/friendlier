# friendlier
An open source way to make friends.

The goal of friendlier is to provide an alternative to closed source, Facebook-requiring apps that one might use to meet people or make friends.

There are a number of potential issues that come with this, the most obvious is user authentication and verification. With Facebook authentication we sacrifice privacy for the ability to largely not worry that people are who they say they are. So a potential solution is to allow users to sign in to the app with the Google account on their phone. Again, this somewhat alleviates the problem though still with the concern for privacy (also see users on Lineage or a non-Google version of Android). Allowing users to sign in with an email address and password mostly solves the issue of privacy but again prevents us from verifying that users are indeed the person they claim to be. The working version of the app will use Google sign in for the time being.

The app makes fairly heavy use of Google services (specifically location, Firebase Authentication, RealtimeDB, and Cloud Messaging). Users will be directed to the Google privacy policy, and on our end users will be permitted to view and delete all stored data at any time. In the case of location, it will be updated when a user opens the app, only the most recent value will be stored and no history will be logged.

The goal of complete transparency is the most important aspect of the project.
