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
  <img src="https://github.com/user-attachments/assets/e159c451-faa8-4eb3-adea-a3856886ab50" width="270">
  <img src="https://github.com/user-attachments/assets/d50bdd96-01b2-4e2d-9b87-007c9be34f2d" width="270">
  </p>
</span>
The scan screen shows the contents of the user's camera. When a barcode is detected using <a href="https://developers.google.com/ml-kit/vision/barcode-scanning">ML Kit's barcode API</a>, the barcode number is then checked if it is in the Open Food Facts API database. If it isn't, then the user is notified with a message at the bottom of the screen saying, "Product not found". If it is, then the "Scan Barcode" text at the top is replaced with a green text which reads, "Scanned".
<br>
<br>

<span>
  <p>
    <img src="https://github.com/user-attachments/assets/e7da6697-68fc-4e4c-b84f-206b3e539175" width="270">
    <img src="https://github.com/user-attachments/assets/8c71b3b4-b38c-4f83-a167-8294e8e8896e" width="270">
    <img src="https://github.com/user-attachments/assets/a44e04d5-a47f-4442-9fae-851b8385862a" width="270">
  </p>
</span>
Once the product is scanned, the app takes the user to a screen which shows several of the product's attributes, including an image of the product, whether it is a risk to the user (if one or more of the allergens selected is in the product), the product's key allergens, and the product's entire ingredients list. If the product is a risk to the user, the text displays, "Risk: High" and the circle next to it is red. The key allergens in the product that match up with the user's selected allergens are highlighted in yellow as well. Otherwise, the the text displays, "Risk: Low" and the circle next to it is green.

# History screen

<span>
  <p>
    <img src="https://github.com/user-attachments/assets/c73e1efe-3c56-4e84-b33f-3863baa4ea04" width="270">
  </p>
</span>
The history screen shows the user's previously scanned products and their attributes such as the product image, name, and brand, as well as its risk to the user. It also includes an arrow on the right side which takes the user to the complete product attribute screen shown above.
