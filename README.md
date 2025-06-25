# Android-Food-App
This app has the user select their allergens from a given list, then allows them to scan any product barcode, and shows if that product is a potential risk if consumed.

# Search screen
<span>
  <p>
  <img src="https://github.com/user-attachments/assets/bc8d505f-4a14-482f-8d6e-9d0241d078f3" width="270">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/user-attachments/assets/d74da80c-0e15-4dfd-9b26-11152e10a6d7" width="270">
  </p>
</span>
Users can scroll through the list of allergens to select their preferences, or search for it using the Search Bar at the top. Their selection is highlighted in green once clicked.

# Scan screen
<span>
  <p>
  <img src="https://github.com/user-attachments/assets/21de484e-44f0-4b92-987a-c03b7b45a6fc" width="270">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/user-attachments/assets/d50bdd96-01b2-4e2d-9b87-007c9be34f2d" width="270">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </p>
</span>
The scan screen shows the contents of the user's camera. When a barcode is detected using ML Kit's barcode API, the barcode number is then checked if it is in the Open Food API database. If it is, then the "Scan Barcode" text at the top is replaced with a green text which reads, "Scanned".
<br>

<span>
  <p>
    <img src="https://github.com/user-attachments/assets/e7da6697-68fc-4e4c-b84f-206b3e539175" width="270">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="https://github.com/user-attachments/assets/8c71b3b4-b38c-4f83-a167-8294e8e8896e" width="270">
  </p>
</span>
