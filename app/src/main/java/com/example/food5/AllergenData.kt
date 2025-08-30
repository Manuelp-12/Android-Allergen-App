package com.example.food5

object AllergenData {
    val allergensMap = mapOf(
        "apple_pie" to mapOf(
            "main" to listOf("gluten", "milk"),
            "potential" to listOf("soy", "peanuts", "tree nuts")
        ),
        "baby_back_ribs" to mapOf(
            "main" to listOf("soy"),
            "potential" to listOf("gluten")
        ),
        "baklava" to mapOf(
            "main" to listOf("nuts", "peanuts", "gluten", "milk", "egg")
        ),
        "beef_carpaccio" to mapOf(
            "main" to listOf("milk"),
            "potential" to listOf("egg", "nuts", "gluten")
        ),
        "beef_tartare" to mapOf(
            "main" to listOf("egg", "mustard")
        ),
        "beet_salad" to mapOf(
            "potential" to listOf("milk", "nuts")
        ),
        "beignets" to mapOf(
            "main" to listOf("gluten", "egg", "milk"),
            "potential" to listOf("soy", "peanuts", "tree nuts")
        ),
        "bibimbap" to mapOf(
            "main" to listOf("egg", "sesame", "soy")
        ),
        "bread_pudding" to mapOf(
            "main" to listOf("gluten", "milk", "eggs", "tree nuts")
        ),
        "breakfast_burrito" to mapOf(
            "main" to listOf("gluten", "eggs", "milk"),
            "potential" to listOf("soy", "tree nuts", "peanuts")
        ),
        "bruschetta" to mapOf(
            "main" to listOf("gluten", "milk", "soy")
        ),
        "caesar_salad" to mapOf(
            "main" to listOf("gluten", "egg", "milk", "fish"),
            "potential" to listOf("soy", "mustard")
        ),
        "cannoli" to mapOf(
            "main" to listOf("gluten", "egg", "milk", "soy"),
            "potential" to listOf("tree nuts")
        ),
        "caprese_salad" to mapOf(
            "main" to listOf("milk"),
            "potential" to listOf("soy")
        ),
        "carrot_cake" to mapOf(
            "main" to listOf("gluten", "eggs", "milk", "nuts"),
            "potential" to listOf("soy", "coconut")
        ),
        "ceviche" to mapOf(
            "main" to listOf("shellfish", "fish")
        ),
        "cheesecake" to mapOf(
            "main" to listOf("dairy", "egg", "gluten"),
            "potential" to listOf("nuts", "tree nuts")
        ),
        "cheese_plate" to mapOf(
            "main" to listOf("milk"),
            "potential" to listOf("gluten", "nuts")
        ),
        "chicken_curry" to mapOf(
            "main" to listOf("gluten", "milk", "soy", "tree nuts", "peanuts"),
            "potential" to listOf("fish", "sesame")
        ),
        "chicken_quesadilla" to mapOf(
            "main" to listOf("milk", "gluten", "soy"),
            "potential" to listOf("egg")
        ),
        "chicken_wings" to mapOf(
            "main" to listOf("gluten"),
            "potential" to listOf("egg")
        ),
        "chocolate_cake" to mapOf(
            "main" to listOf("milk", "gluten", "egg", "soy"),
            "potential" to listOf("nuts")
        ),
        "chocolate_mousse" to mapOf(
            "main" to listOf("milk", "egg", "gluten", "soy"),
            "potential" to listOf("tree nuts", "peanuts")
        ),
        "churros" to mapOf(
            "main" to listOf("gluten", "egg", "milk"),
            "potential" to listOf("soy")
        ),
        "clam_chowder" to mapOf(
            "main" to listOf("shellfish", "fish", "milk", "gluten")
        ),
        "club_sandwich" to mapOf(
            "main" to listOf("gluten", "egg", "milk", "soy"),
            "potential" to listOf("fish", "mustard")
        ),
        "crab_cakes" to mapOf(
            "main" to listOf("shellfish", "gluten", "egg", "soy")
        ),
        "creme_brulee" to mapOf(
            "main" to listOf("egg", "milk")
        ),
        "croque_madame" to mapOf(
            "main" to listOf("milk", "egg", "gluten"),
            "potential" to listOf("soy")
        ),
        "cup_cakes" to mapOf(
            "main" to listOf("gluten", "milk", "egg"),
            "potential" to listOf("nuts", "soy")
        ),
        "deviled_eggs" to mapOf(
            "main" to listOf("egg"),
            "potential" to listOf("milk", "soy")
        ),
        "donuts" to mapOf(
            "main" to listOf("gluten", "milk", "egg"),
            "potential" to listOf("soy", "peanuts", "tree nuts")
        ),
        "dumplings" to mapOf(
            "main" to listOf("gluten", "soy", "egg", "shellfish"),
            "potential" to listOf("sesame")
        ),
        "edamame" to mapOf(
            "main" to listOf("soy")
        ),
        "eggs_benedict" to mapOf(
            "main" to listOf("egg", "gluten", "milk", "soy", "mustard")
        ),
        "escargots" to mapOf(
            "main" to listOf("shellfish")
        ),
        "falafel" to mapOf(
            "main" to listOf("chickpeas", "sesame"),
            "potential" to listOf("nuts")
        ),
        "filet_mignon" to mapOf(
            "main" to listOf("none")
        ),
        "fish_and_chips" to mapOf(
            "main" to listOf("fish", "gluten"),
            "potential" to listOf("egg", "milk")
        ),
        "foie_gras" to mapOf(
            "main" to listOf("none"),
            "potential" to listOf("gluten", "soy")
        ),
        "french_fries" to mapOf(
            "main" to listOf("gluten", "milk")
        ),
        "french_onion_soup" to mapOf(
            "main" to listOf("gluten", "milk", "soy")
        ),
        "french_toast" to mapOf(
            "main" to listOf("gluten", "milk", "egg")
        ),
        "fried_calamari" to mapOf(
            "main" to listOf("shellfish", "gluten"),
            "potential" to listOf("fish")
        ),
        "fried_rice" to mapOf(
            "main" to listOf("gluten", "soy", "egg"),
            "potential" to listOf("milk", "sesame")
        ),
        "frozen_yogurt" to mapOf(
            "main" to listOf("milk"),
            "potential" to listOf("gluten", "soy", "tree nuts")
        ),
        "garlic_bread" to mapOf(
            "main" to listOf("gluten", "milk"),
            "potential" to listOf("soy")
        ),
        "gnocchi" to mapOf(
            "main" to listOf("gluten"),
            "potential" to listOf("milk", "egg")
        ),
        "greek_salad" to mapOf(
            "main" to listOf("gluten", "milk", "soy")
        ),
        "grilled_cheese_sandwich" to mapOf(
            "main" to listOf("gluten", "milk"),
            "potential" to listOf("soy")
        ),
        "grilled_salmon" to mapOf(
            "main" to listOf("fish", "gluten"),
            "potential" to listOf("soy")
        ),
        "guacamole" to mapOf(
            "main" to listOf("avocado")
        ),
        "gyoza" to mapOf(
            "main" to listOf("gluten", "soy", "sesame")
        ),
        "hamburger" to mapOf(
            "main" to listOf("gluten", "sesame"),
            "potential" to listOf("milk")
        ),
        "hot_and_sour_soup" to mapOf(
            "main" to listOf("soy", "gluten", "egg"),
            "potential" to listOf("fish", "shellfish")
        ),
        "hot_dog" to mapOf(
            "main" to listOf("gluten"),
            "potential" to listOf("mustard")
        ),
        "huevos_rancheros" to mapOf(
            "main" to listOf("gluten", "milk", "egg")
        ),
        "hummus" to mapOf(
            "main" to listOf("sesame", "chickpeas")
        ),
        "ice_cream" to mapOf(
            "main" to listOf("milk"),
            "potential" to listOf("egg")
        ),
        "lasagna" to mapOf(
            "main" to listOf("gluten", "milk", "egg"),
            "potential" to listOf("soy")
        ),
        "lobster_bisque" to mapOf(
            "main" to listOf("shellfish", "fish", "gluten"),
            "potential" to listOf("milk", "egg", "soy")
        ),
        "lobster_roll_sandwich" to mapOf(
            "main" to listOf("shellfish", "gluten"),
            "potential" to listOf("milk")
        ),
        "macaroni_and_cheese" to mapOf(
            "main" to listOf("milk", "gluten"),
            "potential" to listOf("egg")
        ),
        "macarons" to mapOf(
            "main" to listOf("tree nuts", "egg")
        ),
        "miso_soup" to mapOf(
            "main" to listOf("soy", "gluten", "fish", "shellfish")
        ),
        "mussels" to mapOf(
            "main" to listOf("shellfish")
        ),
        "nachos" to mapOf(
            "main" to listOf("gluten", "milk")
        ),
        "omelette" to mapOf(
            "main" to listOf("egg"),
            "potential" to listOf("milk")
        ),
        "onion_rings" to mapOf(
            "main" to listOf("gluten"),
            "potential" to listOf("milk", "soy")
        ),
        "oysters" to mapOf(
            "main" to listOf("shellfish")
        ),
        "pad_thai" to mapOf(
            "main" to listOf("gluten", "soy", "fish", "shellfish", "egg", "peanuts")
        ),
        "paella" to mapOf(
            "main" to listOf("shellfish", "fish"),
            "potential" to listOf("gluten", "egg", "soy")
        ),
        "pancakes" to mapOf(
            "main" to listOf("gluten", "egg", "milk")
        ),
        "panna_cotta" to mapOf(
            "main" to listOf("milk", "gelatin"),
            "potential" to listOf("soy", "nuts")
        ),
        "peking_duck" to mapOf(
            "main" to listOf("gluten", "soy", "sesame")
        ),
        "pho" to mapOf(
            "main" to listOf("soy", "fish", "shellfish"),
            "potential" to listOf("peanuts", "tree nuts", "gluten")
        ),
        "pizza" to mapOf(
            "main" to listOf("gluten", "dairy")
        ),
        "pork_chop" to mapOf(
            "main" to listOf("none")
        ),
        "poutine" to mapOf(
            "main" to listOf("milk"),
            "potential" to listOf("gluten", "soy")
        ),
        "prime_rib" to mapOf(
            "main" to listOf("none")
        ),
        "pulled_pork_sandwich" to mapOf(
            "main" to listOf("gluten", "soy")
        ),
        "ramen" to mapOf(
            "main" to listOf("gluten", "sesame", "nuts", "soy")
        ),
        "ravioli" to mapOf(
            "main" to listOf("gluten", "milk", "egg")
        ),
        "red_velvet_cake" to mapOf(
            "main" to listOf("gluten", "milk", "egg")
        ),
        "risotto" to mapOf(
            "main" to listOf("milk")
        ),
        "samosa" to mapOf(
            "main" to listOf("gluten", "milk", "egg")
        ),
        "sashimi" to mapOf(
            "main" to listOf("fish", "shellfish", "soy")
        ),
        "scallops" to mapOf(
            "main" to listOf("shellfish")
        ),
        "seaweed_salad" to mapOf(
            "main" to listOf("gluten", "soy", "sesame")
        ),
        "shrimp_and_grits" to mapOf(
            "main" to listOf("shellfish", "gluten", "milk", "soy")
        ),
        "spaghetti_bolognese" to mapOf(
            "main" to listOf("gluten", "milk"),
            "potential" to listOf("soy")
        ),
        "spaghetti_carbonara" to mapOf(
            "main" to listOf("gluten", "milk", "egg"),
            "potential" to listOf("mustard")
        ),
        "spring_rolls" to mapOf(
            "main" to listOf("gluten", "egg", "soy", "tree nuts")
        ),
        "steak" to mapOf(
            "main" to listOf("none")
        ),
        "strawberry_shortcake" to mapOf(
            "main" to listOf("gluten", "milk", "egg", "tree nuts")
        ),
        "sushi" to mapOf(
            "main" to listOf("fish", "shellfish")
        ),
        "tacos" to mapOf(
            "main" to listOf("gluten"),
            "potential" to listOf("milk", "soy")
        ),
        "takoyaki" to mapOf(
            "main" to listOf("gluten", "soy", "egg", "fish"),
            "potential" to listOf("shellfish", "sesame")
        ),
        "tiramisu" to mapOf(
            "main" to listOf("gluten", "milk", "egg"),
            "potential" to listOf("soy")
        ),
        "tuna_tartare" to mapOf(
            "main" to listOf("fish", "soy", "sesame"),
            "potential" to listOf("egg")
        ),
        "waffles" to mapOf(
            "main" to listOf("gluten", "milk", "egg"),
            "potential" to listOf("soy", "nuts")
        )
    )
}