# Item Organizer

Item Organizer is a native Android app built in Kotlin that retrieves data from a remote API and displays it in an easy-to-read, filterable, and searchable list.

## Key Features

1. **Data Retrieval**: Fetches data from an API using Retrofit and parses it using GSON.
2. **Data Filtering**: Filters out any blank or null values for `name` received from the API and sorts the list by `listId` and then by `name`.
3. **Search Functionality**: Enables users to search through the list, with the keyboard hiding upon clicking the clear icon in the search bar.
4. **Filter by List ID**: Allows users to filter the list by `listId` while also supporting simultaneous search functionality.

## Demo
### Video
[![Watch the video](https://img.youtube.com/vi/O0iQBMTUAWg/maxresdefault.jpg)](https://youtu.be/O0iQBMTUAWg)

## Code Structure

This project follows the Model-View-ViewModel (MVVM) architecture to ensure a clean and maintainable codebase. Here’s an overview of the main components:

### Model:
- **ItemModel**: Represents the data items retrieved from the remote source.
- **GroupedItem**: Represents the grouped data by `listId`.
- **Data**: Holds each group's `ids` and `name`.

### Network:
- **ApiService**: Defines the Retrofit API service for fetching data.
- **RetrofitObject**: Provides the Retrofit instance.

### Repository:
- **ItemRepo**: Handles data fetching from the remote source using Retrofit.

### UI:
- **ItemList**: Displays the list of retrieved items grouped and sorted.
- **Header**: Displays the header for the list.
- **ListIdRow**: Displays the `listId` row.
- **ItemRow**: Displays individual items (`id` & `name`) in the list.
- **SearchBar**: Provides search functionality by item `name/id`.
- **ListIdFilter**: Allows filtering of items by `listId`.
- **CustomProgressIndicator**: Displays a loading indicator while data is being fetched.

### Main Activity:
- **MainActivity**: Sets up the UI, observes the ViewModel's LiveData, and integrates the UI components.

### ViewModel:
- **MainActivityViewModel**: Manages data retrieval, processing, and UI state. Implements methods for grouping, sorting, filtering, searching and handling user interactions.
