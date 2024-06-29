# CookEase - Recipe Search App

## Overview

CookEase is a simple recipe search app built using Kotlin and Jetpack Compose. The app allows users to search for recipes, view detailed information about each recipe, and manage a list of favorite recipes. The app interacts with the Spoonacular API to fetch recipe data.

## Features

- Fetch and display a list of popular recipes using the *Get Random Recipes* API.
- Search for recipes using the *Search Recipes* API and display the results on the home screen.
- View detailed information about a recipe, including ingredients, instructions, nutrients, and similar recipes.
- Mark recipes as favorites and store them locally for easy access.
- Animated bottom sheet for viewing recipe details with stackable layout and swipe-down functionality to navigate back.
- Cached list of favorite recipes.
- Advertisements displayed after every five recipes in the list.
- Separate module for Networking.
- Supports both XML and Compose for UI.

## API Documentation

For detailed information about the Spoonacular API, visit the [API Documentation](https://spoonacular.com/food-api/docs).

## Design

You can view the design for the app on [Figma](https://www.figma.com/file/3osDPZ0cy0AMfynjkRFOoV/MathOnGo---Android---Assignment?type=design&node-id=0-1&mode=design).

## Getting Started

### Prerequisites

- Android Studio
- Kotlin 1.5 or higher

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/recipe-search-app.git
    ```
2. Open the project in Android Studio.
3. Add your Spoonacular API key to the `local.properties` file:
    ```properties
    spoonacularApiKey=YOUR_API_KEY
    ```
4. Build and run the project on an emulator or physical device.

## Usage

- Home Screen: Displays a list of popular recipes.
- Search: Allows users to search for recipes.
- Recipe Details: View complete information about a selected recipe.
- Favorites: Manage and view a list of favorite recipes.
- Advertisements: Displayed after every five recipes in the list.

## Media

### Screenshots

![Screenshot Collage](images/image.jpg)

### Video Demo

Watch the video demo of CookEase [here](video/demo_video.mp4).

## Contributing

Contributions are welcome! Please fork the repository and use a feature branch. Pull requests are accepted.

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- [Spoonacular API](https://spoonacular.com/food-api) for providing recipe data.
- [Figma](https://www.figma.com) for design inspiration.