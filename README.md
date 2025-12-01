# Online Thrift Shop – Spring Boot Backend

This is the backend API for the "Online Thrift Shop" application, built with Spring Boot 3.4.8, as part of a university course called "Development of Information Systems".  
It provides REST endpoints for authentication, product management, user profiles, and admin operations.

The backend is designed to work together with the Angular 20 frontend.

---

## Features
- JWT authentication (login/signup)
- User roles (admin & regular user)
- CRUD operations for products using JPA
- Image uploading (multipart/form-data)
- Product filtering, sorting, and searching
- Favourites & purchases management
- Admin-only operations (manage products, view users)
- Configured CORS for frontend communication
