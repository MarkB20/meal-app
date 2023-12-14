# Meal Finder App
# Overview
The Meal Finder App is a mobile application built for Android using Java and the Room persistence library. It allows users to easily search for meal recipes using the TheMealDB API. Users can search for meals by ingredient, by name, and save meals for later reference in a local database on their device.

# Key Features
* Search for meals by ingredient using the TheMealDB API
* Save search results to a local SQLite database using Room
* Search local database for meals containing user-specified strings in the meal name or ingredients
* Display images for meals in search results
* Handle device rotation gracefully, retaining user's place in the app
* Additional search directly against TheMealDB API by meal name substring

# User Interface
The app has an intuitive, user-friendly interface allowing users to easily:

* Add meals to the local database
* Search for meals by ingredient via the API
* Save API search results to the local database
* Search the local database by string matching on meal name/ingredients
* View images alongside search results
The interface adapts smoothly to device rotation, ensuring a seamless user experience.

# Technical Details
The app makes extensive use of:

* TheMealDB API - to retrieve meal data
* Room - a SQLite object mapping library for persistent local storage
* Volley - for networking and API integration
* It follows Android best practices around layouts, styles, dimens, strings etc. for maintainability.

Unit tests validate expected app behavior using Mockito test doubles. Espresso UI tests cover main user workflows.

# Conclusion
The Meal Finder App delivers robust functionality for searching meal recipes via API and locally, with an intuitive interface optimized for mobile. Its modular architecture would allow extending it to support user accounts, favorites etc.
