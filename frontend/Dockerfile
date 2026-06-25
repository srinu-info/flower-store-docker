FROM node:24-alpine:3.23
WORKDIR /opt/server
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 4200
CMD ["npm", "start"]

# FROM node:24-alpine:3.23 AS builder
# WORKDIR /opt/server
# COPY package*