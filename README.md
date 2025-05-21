# 📱 Currency Converter App

**Currency Converter App** is an Android application built with **Kotlin** and **Jetpack Compose** that allows users to convert amounts between currencies in real time using the [exchangerate.host](https://exchangerate.host/) API.

## ✨ Features

- 🔄 Convert between currencies with real-time exchange rates
- 🌍 Select source and target currencies from a dynamic dropdown (via `/list` endpoint)
- 💰 Instant result display
- 💾 Store conversion history locally with Room
- 📜 View recent conversions in a history list
- 🔍 Navigate to a detailed screen for each conversion
- ✅ Modern architecture using ViewModel, StateFlow, Repository pattern, and Hilt

## 🛠️ Tech Stack

- **Kotlin**
- **Jetpack Compose** – for UI
- **ViewModel + StateFlow** – for state management
- **Room** – for local database storage
- **Retrofit** – for HTTP requests
- **Hilt** – for dependency injection
- **Navigation Compose** – for screen navigation

## 🔐 API Reference

This app uses [https://exchangerate.host/](https://api.exchangerate.host/) as its external currency data provider. API key is required.

### Endpoints used:

- `GET /list` – Retrieves a list of available currencies and their descriptions.
- `GET /convert` – Converts an amount from one currency to another using the most up-to-date exchange rates.

---

## 📦 Architecture

The app follows a clean, modular architecture, structured as follows:

- **data**: API service and Room database implementation (Retrofit + Room)
- **domain**: Models, UseCases, and repository interfaces
- **presentation**: UI components using Jetpack Compose and ViewModel
- **di**: Dependency injection configuration using Hilt

It uses modern Android development best practices: unidirectional data flow, dependency injection, and separation of concerns.

---

## 🚀 Getting Started

To run this project locally:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/currency-converter-app.git

2. **Open the project in Android Studio Arctic Fox (or newer):**  
Make sure you have Kotlin and Jetpack Compose support enabled.

3. **Sync Gradle:**  
Android Studio will usually prompt you to sync Gradle automatically.
If it doesn’t, click "Sync Now" in the toolbar.

4. **Run the app:** 
You can run the app on a connected physical device or an Android emulator.

---

## 📈 Future Improvements & Scalability Notes

As the Currency Converter App evolves, here are some suggestions to improve its functionality, maintainability, and scalability:

### 🧩 Feature Enhancements

- **Favorites & Quick Access:** Allow users to mark frequent currency pairs as favorites for faster conversions.
- **Offline Support:** Cache the latest conversion rates for offline use and show the last updated time.
- **Dark Mode Toggle:** Integrate support for dark/light themes using Material You guidelines.
- **Multi-language Support:** Add localization for key markets (e.g., Spanish, French, etc.) to improve accessibility.
- **Widget Integration:** Provide a home screen widget to quickly convert currencies without opening the app.

### ⚙️ Technical Improvements

- **Use of Clean Architecture Layers:** Further separate UI, domain, and data layers using interfaces and mappers for better testing and flexibility.
- **Pagination for History:** If the number of conversions grows, use lazy loading/paging to handle large datasets efficiently.
- **Testing:** Add more unit tests and UI tests using frameworks like JUnit, Mockito, and Compose Testing.
- **Modularization:** Split the app into dynamic feature modules (`:home`, `:history`, `:core`, `:detail`, etc.) to improve build performance and code isolation.
- **Error Handling:** Improve network and database error reporting with user-friendly messages and retry mechanisms.

### 🌐 Scalability Considerations

- **Remote Storage Option:** As the user base grows, support cloud sync (e.g., Firebase, Supabase) so users can access their history across devices.
- **Analytics & Crash Reporting:** Integrate tools like Firebase Analytics and Crashlytics to monitor user behavior and app stability in production.
- **Rate Limiting & Caching:** If the external API is used by many users, implement request caching or proxy APIs to reduce dependency and avoid limits.
- **CI/CD Integration:** Set up automated testing and builds using GitHub Actions or Bitrise for faster and safer deployments.

---

These improvements would prepare the app for long-term growth, enhance user experience, and ensure maintainability as the codebase evolves.
