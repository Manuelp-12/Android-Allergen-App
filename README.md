# Android-Allergen-App
This app has the user select their allergens from a given list, then allows them to scan any product barcode or take a picture of their product, and shows if that product is a potential risk if consumed.

# Search screen
Users can scroll through the list of allergens to select their preferences, or search for it using the Search Bar at the top. Their selection is highlighted in green once clicked.

<span>
  <p>
  <img src="https://github.com/user-attachments/assets/bc8d505f-4a14-482f-8d6e-9d0241d078f3" width="270">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/user-attachments/assets/d74da80c-0e15-4dfd-9b26-11152e10a6d7" width="270">
  </p>
</span>

# Scan screen
The scan screen shows the contents of the user's camera. When a barcode is detected using <a href="https://developers.google.com/ml-kit/vision/barcode-scanning">ML Kit's barcode API</a>, the barcode number is then checked if it is in the <a href="https://openfoodfacts.github.io/openfoodfacts-server/api/">Open Food Facts API</a> database. If it isn't, then the user is notified with a message at the bottom of the screen saying, "Product not found". If it is, then the "Scan Barcode" text at the top is replaced with a green text which reads, "Scanned".

<span>
  <p>
  <img src="https://github.com/user-attachments/assets/21de484e-44f0-4b92-987a-c03b7b45a6fc" width="270">
  <img src="https://github.com/user-attachments/assets/e159c451-faa8-4eb3-adea-a3856886ab50" width="270">
  <img src="https://github.com/user-attachments/assets/d50bdd96-01b2-4e2d-9b87-007c9be34f2d" width="270">
  </p>
</span>

# Detect screen
The detect screen allows the user to take a picture of their food, and upload it to be analyzed. The machine learning model for detecting the user's foods is made with <a href="https://www.tensorflow.org">TensorFlow</a> and was trained on the <a href="https://www.tensorflow.org/datasets/catalog/food101">Food 101 dataset</a> with a 73% accuracy. The code for training the model can be found <a href="https://github.com/Manuelp-12/food101training">here</a>.

<span>
  <p>
    <img width="270"src="https://github.com/user-attachments/assets/048f0c7e-1295-4d4a-9149-e64a84b535a2" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img width="270" src="https://github.com/user-attachments/assets/4754caa4-c292-4ea7-8c97-c49216f50ef0" />

  </p>
</span>

# Product attributes
If the product is scanned, the app takes the user to a screen which shows several of the product's attributes, including an image of the product, whether it is a risk to the user (if one or more of the allergens selected is in the product), the product's key allergens, and the product's entire ingredients list. If the product is a risk to the user, the text displays, "Risk: High" and the circle next to it is red. The key allergens in the product that match up with the user's selected allergens are highlighted in yellow as well. Otherwise, the the text displays, "Risk: Low" and the circle next to it is green.

If the product is detected, the app shows the image that the user took with its predicted food underneath, with its confidence level that the food is correct. Each food's main and potential allergens are taken from research about the foods, including restaurant websites and cooking tutorials. The risk attribute also contains an additional category "Medium" with a yellow circle if the user matches any potential allergens but not any main allergens.

<span>
  <p>
    <img src="https://github.com/user-attachments/assets/e7da6697-68fc-4e4c-b84f-206b3e539175" width="270">
    <img width="270"src="https://github.com/user-attachments/assets/810fbd5e-2e33-429f-b0de-880e482ad3ff" />
    <img src="https://github.com/user-attachments/assets/a44e04d5-a47f-4442-9fae-851b8385862a" width="270">
  </p>
</span>

# History screen
The history screen shows the user's previously scanned products and their attributes such as the product image, name, and brand, as well as its risk to the user. It also includes an arrow on the right side which takes the user to the complete product attribute screen shown above in the "Product Attributes" section.

<span>
  <p>
    <img src="https://github.com/user-attachments/assets/c73e1efe-3c56-4e84-b33f-3863baa4ea04" width="270">
  </p>
</span>
