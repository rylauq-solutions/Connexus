# 📋 Connexus - Smart Contact Management System

<div align="center">

**A secure full-stack contact management application with OAuth2 authentication and cloud-based image storage.**

[Features](#-features) -  [Architecture](#-architecture) -  [Tech Stack](#-technology-stack) -  [Installation](#-installation) -  [Screenshots](#-screenshots)

</div>

***

## 🎯 Overview

Connexus is a comprehensive contact management system that enables users to securely store, organize, and manage their personal and professional contacts. Built with modern web technologies, it provides a seamless user experience with OAuth2 social login integration and cloud-based profile picture storage.

### Key Highlights

- **OAuth2 Social Login** with Google and GitHub authentication
- **Secure Authentication** using Spring Security and BCrypt password encryption
- **Cloud Storage** integration with Cloudinary for contact profile pictures
- **Advanced Search** with multi-field filtering (name, email, phone)
- **Pagination** for efficient data browsing
- **Responsive Design** with Thymeleaf templates and Tailwind CSS
- **Custom OAuth2 Handler** for seamless third-party authentication

***

## ✨ Features

### 🔐 Authentication & Security
- **Form-based Authentication** with email and password
- **Google OAuth2 Login** for quick access
- **GitHub OAuth2 Login** for developer-friendly authentication
- **BCrypt Password Hashing** for secure credential storage
- **Custom OAuth2 Success Handler** for post-login processing
- **Session Management** with automatic logout functionality
- **Remember Me** functionality for persistent sessions

### 📇 Contact Management
- **CRUD Operations**: Create, read, update, and delete contacts
- **Profile Pictures**: Upload and manage contact photos with Cloudinary
- **Contact Details**: Store name, email, phone, address, description
- **Social Links**: Add website and LinkedIn profile URLs
- **Favorite Contacts**: Mark important contacts for quick access
- **Contact Validation**: Email format, phone number pattern validation

### 🔍 Search & Filter
- **Multi-field Search**: Search by name, email, or phone number
- **Real-time Filtering**: Dynamic search results
- **Pagination**: Navigate large contact lists efficiently
- **Sorting Options**: Sort contacts by name or other fields
- **Empty State Handling**: User-friendly messages for no results

### 👤 User Profile
- **Profile Management**: View and edit user information
- **Profile Picture**: Upload personal avatar
- **Account Information**: Display user details and account status
- **Provider Info**: Show authentication method (Self, Google, GitHub)

### 🎨 User Interface
- **Responsive Design**: Works on desktop, tablet, and mobile
- **Tailwind CSS**: Modern, utility-first styling
- **Thymeleaf Templates**: Server-side rendering for SEO
- **Flash Messages**: Success/error notifications
- **Form Validation**: Real-time input validation with error messages

***

## 🏗️ Architecture

Connexus follows a **Model-View-Controller (MVC)** architecture pattern:

```
┌─────────────────────────────────────────────────┐
│          Presentation Layer (View)              │
│   Thymeleaf Templates • Tailwind CSS            │
└───────────────────┬─────────────────────────────┘
                    │ HTTP Requests/Responses
┌───────────────────▼─────────────────────────────┐
│         Controller Layer (Spring MVC)           │
│   @Controller • @RestController                 │
└───────────────────┬─────────────────────────────┘
                    │ Method Calls
┌───────────────────▼─────────────────────────────┐
│       Business Logic Layer (Service)            │
│   @Service • Business Rules • Validation        │
└───────────────────┬─────────────────────────────┘
                    │ Data Access
┌───────────────────▼─────────────────────────────┐
│      Data Access Layer (Repository)             │
│   Spring Data JPA • Custom Queries              │
└───────────────────┬─────────────────────────────┘
                    │ JDBC
┌───────────────────▼─────────────────────────────┐
│             MySQL Database                      │
│   Users • Contacts • Social Links               │
└─────────────────────────────────────────────────┘

         External Services
┌──────────────────┐  ┌──────────────────┐
│  Google OAuth2   │  │  GitHub OAuth2   │
└──────────────────┘  └──────────────────┘
         │                     │
         └──────────┬──────────┘
                    │
         ┌──────────▼──────────┐
         │  Cloudinary API     │
         │  (Image Storage)    │
         └─────────────────────┘
```

### Design Principles

- **MVC Pattern**: Clear separation of concerns between Model, View, and Controller
- **Server-Side Rendering**: Thymeleaf for fast page loads and SEO optimization
- **Repository Pattern**: Spring Data JPA for database abstraction
- **Service Layer**: Business logic isolated from controllers
- **OAuth2 Integration**: Custom success handler for third-party authentication
- **Cloud-First Storage**: Cloudinary API for scalable image hosting

***

## 🛠️ Technology Stack

### Backend
- **Framework:** Spring Boot 3.5.0
- **Language:** Java 21
- **Security:** Spring Security + OAuth2 Client
- **Database:** MySQL 8.0+
- **ORM:** Spring Data JPA (Hibernate)
- **Template Engine:** Thymeleaf
- **Validation:** Spring Boot Starter Validation
- **Build Tool:** Maven
- **Cloud Storage:** Cloudinary SDK

### Frontend
- **Template Engine:** Thymeleaf
- **Styling:** Tailwind CSS 4.1.10
- **Icons:** Font Awesome
- **JavaScript:** Vanilla JS (for interactive features)
- **Forms:** HTML5 with Bootstrap-like styling

### Development Tools
- **Version Control:** Git & GitHub
- **IDE:** IntelliJ IDEA / Eclipse / STS
- **API Client:** Postman / Browser DevTools
- **Package Manager:** Maven (Backend), npm (Frontend CSS)
- **Hot Reload:** Spring Boot DevTools

### External Services
- **OAuth2 Providers:** Google, GitHub
- **Image Storage:** Cloudinary
- **Email:** (Ready for Spring Mail integration)

***

## 📦 Installation

### Prerequisites

Ensure you have the following installed:
- **Java JDK 21+** - [Download](https://www.oracle.com/java/technologies/downloads/)
- **MySQL 8.0+** - [Download](https://dev.mysql.com/downloads/)
- **Maven 3.8+** - [Download](https://maven.apache.org/download.cgi)
- **Git** - [Download](https://git-scm.com/)
- **Node.js & npm** (for Tailwind CSS) - [Download](https://nodejs.org/)

### External Accounts (Required)
- **Google OAuth2** credentials - [Get here](https://console.cloud.google.com/)
- **GitHub OAuth2** app - [Create here](https://github.com/settings/developers)
- **Cloudinary** account - [Sign up](https://cloudinary.com/)

***

## 🚀 Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/rylauq-solutions/Connexus.git
cd Connexus
```

### 2. Database Setup

Create a MySQL database:

```sql
CREATE DATABASE connexus;
```

### 3. Configure Application Properties

Edit `src/main/resources/application.properties`:

```properties
# Application Name
spring.application.name=connexus
server.port=8081

# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/connexus
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password

# JPA/Hibernate Configuration
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

# OAuth2 Configuration - Google
spring.security.oauth2.client.registration.google.client-id=YOUR_GOOGLE_CLIENT_ID
spring.security.oauth2.client.registration.google.client-secret=YOUR_GOOGLE_CLIENT_SECRET
spring.security.oauth2.client.registration.google.scope=email,profile

# OAuth2 Configuration - GitHub
spring.security.oauth2.client.registration.github.client-id=YOUR_GITHUB_CLIENT_ID
spring.security.oauth2.client.registration.github.client-secret=YOUR_GITHUB_CLIENT_SECRET
spring.security.oauth2.client.registration.github.scope=user:email

# Cloudinary Configuration
cloudinary.cloud.name=YOUR_CLOUDINARY_CLOUD_NAME
cloudinary.api.key=YOUR_CLOUDINARY_API_KEY
cloudinary.api.secret=YOUR_CLOUDINARY_API_SECRET

# File Upload Limits
spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=5MB
```

***

### 4. Set Up OAuth2 Credentials

#### **Google OAuth2**

1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project or select existing
3. Enable **Google+ API**
4. Go to **Credentials** → **Create Credentials** → **OAuth 2.0 Client ID**
5. Configure OAuth consent screen
6. Set **Authorized redirect URI**: `http://localhost:8081/login/oauth2/code/google`
7. Copy **Client ID** and **Client Secret**

#### **GitHub OAuth2**

1. Go to [GitHub Developer Settings](https://github.com/settings/developers)
2. Click **New OAuth App**
3. Set **Authorization callback URL**: `http://localhost:8081/login/oauth2/code/github`
4. Copy **Client ID** and **Client Secret**

#### **Cloudinary**

1. Sign up at [Cloudinary](https://cloudinary.com/)
2. Go to **Dashboard**
3. Copy **Cloud Name**, **API Key**, and **API Secret**

***

### 5. Install Tailwind CSS (Optional for Development)

```bash
npm install
```

To compile Tailwind CSS:

```bash
npx tailwindcss -i src/main/resources/static/css/input.css -o src/main/resources/static/css/output.css --watch
```

***

### 6. Run the Application

```bash
mvn clean install
mvn spring-boot:run
```

**Application will run on:** `http://localhost:8081`

***

### 7. Access the Application

Open your browser and navigate to:
- **Home:** `http://localhost:8081/`
- **Login:** `http://localhost:8081/login`
- **Register:** `http://localhost:8081/register`

**Register a new account or login with:**
- **Google Account** (OAuth2)
- **GitHub Account** (OAuth2)
- **Email & Password** (Form-based)

***

## 📂 Project Structure

```
connexus/
├── src/
│   ├── main/
│   │   ├── java/com/connexus/
│   │   │   ├── config/                    # Configuration classes
│   │   │   │   ├── AppConfig.java         # Cloudinary bean config
│   │   │   │   ├── SecurityConfig.java    # Spring Security config
│   │   │   │   └── OAuthAuthenticationSuccessHandler.java  # OAuth2 handler
│   │   │   ├── controllers/               # MVC Controllers
│   │   │   │   ├── PageController.java    # Public pages (home, login, register)
│   │   │   │   ├── UserController.java    # User dashboard & profile
│   │   │   │   ├── ContactController.java # Contact CRUD operations
│   │   │   │   ├── RootController.java    # Global model attributes
│   │   │   │   └── ApiController.java     # REST API endpoints
│   │   │   ├── entities/                  # JPA Entities
│   │   │   │   ├── User.java              # User entity (implements UserDetails)
│   │   │   │   ├── Contact.java           # Contact entity
│   │   │   │   ├── SocialLink.java        # Social link entity
│   │   │   │   └── Providers.java         # OAuth provider enum
│   │   │   ├── repositories/              # Spring Data JPA Repositories
│   │   │   │   ├── UserRepository.java    # User data access
│   │   │   │   └── ContactRepository.java # Contact data access with custom queries
│   │   │   ├── services/                  # Business logic layer
│   │   │   │   ├── UserService.java       # User service interface
│   │   │   │   ├── ContactService.java    # Contact service interface
│   │   │   │   ├── ImageService.java      # Image upload interface
│   │   │   │   └── impl/                  # Service implementations
│   │   │   │       ├── UserServiceImpl.java
│   │   │   │       ├── ContactServiceImpl.java
│   │   │   │       ├── ImageServiceImpl.java  # Cloudinary integration
│   │   │   │       └── SecurityCustomUserDetailService.java
│   │   │   ├── forms/                     # Form DTOs
│   │   │   │   ├── UserForm.java          # User registration form
│   │   │   │   └── ContactForm.java       # Contact create/update form
│   │   │   ├── helpers/                   # Utility classes
│   │   │   │   ├── AppConstants.java      # Application constants
│   │   │   │   ├── Helper.java            # Helper methods
│   │   │   │   ├── Message.java           # Flash message DTO
│   │   │   │   ├── MessageType.java       # Message type enum
│   │   │   │   ├── SessionHelper.java     # Session utilities
│   │   │   │   └── ResourceNotFoundException.java
│   │   │   └── ConnexusApplication.java   # Main Spring Boot application
│   │   └── resources/
│   │       ├── templates/                 # Thymeleaf templates
│   │       │   ├── base.html              # Base layout
│   │       │   ├── home.html              # Landing page
│   │       │   ├── login.html             # Login page
│   │       │   ├── register.html          # Registration page
│   │       │   ├── about.html             # About page
│   │       │   ├── services.html          # Services page
│   │       │   └── user/                  # User dashboard templates
│   │       │       ├── dashboard.html     # User dashboard
│   │       │       ├── profile.html       # User profile
│   │       │       ├── add_contact.html   # Add contact form
│   │       │       ├── contacts.html      # Contact list with pagination
│   │       │       ├── update_contact.html # Edit contact form
│   │       │       └── search.html        # Search results
│   │       ├── static/                    # Static assets
│   │       │   ├── css/
│   │       │   │   ├── input.css          # Tailwind source
│   │       │   │   └── output.css         # Compiled CSS
│   │       │   ├── js/                    # JavaScript files
│   │       │   └── images/                # Static images
│   │       │       └── Connexus-Monogram.png
│   │       └── application.properties     # Configuration file
│   └── test/                              # Test files (ready for implementation)
├── pom.xml                                # Maven dependencies
├── package.json                           # npm dependencies (Tailwind)
├── tailwind.config.js                     # Tailwind configuration
└── README.md                              # This file
```

***

## 🎨 User Interface

### Key Pages

1. **Home Page** - Landing page with app introduction
2. **Login Page** - Form-based and OAuth2 social login
3. **Registration Page** - New user sign-up with validation
4. **User Dashboard** - Overview of user account and contacts
5. **User Profile** - View and edit user information
6. **Add Contact** - Form to create new contacts
7. **View Contacts** - Paginated list of all contacts
8. **Edit Contact** - Update existing contact details
9. **Search Contacts** - Filter contacts by name, email, or phone

### UI Features

- **Responsive Navigation** - Mobile-friendly menu
- **Flash Messages** - Success/error notifications
- **Form Validation** - Real-time input validation
- **Loading States** - User feedback during operations
- **Empty States** - Helpful messages when no data
- **Pagination Controls** - Navigate through contact pages

***

## 🔐 Security Features

- **Spring Security** with custom configuration
- **BCrypt Password Encoding** (strength: 10 rounds)
- **OAuth2 Client** for Google and GitHub
- **Custom OAuth2 Success Handler** for post-authentication logic
- **CSRF Protection** (disabled for API endpoints)
- **Session Management** with logout functionality
- **UserDetails Implementation** for authentication
- **Password Validation** (minimum 6 characters)
- **Email Validation** with regex pattern
- **Phone Number Validation** (10 digits)
- **SQL Injection Prevention** via JPA/Hibernate

***

## 📊 Database Schema

### Core Entities

#### **users** Table
- `user_id` (VARCHAR, PK) - Unique user identifier (UUID)
- `username` (VARCHAR) - User's display name
- `email` (VARCHAR, UNIQUE) - User's email (login username)
- `password` (VARCHAR) - BCrypt hashed password
- `about` (TEXT) - User bio/description
- `profile_pic` (VARCHAR) - Profile picture URL
- `phone_number` (VARCHAR) - User's phone
- `enabled` (BOOLEAN) - Account status
- `email_verified` (BOOLEAN) - Email verification status
- `phone_verified` (BOOLEAN) - Phone verification status
- `provider` (ENUM) - Authentication provider (SELF, GOOGLE, GITHUB)
- `provider_user_id` (VARCHAR) - OAuth provider user ID

#### **contacts** Table
- `id` (VARCHAR, PK) - Unique contact identifier (UUID)
- `name` (VARCHAR) - Contact's name
- `email` (VARCHAR) - Contact's email
- `phone_number` (VARCHAR) - Contact's phone
- `address` (VARCHAR) - Contact's address
- `picture` (VARCHAR) - Contact profile picture URL (Cloudinary)
- `description` (TEXT) - Additional notes
- `favorite` (BOOLEAN) - Favorite contact flag
- `website_link` (VARCHAR) - Contact's website
- `linked_in_link` (VARCHAR) - LinkedIn profile URL
- `cloudinary_image_public_id` (VARCHAR) - Cloudinary image ID
- `user_id` (VARCHAR, FK) - Owner user reference

#### **social_links** Table
- `id` (BIGINT, PK, AUTO_INCREMENT) - Unique link ID
- `link` (VARCHAR) - Social media URL
- `title` (VARCHAR) - Link title/description
- `contact_id` (VARCHAR, FK) - Contact reference

### Relationships

- **User → Contacts**: One-to-Many (CASCADE DELETE)
- **Contact → Social Links**: One-to-Many (CASCADE DELETE)

**Database Normalization:** Third Normal Form (3NF)

***

## 🔍 API Endpoints

### Public Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/` | Home page (redirects to `/home`) |
| `GET` | `/home` | Landing page |
| `GET` | `/about` | About page |
| `GET` | `/services` | Services page |
| `GET` | `/login` | Login page |
| `GET` | `/register` | Registration page |
| `POST` | `/do-register` | Process user registration |

### Protected Endpoints (Authenticated Users)

#### User Dashboard
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/user/dashboard` | User dashboard |
| `GET` | `/user/profile` | User profile page |

#### Contact Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/user/contacts` | View all contacts (paginated) |
| `GET` | `/user/contacts/add` | Add contact form |
| `POST` | `/user/contacts/add` | Create new contact |
| `GET` | `/user/contacts/update/{contactId}` | Edit contact form |
| `POST` | `/user/contacts/update/{contactId}` | Update contact |
| `GET` | `/user/contacts/delete/{contactId}` | Delete contact |
| `GET` | `/user/contacts/search` | Search contacts |

### REST API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/contacts/{contactId}` | Get contact by ID (JSON) |

***

## 🔧 Configuration Details

### Cloudinary Image Upload

**Image Transformation Settings:**
- **Width:** 500px
- **Height:** 500px
- **Crop Mode:** Fill
- **Format:** Auto-optimized

**Default Image:**
If no profile picture is uploaded, a default placeholder image is used.

### Pagination Settings

- **Default Page Size:** 10 contacts per page
- **Sorting:** Ascending by name (default)
- **Customizable:** Page size and sort direction via query parameters

### Form Validation Rules

**Contact Form:**
- **Name:** 2-50 characters, required
- **Email:** Valid email format, required
- **Phone:** Exactly 10 digits, required
- **Address:** Max 255 characters
- **Description:** Max 1000 characters
- **Website:** Valid URL format (optional)
- **LinkedIn:** Valid LinkedIn URL (optional)
- **Profile Picture:** Max 5MB, image formats only

**User Registration:**
- **Name:** Min 3 characters, required
- **Email:** Valid email, unique, required
- **Password:** Min 6 characters, required
- **About:** Required
- **Phone:** 8-12 characters

***

## 🗺️ Roadmap & Future Enhancements

### Phase 1: Testing & Quality
- [ ] **Unit Tests** with JUnit 5 and Mockito
- [ ] **Integration Tests** for controllers and services
- [ ] **End-to-End Tests** with Selenium
- [ ] **Code Coverage** reports (target: 80%+)

### Phase 2: Enhanced Features
- [ ] **Email Verification** for new accounts
- [ ] **Password Reset** functionality via email
- [ ] **Contact Import/Export** (CSV, vCard)
- [ ] **Contact Groups/Tags** for organization
- [ ] **Advanced Search** with multiple filters
- [ ] **Contact Sharing** with other users
- [ ] **Bulk Operations** (delete, update multiple contacts)

### Phase 3: User Experience
- [ ] **Dark Mode** theme toggle
- [ ] **Profile Picture Cropper** before upload
- [ ] **Contact Notes** with timestamps
- [ ] **Activity Log** for contact interactions
- [ ] **Favorite Contacts** quick access
- [ ] **Recently Viewed** contacts

### Phase 4: Integration & Scalability
- [ ] **Google Contacts** sync
- [ ] **Microsoft Outlook** sync
- [ ] **RESTful API** with Swagger documentation
- [ ] **Mobile App** (React Native / Flutter)
- [ ] **WebSocket** for real-time updates
- [ ] **Redis Caching** for performance
- [ ] **Docker** containerization
- [ ] **CI/CD Pipeline** with GitHub Actions

### Phase 5: Enterprise Features
- [ ] **Multi-tenancy** support
- [ ] **Role-Based Access Control** (RBAC)
- [ ] **Audit Logs** for compliance
- [ ] **Two-Factor Authentication (2FA)**
- [ ] **LDAP/Active Directory** integration
- [ ] **Data Export** for GDPR compliance

***

## 🐛 Known Issues & Limitations

### Current Limitations
- **No Email Service**: Password reset and verification features not implemented
- **No Testing**: Unit and integration tests not yet written
- **Single User Context**: No multi-user collaboration or sharing
- **Image Size Limit**: 5MB max for profile pictures
- **Search Performance**: Full-text search not optimized for large datasets
- **Mobile View**: Some UI elements need responsive optimization

### Technical Debt
- Improve error handling with custom exception classes
- Implement caching for frequently accessed data
- Add comprehensive logging
- Optimize database queries with indexes
- Implement DTOs for API responses
- Add API versioning

***

## 🔧 Troubleshooting

### Common Issues

**Port 8081 Already in Use**
```bash
# Windows
netstat -ano | findstr :8081
taskkill /PID <PID> /F

# Linux/Mac
lsof -ti:8081 | xargs kill -9
```

Or change port in `application.properties`:
```properties
server.port=9090
```

**Database Connection Error**
- Verify MySQL is running
- Check database credentials in `application.properties`
- Ensure database `connexus` exists
- Check MySQL user permissions

**OAuth2 Login Not Working**
- Verify OAuth credentials in `application.properties`
- Check redirect URIs match in Google/GitHub console
- Ensure callback URLs use correct port
- Clear browser cookies and try again

**Cloudinary Upload Failing**
- Verify Cloudinary credentials
- Check file size (max 5MB)
- Ensure image format is supported (JPG, PNG, GIF, WEBP)
- Check internet connectivity

**Tailwind CSS Not Applied**
- Run `npx tailwindcss -i src/main/resources/static/css/input.css -o src/main/resources/static/css/output.css`
- Clear browser cache
- Check if `output.css` is generated
- Verify Thymeleaf template includes correct CSS path

**Session Lost After Login**
- Check browser cookie settings
- Verify `JSESSIONID` cookie is set
- Check CORS configuration if using different ports
- Disable CSRF for testing (not recommended for production)

***

## 📞 Support & Contact

For issues, questions, or contributions:
- **GitHub Issues**: [Report a bug](https://github.com/rylauq-solutions/Connexus/issues)

***

<div align="center">

**Made with ❤️ using Spring Boot & Thymeleaf**

⭐ **Star this repository if you find it helpful!** ⭐

***

**© 2026 Connexus | Built by [rylauq-solutions](https://github.com/rylauq-solutions)**

</div>
