# Stage 1: Build the Angular app
FROM node:20 AS build
WORKDIR /app

# Copy package files and install dependencies
COPY package*.json ./
RUN npm install -f

# Copy the project files and build the Angular app
COPY . .
RUN npm run ng -- build --configuration production

# Stage 2: Serve the app using Nginx
FROM nginx:1.21
COPY --from=build /app/dist/rms-ui /usr/share/nginx/html
EXPOSE 80
