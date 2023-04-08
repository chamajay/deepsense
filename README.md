<div align="center">
	
<img width="" src="https://user-images.githubusercontent.com/116336229/230703545-d95d7bfa-eb24-48ab-ad22-598a41b80597.png"  width=160 height=160  align="center">



### AI Powered Mood, Depression and Suicidal Text Patterns Detection on Android

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
<div align="left">


<p align="justify"><b>DeepSense</b> is an Android application that utilizes two machine learning models to analyze system-wide typed text to accurately determine the user's emotional state.
The app uses an accessibility service to capture text input across the system, enabling it to provide insights into the user's mood and mental state in real-time 
and to notify an emergency contact if it detects the user is at risk of suicide.</p>


## Screenshots

<table align="center">
  <tr>
    <td align="center"><img src="https://user-images.githubusercontent.com/116336229/230706720-120c140b-0277-4e70-82b3-40aee7f35f26.png" width="80%" /></td>
    <td align="center"><img src="https://user-images.githubusercontent.com/116336229/230706738-5f7b11e8-24d2-4abf-a472-3f3c9a32e76c.png" width="80%" /></td>
    <td align="center"><img src="https://user-images.githubusercontent.com/116336229/230706786-52b11695-d1e5-40c9-9cf6-edcd00f5e5e6.png" width="80%" /></td>
  </tr>

</table>

<table align="center">
  <tr>
    <td align="center"><img src="https://user-images.githubusercontent.com/116336229/230707366-d2fff34c-20b7-4e0e-83ee-6348424d2f0e.png" width="80%" /></td>
    <td align="center"><img src="https://user-images.githubusercontent.com/116336229/230707453-e72feb1c-90a1-463e-9c38-d2b92108a068.png" width="80%" /></td>
    <td align="center"><img src="https://user-images.githubusercontent.com/116336229/230707456-df695084-5c7a-476d-be5e-cc030e533da2.png" width="80%" /></td>
  </tr>
</table>

<br>


## Features
- **Text capture**: Capture typed text in popular chat apps (WhatsApp, Telegram, Signal, Slack) and web browsers (Firefox, Chrome, Bromite) to analyze the user's mood and mental health.

- **Mood detection**: Use machine learning models to identify the user's mood based on the typed text.

- **Mood analysis**: Calculate the average mood percentages, mood status, and all emotions in a typed text to provide a comprehensive analysis.

- **Mental health assessment**: Based on the predicted results, determine the user's current mental health condition and provide recommendations for improvement.

- **Emergency response**: If suicidal text patterns are detected, the app will send an emergency SMS to the saved emergency contact, providing immediate support and assistance.

- **User authentication**: Seamless authentication using One Tap Google Log in and Firebase to provide a secure and personalized experience for the user.

- **Material 3**: App uses the latest material design system from Google, offering a modern and intuitive user interface that enhances usability and user experience.


## Installation and Usage

1. Setup the local backend server: [https://github.com/chamajay/deepsense-backend](https://github.com/chamajay/deepsense-backend)

2. Clone the repository:

    ```
    git clone https://github.com/chamajay/deepsense.git
    ```

3. Change the `BASE_URL` constant inside `Constants.kt` file to include the local server address.

4. Build the application from source:
    ```
    ./gradlew assembleDebug
    ```

5. Install and run the application.

## Contributors
- Chamath Jayasena ([@chamajay](https://github.com/chamajay))
- Kumudu Wijewardhana ([@kumuduwije](https://github.com/kumuduwije))
- Sahan Dissanayake ([@sahandissanayake](https://github.com/sahandissanayake419))
- Thimasha Thakshali ([@ThimashaThakshali](https://github.com/ThimashaThakshali))
- Ruwindi Perera ([@RuwindiPerera](https://github.com/RUWINDIPERERA))
