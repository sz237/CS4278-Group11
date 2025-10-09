# Team Setup - Authentication System

## Quick Setup for Teammates

### 1. Pull Latest Changes
```bash
git pull origin main
```

### 2. Configure Your MongoDB Connection

Create this file: `PlusOneBackend/src/main/resources/application.properties`

```properties
spring.application.name=PlusOneBackend

# Replace with YOUR MongoDB Atlas credentials
spring.data.mongodb.uri=mongodb+srv://YOUR_USERNAME:YOUR_PASSWORD@YOUR_CLUSTER.mongodb.net/?retryWrites=true&w=majority&appName=YOUR_CLUSTER_NAME
spring.data.mongodb.database=plusone

server.port=8080
```

**Get your connection string from MongoDB Atlas:**
1. Go to your cluster
2. Click "Connect" → "Connect your application"
3. Copy the connection string
4. Replace `<password>` and `<cluster-url>` with your actual values

### 3. Start the Backend
```bash
cd PlusOneBackend
./mvnw spring-boot:run
```

### 4. Start the Frontend
```bash
cd plusone
npm install
npm run dev
```

### 5. Test It
- Open http://localhost:5173
- Click "Sign up"
- Use a @vanderbilt.edu email
- Create account and test login

## Features Available

✅ **Vanderbilt Email Validation** - Only @vanderbilt.edu emails allowed  
✅ **Secure Authentication** - BCrypt password hashing  
✅ **Clean UI** - Your design preserved with full functionality  
✅ **MongoDB Integration** - Users stored in your existing database  

## API Endpoints

- `POST /api/auth/signup` - Create account
- `POST /api/auth/login` - Login
- `GET /api/auth/test` - Health check

## Troubleshooting

**Backend won't start?**
- Check Java 17 is installed: `java -version`
- Verify MongoDB connection string in application.properties

**Frontend won't start?**
- Run `npm install` first
- Check Node.js is installed

**Authentication errors?**
- Verify MongoDB user has "Read and write to any database" permissions
- Check IP address `0.0.0.0/0` is allowed in Atlas Network Access

---

**That's it! Your authentication system is ready to use.** 🚀
