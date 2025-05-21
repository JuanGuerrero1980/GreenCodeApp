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

## 🚀 Getting Started

To run this project locally:

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/currency-converter-app.git
