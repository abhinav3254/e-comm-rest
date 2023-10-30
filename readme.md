---

# E-Commerce Application API Endpoints

This README documents the endpoints available in the E-Commerce application. The base URL for these endpoints is `http://localhost:9091/`.

## User Endpoints

### Sign Up
- **POST** `/user/signup`
    - Creates a new user account.
    - Example: `http://localhost:9091/user/signup`

### Log In
- **POST** `/user/login`
    - Logs a user into the system.
    - Example: `http://localhost:9091/user/login`

## Category Endpoints

### Add Category
- **POST** `/category/add`
    - Adds a new category.
    - Example: `http://localhost:9091/category/add`

### Get All Categories
- **GET** `/category/all`
    - Retrieves all categories.
    - Example: `http://localhost:9091/category/all`

### Update Category
- **POST** `/category/update/{id}`
    - Updates a specific category by ID.
    - Example: `http://localhost:9091/category/update/123`

### Delete Category
- **POST** `/category/delete/{id}`
    - Deletes a specific category by ID.
    - Example: `http://localhost:9091/category/delete/123`

## Product Endpoints

### Add Product
- **POST** `/product/add`
    - Adds a new product.
    - Example: `http://localhost:9091/product/add`

### Get All Products
- **GET** `/product/all`
    - Retrieves all products.
    - Example: `http://localhost:9091/product/all`

### Update Product
- **POST** `/product/update/{id}`
    - Updates a specific product by ID.
    - Example: `http://localhost:9091/product/update/456`

### Delete Product
- **POST** `/product/delete/{id}`
    - Deletes a specific product by ID.
    - Example: `http://localhost:9091/product/delete/456`

## Save For Later Endpoints

### Add Product to Save For Later
- **POST** `/save-for-later/add/{productId}`
    - Adds a product to the save-for-later list by ID.
    - Example: `http://localhost:9091/save-for-later/add/789`

### Get All Saved Items
- **GET** `/save-for-later/all`
    - Retrieves all products saved for later.
    - Example: `http://localhost:9091/save-for-later/all`

### Delete Product from Save For Later
- **POST** `/save-for-later/delete-product/{productId}`
    - Deletes a product from the save-for-later list by ID.
    - Example: `http://localhost:9091/save-for-later/delete-product/789`

### Delete All Saved Products
- **POST** `/save-for-later/delete-product`
    - Deletes all products from the save-for-later list.
    - Example: `http://localhost:9091/save-for-later/delete-product`

## Cart Item Endpoints

### Add to Cart
- **POST** `/cart-item/add/{productId}`
    - Adds a product to the shopping cart by ID.
    - Example: `http://localhost:9091/cart-item/add/321`

### Get All Cart Items
- **GET** `/cart-item/get`
    - Retrieves all items in the shopping cart.
    - Example: `http://localhost:9091/cart-item/get`

### Increment Quantity
- **POST** `/cart-item/increment/{productId}`
    - Increases the quantity of a specific product in the cart by ID.
    - Example: `http://localhost:9091/cart-item/increment/321`

### Decrement Quantity
- **POST** `/cart-item/decrement/{productId}`
    - Decreases the quantity of a specific product in the cart by ID.
    - Example: `http://localhost:9091/cart-item/decrement/321`

### Delete One Cart Item
- **DELETE** `/cart-item/delete/{cartItemId}`
    - Deletes a specific item from the cart by ID.
    - Example: `http://localhost:9091/cart-item/delete/123`

---

